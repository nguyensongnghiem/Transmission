package com.mobifone.transmission.service;

import com.mobifone.transmission.model.RouterType;
import com.mobifone.transmission.model.TransmissionDeviceType;

import java.util.List;

public interface ITransmissionDeviceTypeService {
    public List<TransmissionDeviceType> findAll();
    public boolean save(TransmissionDeviceType transmissionDeviceType);
}
