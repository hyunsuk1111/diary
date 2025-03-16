package com.example.diary.user.service;

import com.example.diary.jwt.JwtTokenUtil;
import com.example.diary.user.domain.User;
import com.example.diary.user.dto.LoginRequest;
import com.example.diary.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return JwtTokenUtil.generateToken(user.getEmail());
    }
}
