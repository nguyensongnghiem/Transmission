package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.repository.ISiteRepository;
import com.mobifone.transmission.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SiteService implements ISiteService {

    @Autowired
    private ISiteRepository siteRepository;
    @Override
    public Page<Site> findAll(Pageable pageable) {
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
    public <T> T findById(Long editId,Class<T> classType) {
        return siteRepository.findById(editId,classType);
    }

    @Override
    public boolean update(Site site) {
        return siteRepository.save(site)!=null;
    }

    @Override
    public Site findSitesBySiteId(String siteId) {
        return siteRepository.findSitesBySiteId(siteId);
    }

    @Override
    public <T> Page<T> searchBySiteId(String searchSiteId, Pageable pageable,Class<T> classType) {
        return siteRepository.findSiteBySiteIdContainingIgnoreCase(searchSiteId, pageable, classType);
    }

    @Override
    public Page<SiteViewDTO> searchBySiteIdAndProvince(String siteId, String provinceName, Pageable pageable, Class<SiteViewDTO> siteViewDTOClass) {
        return siteRepository.findSitesBySiteIdContainingIgnoreCaseAndProvince_NameContainingIgnoreCase(siteId, provinceName,pageable,siteViewDTOClass);
    }

    @Override
    public Site findSitesBySiteId2(String siteId2) {
        return siteRepository.findSitesBySiteId2(siteId2);
    }

    @Override
    public <T> List<T> findBy(Class<T> classType) {
        return siteRepository.findBy(classType);
    }


}
