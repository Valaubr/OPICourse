package ru.valaubr.tasktracker.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_users",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "api_user_id"))
    private List<ApiUser> customers;

    public Projects(String name) {
        this.name = name;
    }
}
