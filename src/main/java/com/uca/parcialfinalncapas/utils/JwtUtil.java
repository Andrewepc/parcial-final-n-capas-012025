package com.uca.parcialfinalncapas.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    // Se recomienda extraer la clave desde las propiedades de aplicación.
    private static final String SECRET_KEY = "claveSuperSecretaMuyLargaQueDebeSerMuySegura";
    private static final long EXPIRATION_MS = 600000;

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject(); // Suponiendo que el "subject" almacena el identificador del usuario
    }
    public static String generateToken(String subject, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(extraClaims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    // Puedes agregar más métodos para extraer otros datos como roles, expiración, etc.
}