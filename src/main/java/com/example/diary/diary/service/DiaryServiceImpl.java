package com.example.diary.diary.service;

import com.example.diary.diary.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService{

    private final DiaryRepository diaryRepository;

    @Autowired
    public DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }
}
