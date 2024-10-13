package com.mobifone.transmission.security;

import com.mobifone.transmission.model.UserEntity;
import com.mobifone.transmission.model.UserRole;
import com.mobifone.transmission.repository.IUserRepository;
import com.mobifone.transmission.repository.IUserRoleRepository;
import com.mobifone.transmission.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username không tồn tại"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));
    }
    public Collection<GrantedAuthority> getAuthorities(UserEntity user) {
        List<UserRole> userRoles = userRoleRepository.findUserRoleByUser(user);
        List<GrantedAuthority> authorities = userRoles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName())).collect(Collectors.toList());
        return authorities;
    }
}
