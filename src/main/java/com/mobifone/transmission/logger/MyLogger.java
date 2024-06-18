package com.mobifone.transmission.logger;

import com.mobifone.transmission.model.User;
import com.mobifone.transmission.model.UserHistory;
import com.mobifone.transmission.repository.IUserHistoryRepository;
import com.mobifone.transmission.repository.IUserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Aspect
@Component
public class MyLogger {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserHistoryRepository userHistoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(MyLogger.class);

    @After(value = "execution(* com.mobifone.transmission.controller.SiteController.create(..))")
    public void createSite() {
        logger.info("New site created in DB");
    }

    @Before(value = "execution(* com.mobifone.transmission.controller.SiteController.edit(..))")
    public void editSite() {
        logger.info("Đã sửa thông tin site trong DB");

    }

    @AfterReturning(pointcut = "execution(* com.mobifone.transmission.config.CustomUserDetailService.loadUserByUsername(..))",
            returning = "userDetails")
    public void logUserLogin(JoinPoint joinPoint, UserDetails userDetails) {
        User user = userRepository.findUserByUsername(userDetails.getUsername());
        UserHistory userHistory = new UserHistory();
        userHistory.setUser(user);
        userHistoryRepository.save(userHistory);

        logger.info("Người dùng " + userDetails.getUsername() + " đã đăng nhập vào hệ thống");
    }
    @Before(value = "execution(* org.springframework.security.web.authentication.logout.LogoutSuccessHandler.onLogoutSuccess(..))")
    public void afterLogout(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Authentication authentication = (Authentication) args[2];
        String username = authentication.getName();
        logger.info("Người dùng " + username + " đã đăng xuất khỏi hệ thống");
    }
}
