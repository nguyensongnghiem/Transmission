package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.Province;
import com.mobifone.transmission.model.SiteOwner;
import com.mobifone.transmission.repository.IRouterTypeRepository;
import com.mobifone.transmission.repository.ISiteOwnerRepository;
import com.mobifone.transmission.service.ISiteOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SiteOwnerService implements ISiteOwnerService {
    @Autowired
    private ISiteOwnerRepository siteOwnerRepository;
    @Override
    public List<SiteOwner> findAll() {
        return siteOwnerRepository.findAll();
    }

    @Override
    public boolean save(SiteOwner siteOwner) {
        return siteOwnerRepository.save(siteOwner)!=null;
    }
}
