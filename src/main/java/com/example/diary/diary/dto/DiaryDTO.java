package com.example.diary.diary.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DiaryDTO {

    private String title;
    private String content;
    private LocalDate diaryDate;
}
