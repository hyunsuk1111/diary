package com.example.diary.diary.repository;

import com.example.diary.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
}
