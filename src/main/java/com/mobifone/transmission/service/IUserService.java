package com.mobifone.transmission.service;


import com.mobifone.transmission.model.UserEntity;


public interface IUserService {    
    public void addUser(UserEntity user);
    public UserEntity login(UserEntity user);
    public UserEntity findByUsername(String username);
    public boolean isEmailExist(String email);
    public boolean isUserActivated(String name);
    public void deleteById(Integer id) ;
}
