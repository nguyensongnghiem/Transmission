package com.mobifone.transmission.service;

import com.mobifone.transmission.model.Site;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISiteService {
    public Page<Site> findAll(int currentPage);
    public List<Site> findAll();
    public boolean save(Site site);
    public void deleteById(Long id);

    public Site findById(Long editId);

    public boolean update(Site site);
}
