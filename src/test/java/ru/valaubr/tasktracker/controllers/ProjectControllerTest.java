package ru.valaubr.tasktracker.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.valaubr.tasktracker.TaskTrackerApplication;
import ru.valaubr.tasktracker.pojo.CreateProjectRequest;
import ru.valaubr.tasktracker.pojo.GetIdName;

@SpringBootTest
class ProjectControllerTest {
    private static String token;

    @BeforeAll
    public static void createAndLogin() throws InterruptedException {
        AuthControllerTest authControllerTest = new AuthControllerTest();
        authControllerTest.createUserTest();
        token = authControllerTest.loginTest();
        System.out.println(token);
    }

    @Test
    void createProject() {
        CreateProjectRequest request = new CreateProjectRequest("Nmae");
        Response response = RestAssured
                .given().auth().oauth2(token)
                .contentType(ContentType.JSON).body(request)
                .when().post("http://localhost:8080/api/projects");
        Assertions.assertEquals(201, response.getStatusCode());
    }

    @Test
    void getProjectsToUser() {
        Response response = RestAssured
                .given().auth().oauth2(token).when().get("http://localhost:8080/api/projects");
        Assertions.assertFalse(response.print().isBlank());
    }

    @Test
    void addUserOnProject() {
    }

    @Test
    void getAllTask() {
    }

    @Test
    void changeStatus() {
    }

    @Test
    void changeUser() {
    }
}