package com.sushil.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtUtil {

    private static final String SECRET = "mysupersecretkeymysupersecretkey";
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String extractEmail(String token) {
        return getClaims(token).getSubject();
    }
    public static Boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }



}
