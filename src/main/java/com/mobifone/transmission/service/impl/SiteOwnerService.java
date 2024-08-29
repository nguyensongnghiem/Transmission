package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.SiteOwner;
import com.mobifone.transmission.repository.ISiteOwnerRepository;
import com.mobifone.transmission.service.ISiteOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteOwnerService implements ISiteOwnerService {
    @Autowired
    private ISiteOwnerRepository siteOwnerRepository;
    @Override
    public List<SiteOwner> findAll() {
        return siteOwnerRepository.findAll();
    }

    @Override
    public Optional<SiteOwner> findById(int id) {
        return siteOwnerRepository.findById(id);
    }

    @Override
    public boolean save(SiteOwner siteOwner) {
        return siteOwnerRepository.save(siteOwner)!=null;
    }
}
