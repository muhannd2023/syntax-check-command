package com.yarmouk.backend.response;

import lombok.Data;

@Data
public class RegisterResponse {
    private final String statusCode;
    private final String message;
}
