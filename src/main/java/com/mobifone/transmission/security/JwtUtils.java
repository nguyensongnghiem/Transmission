package com.mobifone.transmission.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class JwtUtils {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        HashMap<String, Object> claims = new HashMap<>();
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        claims.put("roles", roles);
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        return token;
    }

    public String generateRefreshToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.REFRESH_EXPIRATION);
        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        return refreshToken;
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromJwt(String token) {
        return extractAllClaims(token).getSubject();
    }
    public Authentication getAuthenticationFromJwt(String token) {
        String username = getUsernameFromJwt(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, null,  userDetails.getAuthorities());

    }

    public Boolean validateToken(String token) {    
            Claims claims = extractAllClaims(token);
            return true;                
    }

    public static Key getKey() {
        return key;
    }
}
