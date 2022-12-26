package ru.valaubr.tasktracker.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ApiUser user;

    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Projects project;
}
