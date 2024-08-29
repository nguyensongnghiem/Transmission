package com.mobifone.transmission.service;

import com.mobifone.transmission.model.SiteOwner;

import java.util.List;
import java.util.Optional;

public interface ISiteOwnerService {
    public List<SiteOwner> findAll();
    public Optional<SiteOwner> findById(int id);
    public boolean save(SiteOwner siteOwner);
}
