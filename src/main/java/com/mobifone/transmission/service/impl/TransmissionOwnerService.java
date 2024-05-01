package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.TransmissionOwner;
import com.mobifone.transmission.repository.ITransmissionOwnerRepository;
import com.mobifone.transmission.service.ITransmissionOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransmissionOwnerService implements ITransmissionOwnerService {
@Autowired
private ITransmissionOwnerRepository transmissionOwnerRepository;
    @Override
    public List<TransmissionOwner> findAll() {
        return transmissionOwnerRepository.findAll();
    }

    @Override
    public boolean save(TransmissionOwner transmissionOwner) {
        return transmissionOwnerRepository.save(transmissionOwner)!=null;
    }
}
