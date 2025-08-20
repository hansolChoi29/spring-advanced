package org.example.expert.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.expert.domain.common.exception.ServerException;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final long TOKEN_TIME = 60 * 60 * 1000L; // 60분

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Long userId, String email, UserRole userRole) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(String.valueOf(userId))
                        .claim("email", email)
                        .claim("userRole", userRole)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date) // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                        .compact();
    }

    // TODO1-3: 유저 조회 시 유효하지 않은 JWT 라고 에러남
    public String substringToken(String tokenValue) {
//        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
//            return tokenValue.substring(BEARER_PREFIX.length()).trim();
//        }
        // 헤더가 비어 있을 때 명확한 예외
        if (!StringUtils.hasText(tokenValue)) {
            throw new IllegalArgumentException("JWT 토큰이 존재하지 않습니다.");
//            return tokenValue.substring(BEARER_PREFIX.length()).trim();
        }
        String token = tokenValue.trim();
        // Bearer가 2번 이상 붙어도 모두 제거,
        while (token.startsWith(BEARER_PREFIX)) {
            token = token.substring(BEARER_PREFIX.length()).trim();
        }
        // 접두사 제거 후에도 비어있으면 즉시 실패!
        if (!StringUtils.hasText(token)) {
            throw new IllegalArgumentException("JWT 토큰이 비어 있습니다.");
        }
        log.debug("JWT 토큰 추출 완료: {}", token);
        return token;
//        throw new ServerException("Not Found Token");
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("JWT 파싱 실패", token, e);
            throw e;
        }
    }
}
