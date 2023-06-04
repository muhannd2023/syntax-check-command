package com.yarmouk.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "question_option")
@Entity
@Getter
@Setter
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String optionText;
    private boolean correct;
}
