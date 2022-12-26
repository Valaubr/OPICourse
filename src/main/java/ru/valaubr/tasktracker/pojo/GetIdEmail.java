package ru.valaubr.tasktracker.pojo;

import lombok.Data;

@Data
public class GetIdEmail {
    private Long projectId;
    private String userEmail;
}
