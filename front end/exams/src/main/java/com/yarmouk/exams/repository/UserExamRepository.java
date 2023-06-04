package com.yarmouk.exams.repository;

import com.yarmouk.exams.model.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExamRepository extends JpaRepository<UserExam, Long> {
    List<UserExam> findAllByUserId(Long id);
}
