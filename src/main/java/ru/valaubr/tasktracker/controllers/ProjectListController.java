package ru.valaubr.tasktracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.tasktracker.configs.StringHandler;
import ru.valaubr.tasktracker.pojo.AddUserInProject;
import ru.valaubr.tasktracker.pojo.CreateProjectRequest;
import ru.valaubr.tasktracker.services.ProjectService;

import java.util.List;

@RestController
public class ProjectListController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/api/projects")
    public List getProjectsToUser() {
        String userEmail = StringHandler.getToken(String.valueOf(SecurityContextHolder.getContext().getAuthentication()));
        return projectService.getAllProjectsForUser(userEmail);
    }

    @PostMapping("/api/projects")
    public ResponseEntity createProject(@RequestBody CreateProjectRequest createProjectRequest) {
        String userEmail = StringHandler.getToken(String.valueOf(SecurityContextHolder.getContext().getAuthentication()));
        projectService.createProject(userEmail, createProjectRequest.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/projects")
    public ResponseEntity addUserOnProject(@RequestBody AddUserInProject addUserInProject) {
        String userEmail = StringHandler.getToken(String.valueOf(SecurityContextHolder.getContext().getAuthentication()));
        projectService.addUserInProject(addUserInProject.getProjectId(), addUserInProject.getUserEmail(), userEmail);
        return ResponseEntity.ok().build();
    }
}
