package com.example.diary.user.service;

import com.example.diary.user.dto.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}
