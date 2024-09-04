package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.RouterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRouterTypeRepository extends JpaRepository<RouterType,Integer> {
    <T> List<T> findBy(Class<T> classType);
}
