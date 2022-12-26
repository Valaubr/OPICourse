package ru.valaubr.tasktracker.pojo;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
