package ru.valaubr.tasktracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class MainPageController {
    @GetMapping
    public String mainPage() {
        return "Hello user";
    }
}
