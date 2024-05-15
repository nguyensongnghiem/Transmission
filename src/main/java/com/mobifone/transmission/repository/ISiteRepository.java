package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.RouterType;
import com.mobifone.transmission.model.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISiteRepository extends JpaRepository<Site,Long> {
public Site findSitesBySiteId(String siteId);
    Page<Site> findSiteBySiteIdContainingIgnoreCase(String name, Pageable pageable);

}
