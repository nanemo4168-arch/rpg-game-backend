package com.javatpg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "players")
@Getter @Setter @NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    private int hp = 100;
    private int currentStage = 1;
    private int totalScore = 0;
    private int comboCount = 0;

    @Enumerated(EnumType.STRING)
    private GameStatus status = GameStatus.PLAYING;

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;

    public enum GameStatus {
        PLAYING, WIN, LOSE
    }

    public static Player create(String nickname) {
        Player player = new Player();
        player.setNickname(nickname);
        player.setHp(100);
        player.setCurrentStage(1);
        player.setTotalScore(0);
        player.setComboCount(0);
        player.setStatus(GameStatus.PLAYING);
        player.setStartedAt(LocalDateTime.now());
        return player;
    }
}
