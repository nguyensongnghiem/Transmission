package com.mobifone.transmission.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime()+ SecurityConstants.JWT_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        return token;
    }
    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        }
        catch(Exception e){
            throw new BadCredentialsException("Token không hợp lệ");
        }
    }
}
