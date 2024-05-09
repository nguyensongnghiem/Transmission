package com.mobifone.transmission.service;

import com.mobifone.transmission.model.LeaseLine;
import com.mobifone.transmission.model.Router;

import java.util.List;

public interface ILeaseLineService {
    public List<LeaseLine> findAll();
    public boolean save(LeaseLine leaseLine);
    public void deleteById(int id);

    public LeaseLine findById(int id);
    public boolean update(LeaseLine leaseLine);
}
