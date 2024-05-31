package com.mobifone.transmission.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mobifone.transmission.model.Role;
import com.mobifone.transmission.model.User;
import com.mobifone.transmission.model.UserRole;
import com.mobifone.transmission.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


public class CustomUserDetails implements UserDetails {
    private User user;

    private IUserRoleRepository userRoleRepository;

    public CustomUserDetails(User user, IUserRoleRepository userRoleRepository) {
        super();
        this.user = user;
        this.userRoleRepository = userRoleRepository;
    }

    public CustomUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<UserRole> userRoles = userRoleRepository.findUserRoleByUser(user);
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRole().getName());
                grantedAuthorities.add(authority);
            }
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
