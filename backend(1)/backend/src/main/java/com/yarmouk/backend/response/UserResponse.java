package com.yarmouk.backend.response;

import com.yarmouk.backend.model.User;
import lombok.Data;

@Data
public class UserResponse {
    private String email;
    private String firstName;
    private String lastName;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
