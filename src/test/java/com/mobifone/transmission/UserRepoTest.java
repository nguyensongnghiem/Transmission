package com.mobifone.transmission;

import com.mobifone.transmission.model.State;
import com.mobifone.transmission.model.User;
import com.mobifone.transmission.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepoTest {
    @Autowired
    IUserRepository userRepository;
    @Test
    public void addUser() {
        User user = new User("Nghiem","songnghiem@gmail.com","OX-1234524tg2", State.ACTIVE);
        userRepository.save(user);
        assertNotNull(userRepository.findById(user.getId()));

    }
}
