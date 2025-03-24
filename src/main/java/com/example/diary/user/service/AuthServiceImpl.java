package com.example.diary.user.service;

import com.example.diary.jwt.JwtTokenUtil;
import com.example.diary.user.domain.User;
import com.example.diary.user.dto.LoginRequest;
import com.example.diary.user.dto.UserDTO;
import com.example.diary.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
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

    @Override
    @Transactional
    public User update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User updatedUser  = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickName(userDTO.getNickName() != null ? userDTO.getNickName() : user.getNickName())
                .password(userDTO.getPassword() != null ? passwordEncoder.encode(userDTO.getPassword()) : user.getPassword())
                .build();

        return userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public User getUserInfo(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    private boolean isEmailUnique(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        return true;
    }


}
