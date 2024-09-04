package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.SiteTransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISiteTransmissionTypeRepository extends JpaRepository<SiteTransmissionType,Integer> {
    <T> List<T> findBy(Class<T> classType);
}
