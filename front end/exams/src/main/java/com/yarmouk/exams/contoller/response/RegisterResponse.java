package com.yarmouk.exams.contoller.response;

import lombok.Data;

@Data
public class RegisterResponse {
    private final String statusCode;
    private final String message;
    private final UserResponse user;
}
