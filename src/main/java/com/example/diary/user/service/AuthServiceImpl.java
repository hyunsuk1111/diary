package com.example.diary.user.service;

import com.example.diary.jwt.JwtTokenUtil;
import com.example.diary.user.domain.User;
import com.example.diary.user.dto.LoginRequest;
import com.example.diary.user.dto.UserDTO;
import com.example.diary.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return JwtTokenUtil.generateToken(user.getEmail());
    }

    @Override
    @Transactional
    public void register(UserDTO userDTO) {
        if (isEmailUnique(userDTO)) {
            User user = User.builder()
                    .email(userDTO.getEmail())
                    .nickName(userDTO.getNickName())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();

            userRepository.save(user);
        }
    }

    private boolean isEmailUnique(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        return true;
    }
}
