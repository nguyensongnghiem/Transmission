package com.mobifone.transmission.service;

import com.mobifone.transmission.model.SiteTransmissionType;
import com.mobifone.transmission.model.TransmissionOwner;

import java.util.List;
import java.util.Optional;

public interface ITransmissionOwnerService {
    public List<TransmissionOwner> findAll();
    public boolean save(TransmissionOwner transmissionOwner);

    Optional<TransmissionOwner> findById(int transmissionOwnerId);
}
