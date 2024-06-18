package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserHistoryRepository extends JpaRepository<UserHistory,Integer>{
}
