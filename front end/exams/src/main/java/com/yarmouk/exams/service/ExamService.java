package com.yarmouk.exams.service;

import com.yarmouk.exams.model.*;
import com.yarmouk.exams.repository.ExamRepository;
import com.yarmouk.exams.contoller.response.ExamSubmitResponse;
import com.yarmouk.exams.repository.UserExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final UserExamRepository userExamRepository;

    public ExamService(ExamRepository examRepository, UserExamRepository userExamRepository) {
        this.examRepository = examRepository;
        this.userExamRepository = userExamRepository;
    }

    public Optional<Exam> get(Long id) {
        return examRepository.findById(id);
    }

    public void save(Exam exam) {
        examRepository.save(exam);
    }

    public void calculate(Exam submission, Long userId) {
        AtomicLong mark = new AtomicLong(0);
        AtomicLong maxMark = new AtomicLong(0);
        Exam exam = examRepository.findById(submission.getId()).get();
        calculateMultiChoice(submission, mark, maxMark, exam);
        calculateTextQuestions(submission, exam, mark, maxMark);
        UserExam userExam = new UserExam();
        userExam.setExam(submission);
        userExam.setUserId(userId);
        userExam.setUserMark(mark.longValue());
        userExamRepository.save(userExam);
    }

    private static void calculateTextQuestions(Exam submission, Exam exam, AtomicLong mark, AtomicLong maxMark) {
        List<TextQuestion> textQuestions = submission.getTextQuestions();
        List<TextQuestion> answers = exam.getTextQuestions();
        for(int index = 0; index < textQuestions.size(); index++) {
            TextQuestion question = textQuestions.get(index);
            if(question.getAnswer().equals(answers.get(index).getAnswer()))
                mark.addAndGet(question.getQuestionMark());
            maxMark.addAndGet(question.getQuestionMark());
        }
    }

    private static void calculateMultiChoice(Exam submission, AtomicLong mark, AtomicLong maxMark, Exam exam) {
        List<MultiChoiceQuestion> multiChoiceQuestions = submission.getMultiChoiceQuestions();
        List<MultiChoiceQuestion> answers = exam.getMultiChoiceQuestions();
        for(int index = 0; index < multiChoiceQuestions.size(); index++) {
            MultiChoiceQuestion userAnswer = multiChoiceQuestions.get(index);
            MultiChoiceQuestion answer = answers.get(index);
            for (int option = 0; option < userAnswer.getOptions().size(); option++) {
                QuestionOption userOption = userAnswer.getOptions().get(option);
                QuestionOption questionOption = answer.getOptions().get(option);
                if(userOption.isCorrect() && questionOption.isCorrect()) {
                    mark.addAndGet(answer.getQuestionMark());
                }
            }
            maxMark.addAndGet(answer.getQuestionMark());
        }
    }

    public List<Exam> getAll() {
        return examRepository.findAll();
    }

    public Exam emptyAnswers(Exam exam) {
        List<TextQuestion> textQuestions = exam.getTextQuestions();
        textQuestions.forEach(textQuestion -> textQuestion.setAnswer(""));
        exam.setTextQuestions(textQuestions);
        List<MultiChoiceQuestion> multiChoiceQuestions = exam.getMultiChoiceQuestions();
        multiChoiceQuestions.forEach(multiChoiceQuestion -> {
            List<QuestionOption> options = multiChoiceQuestion.getOptions();
            options.forEach(option -> option.setCorrect(false));
            multiChoiceQuestion.setOptions(options);
        });
        exam.setMultiChoiceQuestions(multiChoiceQuestions);
        return exam;
    }
}
