package ru.valaubr.tasktracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.valaubr.tasktracker.entities.Projects;

import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Projects, Long> {
    Optional<Projects> findByName(String name);
}
