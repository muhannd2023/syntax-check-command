package com.yarmouk.exams.contoller.response;

import com.yarmouk.exams.model.User;
import com.yarmouk.exams.model.UserExam;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<UserExam> userExams;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userExams = user.getUserExams();
    }
}
