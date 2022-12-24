package ru.valaubr.tasktracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.valaubr.tasktracker.entities.ApiUser;
import ru.valaubr.tasktracker.model.requests.UserCreateRequest;
import ru.valaubr.tasktracker.repos.UserRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public ApiUser readUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void createUser(UserCreateRequest userCreateRequest) {
        ApiUser apiUser = new ApiUser();
        Optional<ApiUser> isUserExist = Optional.ofNullable(userRepo.findByEmail(userCreateRequest.getEmail()));
        if (isUserExist.isPresent()) {
            throw new RuntimeException("User already registered. Please use different username.");
        }
        apiUser.setEmail(userCreateRequest.getEmail());
        apiUser.setFirstName(userCreateRequest.getFirstName());
        apiUser.setLastName(userCreateRequest.getLastName());
        apiUser.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        userRepo.save(apiUser);
    }
}
