package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISiteRepository extends JpaRepository<Site,Long> {
public Site findSitesBySiteId(String siteId);
    Page<Site> findSiteBySiteIdContainingIgnoreCase(String siteId,Pageable pageable);
    Page<Site> findSitesBySiteIdContainingIgnoreCaseAndProvince_NameContainingIgnoreCase(String siteId, String provinceName, Pageable pageable);

}
