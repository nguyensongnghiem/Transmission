package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.repository.IFoContractRepository;
import com.mobifone.transmission.service.IFoContractService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
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

    @Override
    public FoContract findByContractNumber(String contractNumber) {
        return foContractRepository.findByContractNumber(contractNumber);
    }

    @Override
    public List<FoContract> findAll() {
        return foContractRepository.findAll();
    }

    @Override
    public void save(FoContract foContract) {
        foContractRepository.save(foContract);
    }

    @Override
    public void deleteById(int deleteId) {
        foContractRepository.deleteById(deleteId);
    }

    @Override
    public Optional<FoContract> findById(int editId) {
        return foContractRepository.findById(editId);
    }

}
