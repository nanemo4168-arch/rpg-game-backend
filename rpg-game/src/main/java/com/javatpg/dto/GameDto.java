package com.javatpg.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

public class GameDto {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class StartRequest {
        private String nickname;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class AnswerRequest {
        private Long playerId;
        private Long questionId;
        private String answer;
        private String questionType; // MULTIPLE_CHOICE or CODE_INPUT
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class StartResponse {
        private Long playerId;
        private String nickname;
        private int playerHp;
        private BossInfo boss;
        private int questionsPerStage;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class QuestionResponse {
        private Long questionId;
        private String concept;
        private int difficulty;
        private int stage;
        private String questionText;
        private String questionType;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private String hint;
        private boolean hasCode;
        private int questionNumber;   // 현재 몇 번째 문제인지 (1~4)
        private int totalQuestions;   // 스테이지당 총 문제 수
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class AnswerResponse {
        private boolean correct;
        private String correctAnswer;
        private String explanation;
        private int playerHp;
        private int bossHp;
        private int bossMaxHp;
        private int comboCount;
        private int damageDealt;
        private int scoreGained;
        private int totalScore;
        private ComboEffect comboEffect;
        private String gameStatus;   // PLAYING, WIN, LOSE, STAGE_CLEAR
        private int nextStage;
        private BossInfo nextBoss;
        private boolean bossSkillActivated;   // 보스 강공격 발동 여부
        private int bossSkillDamage;          // 강공격 데미지
        private int questionNumber;
        private int totalQuestions;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class GameStateResponse {
        private Long playerId;
        private String nickname;
        private int playerHp;
        private int currentStage;
        private int bossHp;
        private int bossMaxHp;
        private String bossName;
        private int totalScore;
        private int comboCount;
        private String gameStatus;
        private int questionNumber;
        private int totalQuestions;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class BossInfo {
        private int stage;
        private String name;
        private String emoji;
        private int maxHp;
        private int currentHp;
        private String description;
        private int attackPower;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class ComboEffect {
        private String type;
        private int value;
        private String message;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class RankingEntry {
        private int rank;
        private String nickname;
        private int score;
        private int maxStage;
        private String result;
        private String recordedAt;
    }
}
