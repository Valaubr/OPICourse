package ru.valaubr.tasktracker.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllProjectForUser {
    private Long id;
    private String name;
}
