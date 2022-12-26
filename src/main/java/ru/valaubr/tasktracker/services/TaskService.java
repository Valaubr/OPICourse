package ru.valaubr.tasktracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valaubr.tasktracker.entities.ApiUser;
import ru.valaubr.tasktracker.entities.Projects;
import ru.valaubr.tasktracker.entities.Status;
import ru.valaubr.tasktracker.entities.Task;
import ru.valaubr.tasktracker.repos.ProjectRepo;
import ru.valaubr.tasktracker.repos.TaskRepo;
import ru.valaubr.tasktracker.repos.UserRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private ProjectRepo projectRepo;

    public List getTasksForProject(Long id) {
        return taskRepo.findTaskInProjectByUserId(id);
    }

    public void createTask(Long projectId, String name) {
        Task task = new Task();
        task.setName(name);
        task.setProject(projectRepo.findById(projectId).get());
        task.setStatus(Status.OPEN);
        taskRepo.save(task);
    }

    public void changeStatus(Long taskId, String status) {
        Task task = taskRepo.findById(taskId).get();
        task.setStatus(Status.valueOf(status));
        taskRepo.save(task);
    }

    public void changeUser(Long taskId, Long userId) {
        Task task = taskRepo.findById(taskId).get();
        Projects projects = projectRepo.findById(task.getProject().getId()).get();
        for (ApiUser u:projects.getCustomers()) {
            if(Objects.equals(u.getId(), taskId)) {
                task.setUser(u);
                taskRepo.save(task);
                return;
            }
        }
    }
}
