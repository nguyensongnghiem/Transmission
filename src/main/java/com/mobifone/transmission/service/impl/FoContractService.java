package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.repository.IFoContractRepository;
import com.mobifone.transmission.service.IFoContractService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<FoContract> foContracts = foContractRepository.findAll();
        return foContracts.stream()
                .filter(f -> (Period.between(LocalDate.now(),f.getEndDate()).getMonths()<3) && f.getEndDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(FoContract::getEndDate))
                .toList();
    }

    @Override
    public FoContract findByContractNumber(String contractNumber) {
        return foContractRepository.findByContractNumber(contractNumber);
    }

    @Override
    public FoContract findById(Integer id) {
        return foContractRepository.findById(id, FoContract.class);
    }

    @Override
    public List<FoContractViewDTO> findAllViewDTO() {
        return foContractRepository.findBy(FoContractViewDTO.class);
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
    public FoContractViewDTO findViewDTOById(int editId) {
        return foContractRepository.findById(editId, FoContractViewDTO.class);
    }

}
