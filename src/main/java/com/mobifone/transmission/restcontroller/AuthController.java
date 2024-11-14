package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.AuthResponseDTO;
import com.mobifone.transmission.dto.LoginDTO;
import com.mobifone.transmission.dto.RegisterDTO;
import com.mobifone.transmission.exception.ErrorResponse;
import com.mobifone.transmission.model.State;
import com.mobifone.transmission.model.UserEntity;
import com.mobifone.transmission.model.UserRole;
import com.mobifone.transmission.repository.IRoleRepository;
import com.mobifone.transmission.repository.IUserRepository;
import com.mobifone.transmission.repository.IUserRoleRepository;
import com.mobifone.transmission.security.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// @CrossOrigin(origins =  "http://localhost:5173/",allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateToken(authentication);
        String refreshToken = jwtUtils.generateRefreshToken(authentication);
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);        
        cookie.setPath("/");
        cookie.setMaxAge(30*24*60*60);  
            
        response.addCookie(cookie);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Username đã tồn tại");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setState(State.ACTIVE);
        UserEntity createdUser = userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setUser(createdUser);
        userRole.setRole(roleRepository.findRoleByName("ROLE_USER").get());
        userRoleRepository.save(userRole);
        return new ResponseEntity<>("Đã thêm mới user", HttpStatus.CREATED);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        try {
            jwtUtils.validateToken(token);
            return new ResponseEntity<>("Token hợp lệ", HttpStatus.OK);
        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
