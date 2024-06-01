package com.mobifone.transmission;

import com.mobifone.transmission.model.User;
import com.mobifone.transmission.model.State;
import com.mobifone.transmission.repository.IUserRepository;
import com.mobifone.transmission.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepoTest {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IUserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Test
    public void addUser() {
        String hashedPass = encoder.encode("nghiem");
        User user = new User("nghiem","nghiem@gmail.com",hashedPass,State.ACTIVE);
        userRepository.save(user);
        assertNotNull(userRepository.findById(user.getId()));
    }
    @Test
    public void addUserPending() {
        String hashedPass = encoder.encode("quang");
        User user = new User("quang","quang@gmail.com",hashedPass);
        userRepository.save(user);
        assertNotNull(userRepository.findById(user.getId()));
    }

    @Test
    public void isEmailExist() {
//        User user = new User("Nghiem","songnghiem@gmail.com","OX-1234524tg2", State.ACTIVE);
//        userRepository.save(user);
        assertTrue(userService.isEmailExist("cUong@gmail.com"));
        assertTrue(userService.isEmailExist("QUANG@Gmail.com"));
        assertFalse(userService.isEmailExist("NghiEM123@Gmail.com"));

    }
    @Test
    public void isUserActive() {
//        User user = new User("Nghiem","songnghiem@gmail.com","OX-1234524tg2", State.ACTIVE);
//        userRepository.save(user);
        assertTrue(userService.isUserActivated("Nghiem"));
        assertTrue(userService.isUserActivated("Cuong"));
        assertFalse(userService.isUserActivated("Quang"));
        assertFalse(userService.isUserActivated("Qusdfang"));


    }

}
