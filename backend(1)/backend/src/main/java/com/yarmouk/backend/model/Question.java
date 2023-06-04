package com.yarmouk.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String questionText;
    protected Long questionMark;
    protected String questionCategory;
    protected Long questionOrder;
    protected String userAnswer;
    @ManyToMany(targetEntity = QuestionOption.class)
    private List<QuestionOption> options;
}
