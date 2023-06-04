package com.yarmouk.exams.contoller.response;

import com.yarmouk.exams.model.Session;
import com.yarmouk.exams.model.User;
import lombok.Data;

@Data
public class LoginResponse {

    private final String statusCode;
    private final String message;
    private Session session;
    private UserResponse user;

    public LoginResponse(String statusCode, String message, Session session, User user) {
        this.statusCode = statusCode;
        this.message = message;
        this.user = new UserResponse(user);
    }

    public LoginResponse(String statusCode, String message, Session session) {
        this.statusCode = statusCode;
        this.message = message;
        this.session = session;
    }

    public static LoginResponse build(boolean loggedIn, Session session) {
        return loggedIn ? new LoginResponse("200", "Logged In Successfully", session)
                : new LoginResponse("400", "Wrong Credentials", null);
    }
}
