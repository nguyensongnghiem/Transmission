package com.mobifone.transmission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // cấu hình có thể logout
        http.csrf(AbstractHttpConfigurer::disable);
        // các đường dẫn không phải login
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/", "/login", "/logout", "/logoutSuccessful", "/403","/vendor/**" ).permitAll());
        // cấp quyền cho user
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/site/list").hasRole("USER"));

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/site/create").hasRole("ADMIN"));
        // cấp quyền cho user và admin
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/userInfo", "/blog/create").hasAnyRole("USER", "ADMIN"));
        // cấu hình form login
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/process-login") // đường dẫn trùng với url form login
                .defaultSuccessUrl("/site/list")//
                .failureUrl("/login")
                .usernameParameter("username")//trùng với tên trong form đăng nhập
                .passwordParameter("password")// trung với tên trong form đăng nhập
        );
        // cấu hình logout
        http.logout(form -> form.logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful"));

        // cấu hình remember-me : lưu trạng thái đăng nhập khi tắt trình duyệt => mở lại không cần login
//        http.rememberMe(remember -> remember.tokenRepository(persistentTokenRepository()));

        // cấu hình trả về trang 403 khi không có quyền (role) truy cập
        http.exceptionHandling(ex -> ex.accessDeniedPage("/403"));
        ;
        return http.build();

    }
}
