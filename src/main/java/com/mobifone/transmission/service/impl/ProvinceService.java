package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.Province;
import com.mobifone.transmission.repository.IProvinceRepository;
import com.mobifone.transmission.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;
    @Override
    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public boolean save(Province province) {
        return provinceRepository.save(province)!=null;
    }
}
