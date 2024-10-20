package com.mobifone.transmission.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
            try {
                if (StringUtils.hasText(token) && jwtUtils.validateToken(token)) {
                    String username = jwtUtils.getUsernameFromJwt(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                filterChain.doFilter(request, response);
            }
            catch (JwtException e) {
                exceptionResolver.resolveException(request, response, null, e);
            }
            catch (Exception  e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
            }
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    private boolean noRequiresAuthentication(HttpServletRequest request) {
        // Kiểm tra xem request có phải là các endpoint yêu cầu token không
        String path = request.getRequestURI();
        // Ví dụ: Nếu các endpoint /api/** yêu cầu token, còn các endpoint khác không
        return path.startsWith("/api/auth") ;
    }

}
