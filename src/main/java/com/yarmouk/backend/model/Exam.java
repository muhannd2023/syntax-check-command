package com.yarmouk.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "exam")
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String name;
    protected Long categoryId;
    protected Long mark;
    protected Timestamp startTime;
    protected Timestamp endTime;
    @OneToMany(targetEntity = Question.class)
    protected List<Question> questions;
}
