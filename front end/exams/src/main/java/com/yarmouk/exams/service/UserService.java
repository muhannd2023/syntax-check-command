package com.yarmouk.exams.service;

import com.yarmouk.exams.contoller.response.UserResponse;
import com.yarmouk.exams.helper.StringHelper;
import com.yarmouk.exams.model.Session;
import com.yarmouk.exams.model.User;
import com.yarmouk.exams.model.UserExam;
import com.yarmouk.exams.repository.UserExamRepository;
import com.yarmouk.exams.repository.UserRepository;
import com.yarmouk.exams.contoller.request.LoginRequest;
import com.yarmouk.exams.contoller.request.RegisterRequest;
import com.yarmouk.exams.contoller.response.LoginResponse;
import com.yarmouk.exams.contoller.response.RegisterResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// TODO: there is something called delegate as a middleware between the logic and the controller layers to build Responses, so the service is not coupled with the requests and responses
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserExamRepository userExamRepository;
    private final StringHelper stringHelper;

    public UserService(UserRepository userRepository, UserExamRepository userExamRepository, StringHelper stringHelper) {
        this.userRepository = userRepository;
        this.userExamRepository = userExamRepository;
        this.stringHelper = stringHelper;
    }

    public LoginResponse login(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        boolean loggedIn = user.isPresent();
        if(loggedIn)
            loggedIn = stringHelper.equals(request.getPassword(), user.get().getPassword());
        if(!loggedIn)
            return LoginResponse.build(false, null);
        Session session = startSession(user.get().getId());
        return new LoginResponse("200", "Logged In", session, user.get());
    }

    private Session startSession(Long id) {
        return new Session(id);
    }

    public RegisterResponse register(RegisterRequest request) {
        request.setPassword(stringHelper.encode(request.getPassword()));
        User user = new User(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(), Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        return new RegisterResponse("200", "Registered", new UserResponse(user));
    }

    public User get(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow();
    }

    public List<UserExam> getExams(Long id) {
        return userExamRepository.findAllByUserId(id);
    }
}
