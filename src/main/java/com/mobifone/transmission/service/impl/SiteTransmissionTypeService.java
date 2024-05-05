package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.SiteTransmissionType;
import com.mobifone.transmission.repository.ISiteTransmissionTypeRepository;
import com.mobifone.transmission.repository.ITelecomCenterRepository;
import com.mobifone.transmission.service.ISiteTransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteTransmissionTypeService implements ISiteTransmissionTypeService {


    @Autowired
    private ISiteTransmissionTypeRepository siteTransmissionTypeRepository;
    @Override
    public List<SiteTransmissionType> findAll() {
        return siteTransmissionTypeRepository.findAll();
    }

    @Override
    public boolean save(SiteTransmissionType siteTransmissionType) {
        return siteTransmissionTypeRepository.save(siteTransmissionType)!=null;
    }
    @Override
    public SiteTransmissionType findById(int id) {
        return siteTransmissionTypeRepository.findById(id).get();
    }
}
