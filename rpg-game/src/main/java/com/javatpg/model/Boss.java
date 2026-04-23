package com.javatpg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bosses")
@Getter @Setter @NoArgsConstructor
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int stage;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int maxHp;

    @Column(nullable = false)
    private int currentHp;

    @Column(nullable = false)
    private int attackPower;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String emoji;
}
