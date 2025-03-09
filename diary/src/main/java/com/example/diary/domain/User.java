package com.example.diary.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity{

    @Builder
    public User(Long id, String email, String nickName, String password, List<Diary> diaries) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.diaries = diaries == null ? new ArrayList<>() : diaries;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    /*사용자의 게시글*/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Diary> diaries = new ArrayList<>();
}
