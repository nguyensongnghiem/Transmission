package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {

}
