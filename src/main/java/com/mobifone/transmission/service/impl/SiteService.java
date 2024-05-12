package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.repository.ISiteRepository;
import com.mobifone.transmission.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SiteService implements ISiteService {

    @Autowired
    private ISiteRepository siteRepository;
    @Override
    public Page<Site> findAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1,20);
        return siteRepository.findAll(pageable);
    }

    @Override
    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    @Override
    public boolean save(Site site) {
        return siteRepository.save(site)!=null;
    }

    @Override
    public void deleteById(Long id) {
        siteRepository.deleteById(id);
    }

    @Override
    public Site findById(Long editId) {
        return siteRepository.findById(editId).get();
    }

    @Override
    public boolean update(Site site) {
        return siteRepository.save(site)!=null;
    }

    @Override
    public Site findSitesBySiteId(String siteId) {
        return siteRepository.findSitesBySiteId(siteId);
    }

}
