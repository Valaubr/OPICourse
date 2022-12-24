package ru.valaubr.tasktracker.model.requests;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
