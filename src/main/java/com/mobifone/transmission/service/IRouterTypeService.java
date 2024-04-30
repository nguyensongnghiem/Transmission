package com.mobifone.transmission.service;

import com.mobifone.transmission.model.RouterType;

import java.util.List;

public interface IRouterTypeService {
    public List<RouterType> findAll();
    public boolean save(RouterType routerType);
}
