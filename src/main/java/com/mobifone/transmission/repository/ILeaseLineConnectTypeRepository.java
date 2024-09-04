package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.LeaseLineConnectType;
import com.mobifone.transmission.model.Vendor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ILeaseLineConnectTypeRepository extends JpaRepository<LeaseLineConnectType,Integer> {
    <T> List<T> findBy(Class<T> classType);
}
