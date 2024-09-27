package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.RouterType;
import com.mobifone.transmission.repository.IRouterTypeRepository;
import com.mobifone.transmission.service.IRouterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RouterTypeService implements IRouterTypeService {
    @Autowired
    private IRouterTypeRepository routerTypeRepository;
    @Override
    public List<RouterType> findAll() {
        return routerTypeRepository.findAll();
    }

    @Override
    public boolean save(RouterType routerType) {
        return routerTypeRepository.save(routerType)!=null;
    }

    @Override
    public <T> List<T> findBy(Class<T> classType) {
        return routerTypeRepository.findBy(classType);
    }
}
