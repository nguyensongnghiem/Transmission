package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.repository.IFoContractRepository;
import com.mobifone.transmission.service.IFoContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class FoContractService implements IFoContractService {
    @Autowired private IFoContractRepository foContractRepository;
    @Override
    public List<FoContract> findFirst10ByOrderByEndDateDesc() {
        return foContractRepository.findFirst10ByOrderByEndDateDesc();
    }

    @Override
    public List<FoContract> findContractEndIn5Month() {
        return foContractRepository.findContractEndIn5Month();
    }
}