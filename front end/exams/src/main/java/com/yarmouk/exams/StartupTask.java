package com.yarmouk.exams;

import com.yarmouk.exams.contoller.request.RegisterRequest;
import com.yarmouk.exams.model.User;
import com.yarmouk.exams.repository.UserRepository;
import com.yarmouk.exams.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StartupTask implements ApplicationRunner {

    private final UserService userService;
    private final UserRepository userRepository;

    public StartupTask(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Optional<User> admin = userRepository.findByEmail("admin");
        if(admin.isPresent())
            return;
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("admin");
        registerRequest.setFirstName("System");
        registerRequest.setLastName("Admin");
        registerRequest.setPassword("P@ssw0rd");
        userService.register(registerRequest);
        System.out.println("Executing startup task...");
    }
}