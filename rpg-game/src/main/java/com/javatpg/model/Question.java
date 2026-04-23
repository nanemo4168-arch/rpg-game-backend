package com.javatpg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions")
@Getter @Setter @NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String concept;

    @Column(nullable = false)
    private int difficulty;

    @Column(nullable = false)
    private int stage;

    @Column(name = "question_text", nullable = false, columnDefinition = "VARCHAR(5000)")
    private String questionText;

    // MULTIPLE_CHOICE or CODE_INPUT
    @Column(name = "question_type", nullable = false)
    private String questionType = "MULTIPLE_CHOICE";

    @Column(name = "option_a")
    private String optionA;

    @Column(name = "option_b")
    private String optionB;

    @Column(name = "option_c")
    private String optionC;

    @Column(name = "option_d")
    private String optionD;

    @Column(name = "correct_answer", nullable = false, columnDefinition = "VARCHAR(1000)")
    private String correctAnswer;

    @Column(columnDefinition = "VARCHAR(5000)")
    private String explanation;

    @Column(columnDefinition = "VARCHAR(2000)")
    private String hint;

    @Column(name = "has_code", nullable = false)
    private boolean hasCode = false;
}
