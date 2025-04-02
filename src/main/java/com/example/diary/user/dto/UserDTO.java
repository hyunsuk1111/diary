package com.example.diary.user.dto;

import com.example.diary.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String nickName;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String email, String nickName, String password) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

    public UserDTO(Long id, String email, String nickName) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
    }
}
