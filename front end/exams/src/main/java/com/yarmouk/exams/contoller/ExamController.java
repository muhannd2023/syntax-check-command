package com.yarmouk.exams.contoller;

import com.yarmouk.exams.model.Exam;
import com.yarmouk.exams.contoller.response.ExamSubmitResponse;
import com.yarmouk.exams.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/exam")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Exam>> getAll() {
        List<Exam> exams = examService.getAll();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> get(@PathVariable("id") Long id) {
        Optional<Exam> exam = examService.get(id);
        return exam.map(value -> new ResponseEntity<>(examService.emptyAnswers(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Exam(), HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public void save(@RequestBody Exam exam) {
        examService.save(exam);
    }

    @PostMapping("/submit/{id}")
    public void submit(@RequestBody Exam exam, @PathVariable("id") Long userId) {
        examService.calculate(exam, userId);
    }

}
