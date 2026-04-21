package vn.hblab.training.tracker.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import vn.hblab.training.tracker.application.security.TokenGenerator;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil implements TokenGenerator {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public String generate(String userName) {
        return Jwts.builder()
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getKey())
                .compact();
    }

    // Lấy username từ token (dùng cho filter sau này)
    public String extractUserName(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Kiểm tra token hợp lệ (dùng cho filter sau này)
    public boolean isValid(String token, String userName) {
        try {
            return extractUserName(token).equals(userName);
        } catch (Exception e) {
            return false;
        }
    }
}

