package ru.valaubr.tasktracker.pojo;

import lombok.Data;

@Data
public class GetTaskIDUserId {
    private Long taskId;
    private Long userId;
}
