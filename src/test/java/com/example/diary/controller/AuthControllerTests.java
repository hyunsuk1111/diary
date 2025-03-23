package com.example.diary.controller;

import com.example.diary.user.domain.User;
import com.example.diary.user.dto.LoginRequest;
import com.example.diary.user.dto.UserDTO;
import com.example.diary.user.repository.UserRepository;
import com.example.diary.user.service.AuthService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository; // UserRepository 주입 (DB에 접근 가능)

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

    @Test
    public void register() throws Exception {
        UserDTO userDTO = new UserDTO("test4@gmail.com", "nickname", "1234");

        authService.register(userDTO);

        // then
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail()); // DB에서 사용자 조회
        assertTrue(user.isPresent()); // 사용자가 DB에 저장되었는지 확인
        assertEquals(userDTO.getEmail(), user.get().getEmail()); // 이메일이 제대로 저장되었는지 확인
    }

    @Test
    public void registerFail() throws Exception {
        UserDTO userDTO = new UserDTO("test3@gmail.com", "nickname", "1234");

        // then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authService.register(userDTO);
        });

        assertTrue(exception.getMessage().contains("Email is already registered"));
    }
}
