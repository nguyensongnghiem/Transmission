package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.User;
import com.mobifone.transmission.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole,Integer> {
    public List<UserRole> findUserRoleByUser(User user);
}
