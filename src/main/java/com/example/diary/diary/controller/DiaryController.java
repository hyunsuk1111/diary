package com.example.diary.diary.controller;

import com.example.diary.diary.domain.Diary;
import com.example.diary.diary.dto.DiaryDTO;
import com.example.diary.diary.service.DiaryService;
import com.example.diary.jwt.JwtTokenUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/write")
    public ResponseEntity writeDiary(@RequestBody DiaryDTO diaryDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer", "");
        String email = JwtTokenUtil.getUsernameFromToken(token);

        return ResponseEntity.ok("good");
    }
}
