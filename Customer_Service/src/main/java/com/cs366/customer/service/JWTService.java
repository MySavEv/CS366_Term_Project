package com.cs366.customer.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;  // ใช้ค่า secret จาก application.properties

    // @Value("${jwt.expirationMs}")
    private long expirationTime = 360000;  // เวลา expiration ของ JWT (เช่น 1 ชั่วโมง)

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // สร้าง Token จาก userId
    public String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // userId จะเป็น subject
                .setIssuedAt(new Date()) // เวลาที่ token ถูกสร้าง
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // เวลา expiration
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ตรวจสอบว่า Token ถูกต้องหรือไม่
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false; // expired, malformed, signature invalid, etc.
        }
    }

    // ดึงข้อมูล userId จาก token
    public long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}