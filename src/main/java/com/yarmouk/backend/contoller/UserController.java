package com.yarmouk.backend.contoller;

import com.yarmouk.backend.model.User;
import com.yarmouk.backend.request.LoginRequest;
import com.yarmouk.backend.request.RegisterRequest;
import com.yarmouk.backend.response.LoginResponse;
import com.yarmouk.backend.response.RegisterResponse;
import com.yarmouk.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return login.getStatusCode() == "200" ? new ResponseEntity<>(login, null, HttpStatus.ACCEPTED) : new ResponseEntity<>(login, null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id) {
        return userService.get(id);
    }

}
