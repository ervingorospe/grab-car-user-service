package com.ervingorospe.grab_user_service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {
    private String secret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false; // Token is expired
        } catch (JwtException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }
}
