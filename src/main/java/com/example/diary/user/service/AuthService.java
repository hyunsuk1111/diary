package com.example.diary.user.service;

import com.example.diary.user.domain.User;
import com.example.diary.user.dto.LoginRequest;
import com.example.diary.user.dto.UserDTO;

public interface AuthService {
    String login(LoginRequest loginRequest);
    void register(UserDTO userDTO);
    User update(Long id, UserDTO userDTO);
    void delete(Long id);
    User getUserInfo(String email);

}
