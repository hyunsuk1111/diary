package com.example.diary.user.controller;

import com.example.diary.user.domain.User;
import com.example.diary.user.dto.JwtResponse;
import com.example.diary.user.dto.LoginRequest;
import com.example.diary.user.dto.UserDTO;
import com.example.diary.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest);  // JWT 토큰 생성

            return ResponseEntity.ok(new JwtResponse(token));  // 생성한 토큰을 반환
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO userDTO) {
        try {
            authService.register(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred during registration");
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            User updatedUser = authService.update(id, userDTO);

            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            authService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    @GetMapping("/userInfo")
    public ResponseEntity<UserDTO> getUserInfo(Principal principal) {

        try {
            String email = principal.getName();

            User userInfo = authService.getUserInfo(email);

            UserDTO userDTO = new UserDTO(userInfo.getId(), userInfo.getEmail(), userInfo.getNickName());

            return ResponseEntity.ok(userDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
