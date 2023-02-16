package ru.valaubr.tasktracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.tasktracker.configs.StringHandler;
import ru.valaubr.tasktracker.pojo.*;
import ru.valaubr.tasktracker.services.ProjectService;
import ru.valaubr.tasktracker.services.TaskService;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/api/projects")
    public List getProjectsToUser() {
        String userEmail = StringHandler.getToken(String.valueOf(SecurityContextHolder.getContext().getAuthentication()));
        return projectService.getAllProjectsForUser(userEmail);
    }

    @PostMapping("/api/projects")
    public ResponseEntity createProject(@RequestBody CreateProjectRequest createProjectRequest) {
        String userEmail = StringHandler.getToken(String.valueOf(SecurityContextHolder.getContext().getAuthentication()));
        projectService.createProject(userEmail, createProjectRequest.getName());
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/api/projects")
    public ResponseEntity addUserOnProject(@RequestBody GetIdEmail getIdEmail) {
        String userEmail = StringHandler.getToken(String.valueOf(SecurityContextHolder.getContext().getAuthentication()));
        projectService.addUserInProject(getIdEmail.getProjectId(), getIdEmail.getUserEmail(), userEmail);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/projects/task")
    public List getAllTask(@RequestBody GetProjectIdFromReqBody getProjectIdFromReqBody) {
        return taskService.getTasksForProject(getProjectIdFromReqBody.getId());
    }

    @PostMapping("/api/projects/task")
    public ResponseEntity createTask(@RequestBody GetIdName getIdName) {
        taskService.createTask(getIdName.getId(), getIdName.getName());
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/api/projects/task")
    public ResponseEntity changeStatus(@RequestBody GetIdStatus getIdStatus) {
        taskService.changeStatus(getIdStatus.getId(), getIdStatus.getStatus());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/projects/task/user")
    public ResponseEntity changeUser(@RequestBody GetTaskIDUserId getTaskIDUserId) {
        taskService.changeUser(getTaskIDUserId.getTaskId(), getTaskIDUserId.getUserId());
        return ResponseEntity.ok().build();
    }
}
