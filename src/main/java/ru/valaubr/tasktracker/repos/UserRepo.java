package ru.valaubr.tasktracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.valaubr.tasktracker.entities.ApiUser;
@Repository
public interface UserRepo extends JpaRepository<ApiUser, String> {
    ApiUser findByEmail(String email);
}
