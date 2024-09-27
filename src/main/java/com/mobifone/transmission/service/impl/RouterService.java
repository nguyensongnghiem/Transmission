package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.dto.inf.SimpleRouterDTO;
import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.repository.IRouterRepository;
import com.mobifone.transmission.repository.ISiteRepository;
import com.mobifone.transmission.service.IRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RouterService implements IRouterService {
    @Autowired
    private IRouterRepository routerRepository;
    public Page<Router> findAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1,20);
        return routerRepository.findAll(pageable);
    }

    @Override
    public List<Router> findAll() {
        return routerRepository.findAll();
    }

    @Override
    public boolean save(Router router) {
        return routerRepository.save(router)!=null;
    }

    @Override
    public void deleteById(Long id) {
        routerRepository.deleteById(id);
    }

    @Override
    public <T> T findById(Long editId, Class<T> classType) {
        return routerRepository.findById(editId,classType);
    }

    @Override
    public Router findById(Long editId) {
        return routerRepository.findById(editId).get();
    }

    @Override
    public boolean update(Router router) {
        return routerRepository.save(router)!=null;
    }

    @Override
    public Router findRouterByName(String routerName) {
        return routerRepository.findRouterByName(routerName);
    }

    @Override
    public Router findRouterByIp(String ip) {
        return routerRepository.findRouterByIp(ip);
    }

    @Override
    public List<RouterViewDTO> findBy(Class<RouterViewDTO> routerViewDTOClass) {
        return routerRepository.findBy(routerViewDTOClass);
    }

    @Override
    public List<SimpleRouterDTO> findAllSimpleRouter() {
        return routerRepository.findAllSimpleRouter();
    }
}
