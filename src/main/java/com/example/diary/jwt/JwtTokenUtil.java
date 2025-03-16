package com.example.diary.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final String SECRET_KEY = loadSecretKey();
    private static final long EXPIRATION_TIME = 86400000; //하루

    private static String loadSecretKey() {
        try {
            Path path = Paths.get("src/main/resources/secret-key.txt");
            return Files.readString(path).trim();
        } catch (Exception e) {
            throw new RuntimeException("Unable to load secret key from file", e);
        }
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

    public static String generateToken(String email) {
        long currentTimeMillis = System.currentTimeMillis();
        Date expirationDate = new Date(currentTimeMillis + EXPIRATION_TIME);  // 만료 시간 설정

        return Jwts.builder()
                .setSubject(email)  // 이메일
                .setIssuedAt(new Date(currentTimeMillis))  // 발행 시간
                .setExpiration(expirationDate)  // 만료 시간
                .signWith(SignatureAlgorithm.HS256, getSecretKey())  // 서명 (비밀키 사용)
                .compact();  // 최종 토큰 생성
    }

    public static boolean validateToken(String token, String email) {
        return (email.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
    }

    // JWT 토큰에서 사용자 정보 추출 (subject)
    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())  // 서명 검증용 비밀키 설정
                .parseClaimsJws(token)  // 토큰을 파싱해서 클레임 가져오기
                .getBody()
                .getSubject();  // 사용자명 (subject) 반환
    }

    // 토큰 만료 여부 체크
    public static boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(getSecretKey())  // 서명 검증용 비밀키 설정
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();  // 만료 시간 가져오기
        return expiration.before(new Date());  // 만료된 토큰인지 확인
    }
}
