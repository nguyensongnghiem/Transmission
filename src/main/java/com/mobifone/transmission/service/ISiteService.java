package com.mobifone.transmission.service;

import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.model.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISiteService {
    public Page<Site> findAll(Pageable pageable);

    public List<Site> findAll();

    public boolean save(Site site);

    public void deleteById(Long id);

    public <T> T findById(Long editId,Class<T> classType);

    public boolean update(Site site);

    public Site findSitesBySiteId(String siteId);
    Site findSitesBySiteId2(String siteId2);

    public <T> Page<T> searchBySiteId(String searchSiteId, Pageable pageable, Class<T> classType);

    Page<SiteViewDTO> searchBySiteIdAndProvince(String siteId, String provinceName, Pageable pageable, Class<SiteViewDTO> siteViewDTOClass);

    <T> List<T> findBy(Class<T> classType);

}
