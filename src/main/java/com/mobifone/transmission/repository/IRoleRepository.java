package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.Role;
import com.mobifone.transmission.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface IRoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findRoleByName(String name);

}
