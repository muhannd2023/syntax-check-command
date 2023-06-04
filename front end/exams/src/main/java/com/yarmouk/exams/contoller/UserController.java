package com.yarmouk.exams.contoller;

import com.yarmouk.exams.model.User;
import com.yarmouk.exams.contoller.request.LoginRequest;
import com.yarmouk.exams.contoller.request.RegisterRequest;
import com.yarmouk.exams.contoller.response.LoginResponse;
import com.yarmouk.exams.contoller.response.RegisterResponse;
import com.yarmouk.exams.model.UserExam;
import com.yarmouk.exams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse login = userService.login(request);
        return Objects.equals(login.getStatusCode(), "200") ? new ResponseEntity<>(login, null, HttpStatus.ACCEPTED) : new ResponseEntity<>(login, null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id) {
        return userService.get(id);
    }

    @GetMapping("/exams/{id}")
    public List<UserExam> getExams(@PathVariable("id") Long id) {
        return userService.getExams(id);
    }

}
