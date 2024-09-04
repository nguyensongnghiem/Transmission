package com.mobifone.transmission.service;

import com.mobifone.transmission.model.SiteOwner;
import com.mobifone.transmission.model.SiteTransmissionType;

import java.util.List;
import java.util.Optional;

public interface ISiteTransmissionTypeService {
    public List<SiteTransmissionType> findAll();
    public  <T> List<T> findBy(Class<T> clazz);
    public boolean save(SiteTransmissionType siteTransmissionType);

    Optional<SiteTransmissionType> findById(int id);
}
