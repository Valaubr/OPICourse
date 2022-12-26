package ru.valaubr.tasktracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.valaubr.tasktracker.entities.Projects;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Projects, Long> {
    Optional<Projects> findById(Long id);
    Optional<Projects> findByName(String name);
    @Query("select p.id, p.name from Projects p left join p.customers c where c.id = :user")
    List findAllByUserId(@Param("user") Long user);

    @Query("from Projects p left join p.customers c where c.id = :user")
    void insertUserInProject(@Param("user") Long user);
}
