package ru.valaubr.tasktracker.entities;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ApiUser user;

    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
}
