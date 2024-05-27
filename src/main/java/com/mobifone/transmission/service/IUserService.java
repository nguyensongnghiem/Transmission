package com.mobifone.transmission.service;


import com.mobifone.transmission.model.User;


public interface IUserService {    
    public void addUser(User user);
    public User login(User user);
    public User findByUsername(String username);
    public boolean isEmailExist(String email);
    public boolean isUserActivated(String name);
    public void deleteById(Integer id) ;
}
