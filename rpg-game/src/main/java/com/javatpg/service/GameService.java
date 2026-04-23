package com.javatpg.service;

import com.javatpg.dto.GameDto.*;
import com.javatpg.model.*;
import com.javatpg.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final PlayerRepository playerRepository;
    private final BossRepository bossRepository;
    private final QuestionRepository questionRepository;
    private final GameSessionRepository gameSessionRepository;
    private final RankingRepository rankingRepository;

    private static final int BASE_DAMAGE_TO_BOSS = 50;   // 정답 시 보스 데미지 (크게)
    private static final int COMBO3_BONUS_DAMAGE = 30;
    private static final int COMBO5_HEAL_AMOUNT = 15;
    private static final int MAX_PLAYER_HP = 100;
    private static final int QUESTIONS_PER_STAGE = 4;

    public StartResponse startGame(StartRequest request) {
        Player player = Player.create(request.getNickname());
        player = playerRepository.save(player);

        Boss boss = bossRepository.findByStage(1)
                .orElseThrow(() -> new RuntimeException("보스를 찾을 수 없습니다."));

        GameSession session = new GameSession();
        session.setPlayer(player);
        session.setStage(1);
        session.setBossCurrentHp(boss.getMaxHp());
        session.setBossMaxHp(boss.getMaxHp());
        session.setQuestionCount(0);
        session.setWrongQuestionIds("");
        session.setAskedQuestionIds("");
        session.setQuestionsSinceLastBossSkill(0);
        gameSessionRepository.save(session);

        return new StartResponse(player.getId(), player.getNickname(), player.getHp(),
                toBossInfo(boss, boss.getMaxHp()), QUESTIONS_PER_STAGE);
    }

    @Transactional(readOnly = true)
    public QuestionResponse getQuestion(Long playerId) {
        Player player = findPlayer(playerId);
        GameSession session = findSession(playerId);

        Set<Long> askedIds = parseIds(session.getAskedQuestionIds());
        Set<Long> wrongIds = parseIds(session.getWrongQuestionIds());

        Question question = null;

        // 틀린 문제 25% 확률로 복습
        if (!wrongIds.isEmpty() && Math.random() < 0.25) {
            List<Long> retryable = wrongIds.stream()
                    .filter(id -> !askedIds.contains(id))
                    .collect(Collectors.toList());
            if (!retryable.isEmpty()) {
                question = questionRepository.findById(retryable.get(0)).orElse(null);
            }
        }

        if (question == null) {
            List<Long> excludeIds = new ArrayList<>(askedIds);
            if (excludeIds.isEmpty()) excludeIds.add(-1L);
            question = questionRepository.findRandomByStageExcluding(player.getCurrentStage(), excludeIds);
        }

        if (question == null) {
            session.setAskedQuestionIds("");
            gameSessionRepository.save(session);
            question = questionRepository.findRandomByStage(player.getCurrentStage());
        }

        if (question == null) throw new RuntimeException("문제가 없습니다.");
        return toQuestionResponse(question, session.getQuestionCount() + 1, QUESTIONS_PER_STAGE);
    }

    public AnswerResponse processAnswer(AnswerRequest request) {
        Player player = findPlayer(request.getPlayerId());
        GameSession session = findSession(request.getPlayerId());
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("문제를 찾을 수 없습니다."));

        addToIds(session, "asked", question.getId());
        session.setLastQuestionId(question.getId());
        session.setQuestionCount(session.getQuestionCount() + 1);

        // 대소문자 구분 없이 채점
        boolean isCorrect = checkAnswer(question, request.getAnswer());

        int damageDealt = 0, scoreGained = 0;
        ComboEffect comboEffect = null;

        if (isCorrect) {
            player.setComboCount(player.getComboCount() + 1);
            int combo = player.getComboCount();

            // 정답 시 보스에게 큰 데미지
            damageDealt = BASE_DAMAGE_TO_BOSS + (player.getCurrentStage() - 1) * 10;

            if (combo == 3) {
                damageDealt += COMBO3_BONUS_DAMAGE;
                comboEffect = new ComboEffect("DAMAGE_BOOST", COMBO3_BONUS_DAMAGE,
                        "🔥 3콤보! 추가 데미지 +" + COMBO3_BONUS_DAMAGE + "!");
                player.setComboCount(0);
            } else if (combo == 5) {
                int healed = Math.min(COMBO5_HEAL_AMOUNT, MAX_PLAYER_HP - player.getHp());
                player.setHp(player.getHp() + healed);
                comboEffect = new ComboEffect("HEAL", healed, "💚 5콤보! HP +" + healed + " 회복!");
                player.setComboCount(0);
            }

            // 코드 입력 문제는 1.5배 데미지
            if ("CODE_INPUT".equals(question.getQuestionType())) {
                damageDealt = (int)(damageDealt * 1.5);
            }

            int newBossHp = Math.max(0, session.getBossCurrentHp() - damageDealt);
            session.setBossCurrentHp(newBossHp);

            scoreGained = question.getDifficulty() * 10 * player.getCurrentStage();
            if ("CODE_INPUT".equals(question.getQuestionType())) scoreGained *= 2;
            player.setTotalScore(player.getTotalScore() + scoreGained);

            removeFromWrong(session, question.getId());

        } else {
            // 오답 시 플레이어 HP 감소 (보스 공격력만큼)
            player.setComboCount(0);
            addToIds(session, "wrong", question.getId());

            Boss boss = bossRepository.findByStage(player.getCurrentStage())
                    .orElseThrow(() -> new RuntimeException("보스를 찾을 수 없습니다."));
            int damageTaken = boss.getAttackPower();
            player.setHp(Math.max(0, player.getHp() - damageTaken));
        }

        // 게임 상태 체크
        String gameStatus = checkGameStatus(player, session);
        gameSessionRepository.save(session);
        playerRepository.save(player);

        // 스테이지 클리어 시 nextBoss는 이미 currentStage가 증가된 상태
        BossInfo nextBoss = null;
        if ("STAGE_CLEAR".equals(gameStatus)) {
            nextBoss = bossRepository.findByStage(player.getCurrentStage())
                    .map(b -> toBossInfo(b, b.getMaxHp())).orElse(null);
        }

        return new AnswerResponse(
                isCorrect,
                question.getCorrectAnswer(),
                question.getExplanation(),
                player.getHp(),
                session.getBossCurrentHp(),
                session.getBossMaxHp(),
                player.getComboCount(),
                damageDealt,
                scoreGained,
                player.getTotalScore(),
                comboEffect,
                gameStatus,
                player.getCurrentStage(),
                nextBoss,
                false, 0,
                session.getQuestionCount(),
                QUESTIONS_PER_STAGE
        );
    }

    // 대소문자 구분 없이 채점
    private boolean checkAnswer(Question question, String userAnswer) {
        if (userAnswer == null) return false;
        String correct = question.getCorrectAnswer().trim().toLowerCase()
                .replaceAll("\\s+", "");
        String user = userAnswer.trim().toLowerCase()
                .replaceAll("\\s+", "");
        return correct.equals(user);
    }

    private String checkGameStatus(Player player, GameSession session) {
        if (player.getHp() <= 0) {
            player.setStatus(Player.GameStatus.LOSE);
            saveOrUpdateRanking(player);
            return "LOSE";
        }

        if (session.getQuestionCount() >= QUESTIONS_PER_STAGE) {
            // 마지막 문제 클리어 시 빌런 HP 강제 0
            session.setBossCurrentHp(0);
            if (player.getCurrentStage() >= 3) {
                player.setStatus(Player.GameStatus.WIN);
                saveOrUpdateRanking(player);
                return "WIN";
            } else {
                // 다음 스테이지로 (1→2, 2→3)
                int nextStage = player.getCurrentStage() + 1;
                player.setCurrentStage(nextStage);

                Boss nextBoss = bossRepository.findByStage(nextStage)
                        .orElseThrow(() -> new RuntimeException("다음 보스를 찾을 수 없습니다."));
                session.setStage(nextStage);
                session.setBossCurrentHp(nextBoss.getMaxHp());
                session.setBossMaxHp(nextBoss.getMaxHp());
                session.setLastQuestionId(null);
                session.setQuestionCount(0);
                session.setAskedQuestionIds("");
                session.setQuestionsSinceLastBossSkill(0);
                return "STAGE_CLEAR";
            }
        }
        return "PLAYING";
    }

    // 같은 닉네임이면 최고점수만 유지
    private void saveOrUpdateRanking(Player player) {
        List<Ranking> existing = rankingRepository.findByNickname(player.getNickname());
        if (existing.isEmpty()) {
            rankingRepository.save(Ranking.from(player));
        } else {
            Ranking r = existing.get(0);
            if (player.getTotalScore() > r.getScore()) {
                r.setScore(player.getTotalScore());
                r.setMaxStage(player.getCurrentStage());
                r.setResult(player.getStatus().name());
                r.setRecordedAt(java.time.LocalDateTime.now());
                rankingRepository.save(r);
            }
        }
    }

    @Transactional(readOnly = true)
    public GameStateResponse getGameState(Long playerId) {
        Player player = findPlayer(playerId);
        GameSession session = findSession(playerId);
        Boss boss = bossRepository.findByStage(player.getCurrentStage())
                .orElseThrow(() -> new RuntimeException("보스를 찾을 수 없습니다."));
        return new GameStateResponse(player.getId(), player.getNickname(), player.getHp(),
                player.getCurrentStage(), session.getBossCurrentHp(), session.getBossMaxHp(),
                boss.getName(), player.getTotalScore(), player.getComboCount(),
                player.getStatus().name(), session.getQuestionCount(), QUESTIONS_PER_STAGE);
    }

    @Transactional(readOnly = true)
    public List<RankingEntry> getRankings() {
        List<Ranking> rankings = rankingRepository.findAllByOrderByScoreDesc(PageRequest.of(0, 10));
        return IntStream.range(0, rankings.size()).mapToObj(i -> {
            Ranking r = rankings.get(i);
            return new RankingEntry(i + 1, r.getNickname(), r.getScore(),
                    r.getMaxStage(), r.getResult(), r.getRecordedAt().toString());
        }).collect(Collectors.toList());
    }

    private Set<Long> parseIds(String ids) {
        if (ids == null || ids.isEmpty()) return new HashSet<>();
        return Arrays.stream(ids.split(","))
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .collect(Collectors.toSet());
    }

    private void addToIds(GameSession session, String type, Long id) {
        if ("asked".equals(type)) {
            String current = session.getAskedQuestionIds();
            session.setAskedQuestionIds(current.isEmpty() ? String.valueOf(id) : current + "," + id);
        } else if ("wrong".equals(type)) {
            Set<Long> wrongIds = parseIds(session.getWrongQuestionIds());
            wrongIds.add(id);
            session.setWrongQuestionIds(wrongIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    private void removeFromWrong(GameSession session, Long id) {
        Set<Long> wrongIds = parseIds(session.getWrongQuestionIds());
        wrongIds.remove(id);
        session.setWrongQuestionIds(wrongIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }

    private Player findPlayer(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("플레이어를 찾을 수 없습니다."));
    }

    private GameSession findSession(Long playerId) {
        return gameSessionRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new RuntimeException("세션을 찾을 수 없습니다."));
    }

    private BossInfo toBossInfo(Boss boss, int currentHp) {
        return new BossInfo(boss.getStage(), boss.getName(), boss.getEmoji(),
                boss.getMaxHp(), currentHp, boss.getDescription(), boss.getAttackPower());
    }

    private QuestionResponse toQuestionResponse(Question q, int questionNumber, int total) {
        return new QuestionResponse(q.getId(), q.getConcept(), q.getDifficulty(), q.getStage(),
                q.getQuestionText(), q.getQuestionType(),
                q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD(),
                q.getHint(), q.isHasCode(), questionNumber, total);
    }
}
