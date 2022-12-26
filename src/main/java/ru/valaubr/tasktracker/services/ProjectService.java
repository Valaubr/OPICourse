package ru.valaubr.tasktracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valaubr.tasktracker.entities.ApiUser;
import ru.valaubr.tasktracker.entities.Projects;
import ru.valaubr.tasktracker.repos.ProjectRepo;
import ru.valaubr.tasktracker.repos.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectRepo projectRepo;
    @Autowired
    UserRepo userRepo;

    public List getAllProjectsForUser(String email) {
        ApiUser user = userRepo.findByEmail(email);
        return projectRepo.findAllByUserId(user.getId());
    }

    public void createProject(String email, String name) {
        ApiUser user = userRepo.findByEmail(email);
        Projects projects = new Projects();
        projects.setName(name);
        List<ApiUser> customer = new ArrayList<>();
        customer.add(user);
        projects.setCustomers(customer);
        projectRepo.save(projects);
    }

    public void addUserInProject(Long projectId, String userEmail, String admin) {
        if (isUserInProject(projectId, admin)){
            Optional<Projects> projects = projectRepo.findById(projectId);
            Projects project = projects.get();
            project.getCustomers().add(userRepo.findByEmail(userEmail));
            projectRepo.save(project);
        }
    }

    private boolean isUserInProject(Long projectId, String admin) {
        Optional<Projects> project = projectRepo.findById(projectId);
        List<ApiUser> list = project.get().getCustomers();
        for (ApiUser user : list) {
            if (user.getEmail().equals(admin)) {
                return true;
            }
        }
        return false;
    }
}
