package ru.valaubr.tasktracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valaubr.tasktracker.model.requests.UserCreateRequest;
import ru.valaubr.tasktracker.services.UserService;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    public String login() {
        return "Hell`o rest world";
    }

    public ResponseEntity createUser(@RequestBody UserCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest);
        return ResponseEntity.ok().build();
    }
}
