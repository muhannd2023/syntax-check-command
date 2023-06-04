package com.yarmouk.exams.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected Long duration;
    protected Long mark;
    @OneToMany(cascade = CascadeType.ALL)
    protected List<MultiChoiceQuestion> multiChoiceQuestions;
    @OneToMany(cascade = CascadeType.ALL)
    protected List<TextQuestion> textQuestions;
}
