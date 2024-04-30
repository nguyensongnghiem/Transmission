package com.mobifone.transmission.service;

import com.mobifone.transmission.model.Province;
import com.mobifone.transmission.model.SiteOwner;

import java.util.List;

public interface ISiteOwnerService {
    public List<SiteOwner> findAll();
    public boolean save(SiteOwner siteOwner);
}
