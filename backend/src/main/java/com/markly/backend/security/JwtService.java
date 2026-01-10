package com.markly.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("marklyjwtsecretmarklyjwtsecretmarklyjwtsecret")
    private String jwtSecret;

    @Value("86400000")
    private long jwtExpiration;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return  Keys.hmacShaKeyFor(keyBytes);
    }

    // 1️⃣ Generate token
    public String generateToken(String email,String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 2️⃣ Extract email from token
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }
    // 3 Extract role
    public String extractRole(String token) {
        return getClaims(token).get("role",String.class);
    }

    // 3️⃣ Check if token is expired
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // 4️⃣ Internal helper
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
