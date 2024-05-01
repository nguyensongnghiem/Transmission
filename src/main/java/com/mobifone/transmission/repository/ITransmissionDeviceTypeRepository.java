package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.TransmissionDeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransmissionDeviceTypeRepository extends JpaRepository<TransmissionDeviceType,Integer> {
}
