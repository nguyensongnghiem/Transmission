package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.UserEntity;
import com.mobifone.transmission.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobifone.transmission.repository.IUserRepository;
import com.mobifone.transmission.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void addUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity login(UserEntity user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public boolean isEmailExist(String email) {
        List<UserEntity> users = userRepository.findByEmailEqualsIgnoreCase(email);
        return !users.isEmpty();
    }

    @Override
    public boolean isUserActivated(String name) {
        UserEntity foundUser = userRepository.findUserByUsername(name).get();
        if (foundUser !=null){
        return foundUser.getState()== State.ACTIVE;
        }
        return false;
    }
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findUserByUsername(username).get();
    }


}
