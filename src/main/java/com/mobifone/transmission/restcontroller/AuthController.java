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
import com.mobifone.transmission.security.CustomUserDetailsService;
import com.mobifone.transmission.security.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private CustomUserDetailsService userDetailsService;

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
        // cookie.setSecure(true);                  
        response.addCookie(cookie);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        // Tạo cookie mới với cùng tên nhưng có thời gian sống = 0
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setPath("/"); // Đảm bảo cookie có cùng path với cookie hiện tại
        cookie.setHttpOnly(true); // Đặt cookie là HTTP-only
        // cookie.setSecure(true); // Chỉ gửi cookie qua HTTPS (nếu cần)
        cookie.setMaxAge(0); // Xóa cookie

        // Thêm cookie vào phản hồi
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
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

    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@CookieValue(value = "refreshToken") String refreshToken, HttpServletRequest request, HttpServletResponse response) {
                jwtUtils.validateToken(refreshToken); // validate token, nếu có lỗi thì handler sẽ xử lý ngoại lệ trả về FE            
        
        Authentication authentication = jwtUtils.getAuthenticationFromJwt(refreshToken);
        String newAccessToken = jwtUtils.generateToken(authentication);
        String newRefreshToken = jwtUtils.generateRefreshToken(authentication);                
     
        Cookie cookie = new Cookie("refreshToken", newRefreshToken);
        cookie.setHttpOnly(true);        
        cookie.setPath("/");
        cookie.setMaxAge(30*24*60*60);   
        // cookie.setSecure(true);                  
        response.addCookie(cookie);
        return ResponseEntity.ok(new AuthResponseDTO(newAccessToken));
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
