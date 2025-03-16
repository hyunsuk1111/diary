package com.example.diary.controller;

import com.example.diary.user.dto.LoginRequest;
import com.example.diary.user.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Test
    public void login() throws Exception {
        LoginRequest loginRequest = new LoginRequest("test1@gmail.com", "1234");

        String token = authService.login(loginRequest);

        System.out.println("token = " + token);

        assertNotNull(token);
    }

    @Test
    public void loginFail() throws Exception {
        LoginRequest loginRequest = new LoginRequest("test3@gmail.com", "1234");

        assertThrows(RuntimeException.class, () -> authService.login(loginRequest));
    }
}
