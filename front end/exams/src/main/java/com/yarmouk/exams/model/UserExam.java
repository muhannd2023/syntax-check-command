package com.yarmouk.exams.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class UserExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @ManyToOne
    private Exam exam;
    private Long userMark;
}
