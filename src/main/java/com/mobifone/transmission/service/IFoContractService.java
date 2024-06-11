package com.mobifone.transmission.service;

import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.model.FoContract;

import java.util.List;
import java.util.Optional;

public interface IFoContractService {
    public List<FoContract> findFirst10ByOrderByEndDateDesc();
    public List<FoContract> findContractEndIn5Month();
    public FoContract findByContractNumber(String name);

    FoContract findById(Integer id);
    List<FoContractViewDTO> findAllViewDTO();

    void save(FoContract foContract);

    void deleteById(int deleteId);


    FoContractViewDTO findViewDTOById(int editId);
}
