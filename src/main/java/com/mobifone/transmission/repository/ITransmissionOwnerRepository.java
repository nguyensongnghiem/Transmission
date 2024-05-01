package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.TransmissionOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransmissionOwnerRepository extends JpaRepository<TransmissionOwner,Integer> {
}
