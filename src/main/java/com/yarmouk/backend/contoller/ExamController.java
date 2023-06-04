package com.yarmouk.backend.contoller;

import com.yarmouk.backend.model.Exam;
import com.yarmouk.backend.response.ExamSubmitResponse;
import com.yarmouk.backend.service.ExamService;
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

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getAll() {
        List<Exam> exams = examService.getAll();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/exams/{id}")
    public ResponseEntity<Exam> get(@PathVariable("id") Long id) {
        Optional<Exam> exam = examService.get(id);
        return exam.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Exam(), HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public void save(@RequestBody Exam exam) {
        examService.save(exam);
    }

    @PostMapping("/submit")
    public ExamSubmitResponse submit(@RequestBody Exam exam) {
        return examService.calculate(exam);
    }

}
