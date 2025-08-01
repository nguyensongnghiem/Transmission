package com.mobifone.transmission.service;
import java.util.List;
import com.mobifone.transmission.model.RouterBackup;

public interface IRouterBackupService {
    public boolean save(RouterBackup routerBackup);

    public List<RouterBackup> findAll();

    public void deleteById(Long id);

    public RouterBackup findById(Long id);

}
