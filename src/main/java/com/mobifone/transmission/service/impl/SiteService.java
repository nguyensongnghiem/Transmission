package com.mobifone.transmission.service.impl;

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
    public <T> Page<T> searchAllSite(String searchSiteId,  String transOwner, String transType, String province, Pageable pageable, Class<T> classType) {
        return siteRepository.findSiteBySiteIdContainingIgnoreCaseAndTransmissionOwner_NameContainingIgnoreCaseAndSiteTransmissionType_NameContainingIgnoreCaseAndProvince_NameContainingIgnoreCase(searchSiteId, transOwner,transType, province, pageable, classType);
    }

    @Override
    public <T> List<T> searchAllByProvince(String province, Class<T> classType) {
        return siteRepository.findByProvince_NameContainingIgnoreCase(province,classType);
    }

    @Override
    public <T> List<T> searchAllByTransmissionType(String transmissionType, Class<T> classType) {
        return siteRepository.findBySiteTransmissionType_NameContainingIgnoreCase(transmissionType,classType);
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
