package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.TelecomCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ITelecomCenterRepository extends JpaRepository<TelecomCenter,Integer> {
    <T> List<T> findBy(Class<T> classType);

}