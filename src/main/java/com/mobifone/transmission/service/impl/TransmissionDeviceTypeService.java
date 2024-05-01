package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.TransmissionDeviceType;
import com.mobifone.transmission.repository.ITransmissionDeviceTypeRepository;
import com.mobifone.transmission.service.ITransmissionDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransmissionDeviceTypeService implements ITransmissionDeviceTypeService {
    @Autowired
    ITransmissionDeviceTypeRepository transmissionDeviceTypeRepository;
    @Override
    public List<TransmissionDeviceType> findAll() {
        return transmissionDeviceTypeRepository.findAll();
    }

    @Override
    public boolean save(TransmissionDeviceType transmissionDeviceType) {
        return transmissionDeviceTypeRepository.save(transmissionDeviceType)!=null;
    }
}
