package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.SiteTransmissionType;
import com.mobifone.transmission.repository.ISiteTransmissionTypeRepository;
import com.mobifone.transmission.service.ISiteTransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteTransmissionTypeService implements ISiteTransmissionTypeService {


    @Autowired
    private ISiteTransmissionTypeRepository siteTransmissionTypeRepository;
    @Override
    public List<SiteTransmissionType> findAll() {
        return siteTransmissionTypeRepository.findAll();
    }

    @Override
    public Optional<SiteTransmissionType> findById(int id) {
        return siteTransmissionTypeRepository.findById(id);
    }

    @Override
    public boolean save(SiteTransmissionType siteTransmissionType) {
        return siteTransmissionTypeRepository.save(siteTransmissionType)!=null;
    }
