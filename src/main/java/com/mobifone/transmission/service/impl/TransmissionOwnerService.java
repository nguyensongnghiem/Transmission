package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.TransmissionOwner;
import com.mobifone.transmission.repository.ITransmissionOwnerRepository;
import com.mobifone.transmission.service.ITransmissionOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransmissionOwnerService implements ITransmissionOwnerService {
@Autowired
private ITransmissionOwnerRepository transmissionOwnerRepository;
    @Override
    public List<TransmissionOwner> findAll() {
        return transmissionOwnerRepository.findAll();
    }

    @Override
    public <T> List<T> findBy(Class<T> clazz) {
        return transmissionOwnerRepository.findBy(clazz);
    }

    @Override
    public boolean save(TransmissionOwner transmissionOwner) {
        return transmissionOwnerRepository.save(transmissionOwner)!=null;
    }

    @Override
    public Optional<TransmissionOwner> findById(int id) {
        return transmissionOwnerRepository.findById(id);
    }
}
