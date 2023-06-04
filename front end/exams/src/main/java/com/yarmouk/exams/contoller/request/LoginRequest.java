package com.yarmouk.exams.contoller.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
