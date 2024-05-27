package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IUserRepository extends JpaRepository<User,Integer> {
    public List<User> findByEmailEqualsIgnoreCase(String email);
    public User findUserByUsername(String name);
}
