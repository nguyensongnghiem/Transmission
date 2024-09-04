package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.TransmissionOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransmissionOwnerRepository extends JpaRepository<TransmissionOwner,Integer> {
    <T> List<T> findBy(Class<T> classType);
}
