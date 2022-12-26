package ru.valaubr.tasktracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.tasktracker.pojo.UserCreateRequest;
import ru.valaubr.tasktracker.services.security.UserService;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/signin")
    public ResponseEntity createUser(@RequestBody UserCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest);
        return ResponseEntity.ok().build();
    }
}
