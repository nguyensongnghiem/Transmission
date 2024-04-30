package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.Vendor;
import com.mobifone.transmission.repository.IVendorRepository;
import com.mobifone.transmission.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService implements IVendorService {
    @Autowired
    private IVendorRepository vendorRepository;
    @Override
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @Override
    public boolean save(Vendor vendor) {
        return vendorRepository.save(vendor)!=null;
    }
}
