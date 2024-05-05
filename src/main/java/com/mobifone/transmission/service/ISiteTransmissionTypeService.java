package com.mobifone.transmission.service;

import com.mobifone.transmission.model.SiteOwner;
import com.mobifone.transmission.model.SiteTransmissionType;

import java.util.List;

public interface ISiteTransmissionTypeService {
    public List<SiteTransmissionType> findAll();
    public SiteTransmissionType findById(int id);
    public boolean save(SiteTransmissionType siteTransmissionType);
}
