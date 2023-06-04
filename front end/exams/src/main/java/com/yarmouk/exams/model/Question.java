package com.yarmouk.exams.model;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String questionText;
    protected Long questionMark;
}
