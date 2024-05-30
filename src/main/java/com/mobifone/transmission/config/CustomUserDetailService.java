package com.mobifone.transmission.config;

import com.mobifone.transmission.model.User;
import com.mobifone.transmission.repository.IUserRoleRepository;
import com.mobifone.transmission.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    IUserService userService;
    @Autowired
    IUserRoleRepository userRoleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user ==null) throw new UsernameNotFoundException("Username not found");
        UserDetails uDetails = new CustomUserDetails(user,userRoleRepository);
        return uDetails;
    } 
}
