package com.mobifone.transmission.service;

import com.mobifone.transmission.model.LeaseLineConnectType;

import java.util.List;

public interface ILeaseLineConnectTypeService {
    public List<LeaseLineConnectType> findAll();
    public boolean save(LeaseLineConnectType leaseLineConnectType);
}
