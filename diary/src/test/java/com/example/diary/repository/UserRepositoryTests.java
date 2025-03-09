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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // 테스트 전에 실행할 DB 초기화 작업
        //userRepository.deleteAll();
        //entityManager.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
    }

    @Test
    @Transactional
    @Rollback(false)
    void save() {
        //given
        User user = User.builder()
                .email("test1@gmail.com")
                .nickName("nickname")
                .password("1234")
                .diaries(null)
                .build();

        userRepository.save(user);

        //when
        User savedUser = userRepository.findById(user.getId()).get();

        //then
        assertThat(user.getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Test
    @Transactional
    void getDiaries() {
        // given
        Optional<User> user = userRepository.findById(1L);

        // when
        user.ifPresent(u -> {
            u.getDiaries().forEach(diary -> {
                System.out.println("title : " + diary.getTitle());
                System.out.println("content : " + diary.getContent());
                System.out.println("written by: " + u.getNickName());
            });
        });
    }
}
