package com.mobifone.transmission.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobifone.transmission.model.RouterBackup;
import com.mobifone.transmission.repository.IRouterBackupRepository;
import com.mobifone.transmission.service.IRouterBackupService;
@Service
public class RouterBackupService implements IRouterBackupService {
    @Autowired
    private IRouterBackupRepository routerBackupRepository;

    @Override
    public boolean save(RouterBackup routerBackup) {
        try {
            routerBackupRepository.save(routerBackup);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<RouterBackup> findAll() {
        return routerBackupRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        routerBackupRepository.deleteById(id);
    }

    @Override
    public RouterBackup findById(Long id) {
       return routerBackupRepository.findById(id).get();
    }   
    
}
