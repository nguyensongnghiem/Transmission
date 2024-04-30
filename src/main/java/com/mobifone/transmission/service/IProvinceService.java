package com.mobifone.transmission.service;

import com.mobifone.transmission.model.Province;

import java.util.List;

public interface IProvinceService {
    public List<Province> findAll();
    public boolean save(Province province);
}
