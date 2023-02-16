package ru.valaubr.tasktracker.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.valaubr.tasktracker.TaskTrackerApplication;
import ru.valaubr.tasktracker.pojo.UserCreateRequest;

@SpringBootTest
class AuthControllerTest {

    @BeforeAll
    public static void createAndLogin() throws InterruptedException {
        TaskTrackerApplication.main(new String[] {});
        Thread.sleep(15000);
    }

    @Test
    void createUserTest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmail("test@mail.com");
        userCreateRequest.setPassword("testPassword");
        userCreateRequest.setFirstName("Jhon");
        userCreateRequest.setLastName("Titor");
        Response request = RestAssured.given().contentType(ContentType.JSON).body(userCreateRequest).when().post("http://localhost:8080/api/signin");
        Assertions.assertTrue(request.getStatusCode() == 201 || request.getStatusCode() ==  500);
    }

    @Test
    String loginTest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmail("test@mail.com");
        userCreateRequest.setPassword("testPassword");
        Response request = RestAssured.given().contentType(ContentType.JSON).body(userCreateRequest).when().post("http://localhost:8080/login");
        Assertions.assertFalse(request.header("Authorization").isBlank());
        return request.header("Authorization");
    }
}