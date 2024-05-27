package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobifone.transmission.model.User;
import com.mobifone.transmission.repository.IUserRepository;
import com.mobifone.transmission.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User login(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public boolean isEmailExist(String email) {
        List<User> users = userRepository.findByEmailEqualsIgnoreCase(email);
        return !users.isEmpty();
    }

    @Override
    public boolean isUserActivated(String name) {
        User foundUser = userRepository.findUserByUsername(name);
        if (foundUser!=null){
        return foundUser.getState()== State.ACTIVE;
        }
        return false;
    }
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


}
