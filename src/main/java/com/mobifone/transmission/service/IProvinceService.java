package com.mobifone.transmission.service;

import com.mobifone.transmission.dto.ProvinceDTO;
import com.mobifone.transmission.model.Province;

import java.util.List;
import java.util.Optional;

public interface IProvinceService {
    public List<Province> findAll();

    Optional<Province> findById(String id);

    public boolean save(Province province);

    <T> List<T> findBy(Class<T> provinceDTOClass);
}
