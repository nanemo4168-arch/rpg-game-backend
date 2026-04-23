package com.javatpg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "rankings")
@Getter @Setter @NoArgsConstructor
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int score;

    private int maxStage;
    private String result;
    private LocalDateTime recordedAt;

    public static Ranking from(Player player) {
        Ranking ranking = new Ranking();
        ranking.setNickname(player.getNickname());
        ranking.setScore(player.getTotalScore());
        ranking.setMaxStage(player.getCurrentStage());
        ranking.setResult(player.getStatus().name());
        ranking.setRecordedAt(LocalDateTime.now());
        return ranking;
    }
}
