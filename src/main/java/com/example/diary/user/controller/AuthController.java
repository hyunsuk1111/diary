package com.example.diary.user.controller;

import com.example.diary.jwt.JwtTokenUtil;
import com.example.diary.user.dto.JwtResponse;
import com.example.diary.user.dto.LoginRequest;
import com.example.diary.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
