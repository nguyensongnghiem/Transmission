package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.LeaseLine;
import com.mobifone.transmission.model.Vendor;
import com.mobifone.transmission.repository.ILeaseLineRepository;
import com.mobifone.transmission.repository.IVendorRepository;
import com.mobifone.transmission.service.ILeaseLineService;
import com.mobifone.transmission.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseLineService implements ILeaseLineService {
    @Autowired
    private ILeaseLineRepository leaseLineRepository;
    @Override
    public List<LeaseLine> findAll() {
        return leaseLineRepository.findAll();
    }

    @Override
    public boolean save(LeaseLine leaseLine) {
        return leaseLineRepository.save(leaseLine)!=null;
    }

    @Override
    public void deleteById(int id) {
        leaseLineRepository.deleteById(id);
    }

    @Override
    public LeaseLine findById(int id) {
        return leaseLineRepository.findById(id).get();
    }

    @Override
    public boolean update(LeaseLine leaseLine) {
        return false;
    }
}