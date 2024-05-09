package com.mobifone.transmission.service;

import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.model.Site;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRouterService {
    public Page<Router> findAll(int currentPage);
    public List<Router> findAll();
    public boolean save(Router router);
    public void deleteById(Long id);

    public Router findById(Long id);
    public boolean update(Router router);
}
