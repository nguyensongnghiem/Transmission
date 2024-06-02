package com.mobifone.transmission.service;

import com.mobifone.transmission.model.FoContract;

import java.util.List;

public interface IFoContractService {
    public List<FoContract> findFirst10ByOrderByEndDateDesc();
    public List<FoContract> findContractEndIn5Month();
}
