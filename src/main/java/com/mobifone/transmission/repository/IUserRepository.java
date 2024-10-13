package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface IUserRepository extends JpaRepository<UserEntity,Integer> {
    public List<UserEntity> findByEmailEqualsIgnoreCase(String email);
    Optional<UserEntity> findUserByUsername(String name);
    Boolean existsByUsername(String username);
}
