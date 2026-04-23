package com.javatpg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game_sessions")
@Getter @Setter @NoArgsConstructor
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    private int stage;
    private int bossCurrentHp;
    private int bossMaxHp;
    private Long lastQuestionId;

    private int questionCount = 0;

    @Column(columnDefinition = "VARCHAR(1000)")
    private String wrongQuestionIds = "";

    @Column(columnDefinition = "VARCHAR(1000)")
    private String askedQuestionIds = "";

    private int questionsSinceLastBossSkill = 0;
}
