package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.TransmissionDeviceType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransmissionDeviceTypeRepository extends JpaRepository<TransmissionDeviceType,Integer> {
    <T> List<T> findBy(Class<T> classType);
}
