package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.Router;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRouterRepository extends JpaRepository<Router,Long> {
    Router findRouterByName(String routerName);
    Router findRouterByIp(String ip);
    <T> List<T> findBy(Class<T> classType);
}
