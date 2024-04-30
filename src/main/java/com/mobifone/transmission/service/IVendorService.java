package com.mobifone.transmission.service;

import com.mobifone.transmission.model.Vendor;

import java.util.List;

public interface IVendorService {
    public List<Vendor> findAll();
    public boolean save(Vendor vendor);
}
