package ru.valaubr.tasktracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.valaubr.tasktracker.entities.Task;
import ru.valaubr.tasktracker.pojo.GetProjectIdFromReqBody;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query("select t.id, t.name, t.user.id, t.status from Task t where t.project.id = :project")
    List findTaskInProjectByUserId(@Param("project") Long project);
}
