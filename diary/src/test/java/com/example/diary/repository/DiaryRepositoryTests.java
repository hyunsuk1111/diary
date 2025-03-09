package com.example.diary.repository;

import com.example.diary.domain.Diary;
import com.example.diary.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DiaryRepositoryTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        // 테스트 전에 실행할 DB 초기화 작업
        //diaryRepository.deleteAll();
        //entityManager.createNativeQuery("ALTER TABLE diaries AUTO_INCREMENT = 1").executeUpdate();
    }

    @Test
    //@Transactional
    //@Rollback(false)
    void save() {
        //given
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));;

        Diary diary = Diary.builder()
                .title("타이틀3")
                .content("성장하자3")
                .user(user)
                .build();

        diaryRepository.save(diary);

        //when
        Diary savedDiary = diaryRepository.findById(diary.getId()).get();

        //then
        assertThat(diary.getTitle()).isEqualTo(savedDiary.getTitle());
    }


}
