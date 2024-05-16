package com.mobifone.transmission.service;

import com.mobifone.transmission.dto.SiteViewDTO;
import com.mobifone.transmission.model.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISiteService {
    public Page<Site> findAll(Pageable pageable);

    public List<Site> findAll();

    public boolean save(Site site);

    public void deleteById(Long id);

    public Site findById(Long editId);

    public boolean update(Site site);

    public Site findSitesBySiteId(String siteId);

    public Page<Site> findSiteBySiteIdContainingIgnoreCase(String searchSiteId, Pageable pageable);
    Page<Site> findSitesBySiteIdAndProvince_Name(String siteId,String provinceName, Pageable pageable);


}
