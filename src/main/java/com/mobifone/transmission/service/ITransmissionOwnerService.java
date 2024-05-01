package com.mobifone.transmission.service;

import com.mobifone.transmission.model.SiteTransmissionType;
import com.mobifone.transmission.model.TransmissionOwner;

import java.util.List;

public interface ITransmissionOwnerService {
    public List<TransmissionOwner> findAll();
    public boolean save(TransmissionOwner transmissionOwner);
}
