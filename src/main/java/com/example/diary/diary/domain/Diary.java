package com.example.diary.diary.domain;

import com.example.diary.common.BaseEntity;
import com.example.diary.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diaries")
public class Diary extends BaseEntity {

    @Builder
    public Diary(Long id, String title, String content,LocalDate diaryDate, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.diaryDate = diaryDate;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate diaryDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
