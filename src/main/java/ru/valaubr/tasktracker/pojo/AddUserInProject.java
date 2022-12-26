package ru.valaubr.tasktracker.pojo;

import lombok.Data;

@Data
public class AddUserInProject {
    private Long projectId;
    private String userEmail;
}
