package com.yarmouk.backend.service;

import com.yarmouk.backend.helper.StringHelper;
import com.yarmouk.backend.model.Session;
import com.yarmouk.backend.model.User;
import com.yarmouk.backend.repository.UserRepository;
import com.yarmouk.backend.request.LoginRequest;
import com.yarmouk.backend.request.RegisterRequest;
import com.yarmouk.backend.response.LoginResponse;
import com.yarmouk.backend.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final StringHelper stringHelper;

    public UserService(UserRepository userRepository, StringHelper stringHelper) {
        this.userRepository = userRepository;
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
        userRepository.save(new User(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(), Timestamp.valueOf(LocalDateTime.now())));
        return new RegisterResponse("200", "Registered");
    }

    public User get(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow();
    }
}
