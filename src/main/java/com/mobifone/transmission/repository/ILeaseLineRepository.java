package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.LeaseLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILeaseLineRepository extends JpaRepository<LeaseLine,Integer> {
    <T> List<T> findBy(Class<T> classType);
    <T> T findById(Integer id,Class<T> classType);

}
