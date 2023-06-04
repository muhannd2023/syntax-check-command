package com.yarmouk.exams.model;

import jakarta.persistence.*;
import lombok.Data;


@Table(name = "question_option")
@Entity
@Data
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String optionText;
    private boolean correct;
}
