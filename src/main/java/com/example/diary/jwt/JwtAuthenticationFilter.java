package com.example.diary.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");  // 요청 헤더에서 토큰을 가져옵니다.

        if (token != null && token.startsWith("Bearer ")) {  // Bearer로 시작하는지 확인
            token = token.substring(7);  // Bearer 이후의 실제 토큰을 가져옵니다.
            String email = JwtTokenUtil.getUsernameFromToken(token);  // 토큰에서 사용자 정보를 추출

            if (JwtTokenUtil.validateToken(token, email)) {  // 토큰이 유효한지 검증

                // 인증 객체 생성
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        email, null, null);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));  // 요청 세부 정보 추가
                SecurityContextHolder.getContext().setAuthentication(authentication);  // Spring Security의 인증 정보 설정
            }
        }
        chain.doFilter(request, response);  // 다음 필터로 요청을 전달
    }
}
