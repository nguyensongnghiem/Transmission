package com.mobifone.transmission.service;

import com.mobifone.transmission.dto.inf.LeaseLineViewDTO;
import com.mobifone.transmission.model.LeaseLine;

import java.util.List;

public interface ILeaseLineService {
    public List<LeaseLine> findAll();
    public boolean save(LeaseLine leaseLine);
    public void deleteById(int id);

    public LeaseLine findById(int id);
    public <T> T findById(int id,Class<T> classType);
    public boolean update(LeaseLine leaseLine);

    List<LeaseLineViewDTO> findBy(Class<LeaseLineViewDTO> leaseLineViewDTOClass);
}
