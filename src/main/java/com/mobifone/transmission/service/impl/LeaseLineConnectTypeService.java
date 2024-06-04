package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.LeaseLineConnectType;
import com.mobifone.transmission.repository.ILeaseLineConnectTypeRepository;
import com.mobifone.transmission.service.ILeaseLineConnectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseLineConnectTypeService implements ILeaseLineConnectTypeService {
    @Autowired
    private ILeaseLineConnectTypeRepository leaseLineConnectTypeRepository;
    @Override
    public List<LeaseLineConnectType> findAll() {
        return leaseLineConnectTypeRepository.findAll();
    }

    @Override
    public boolean save(LeaseLineConnectType leaseLineConnectType) {
        return leaseLineConnectTypeRepository.save(leaseLineConnectType)!=null;
    }
}
