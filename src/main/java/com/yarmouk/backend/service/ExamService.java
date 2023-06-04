package com.yarmouk.backend.service;

import com.yarmouk.backend.model.Exam;
import com.yarmouk.backend.repository.ExamRepository;
import com.yarmouk.backend.response.ExamSubmitResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Optional<Exam> get(Long id) {
        return examRepository.findById(id);
    }

    public void save(Exam exam) {
        examRepository.save(exam);
    }

    public ExamSubmitResponse calculate(Exam exam) {
        AtomicLong mark = new AtomicLong(0);
        AtomicLong maxMark = new AtomicLong(0);
        exam.getQuestions().forEach(question -> {
            String userAnswer = question.getUserAnswer();
            question.getOptions().forEach(questionOption -> {
                if(userAnswer.equals(questionOption.getOptionText()) && questionOption.isCorrect())
                    mark.addAndGet(question.getQuestionMark());
            });
            maxMark.addAndGet(question.getQuestionMark());
        });
        return new ExamSubmitResponse(mark.get(), maxMark.get());
    }

    public List<Exam> getAll() {
        return examRepository.findAll();
    }
}
