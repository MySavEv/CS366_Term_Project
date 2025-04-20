package com.cs366.order.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secretKey;  // ใช้ค่า secret จาก application.properties

    @Value("${jwt.expirationMs}")
    private long expirationTime;  // เวลา expiration ของ JWT (เช่น 1 ชั่วโมง)

    // สร้าง Token จาก userId
    public String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // userId จะเป็น subject
                .setIssuedAt(new Date()) // เวลาที่ token ถูกสร้าง
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // เวลา expiration
                .compact();
    }

    // ตรวจสอบว่า Token ถูกต้องหรือไม่
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secretKey) // ใช้ secretKey ในการตรวจสอบ
                .parseClaimsJws(token); // ตรวจสอบ token ว่ามีความถูกต้องหรือไม่
            return true;
        } catch (Exception e) {
            return false; // ถ้ามี error ก็จะถือว่า invalid
        }
    }

    // ดึงข้อมูล userId จาก token
    public Long extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject()); // ใช้ subject เป็น userId
    }
}