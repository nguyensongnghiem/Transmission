package com.mobifone.transmission.service;

import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.dto.inf.SimpleRouterDTO;
import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.model.Site;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRouterService {
    public Page<Router> findAll(int currentPage);
    public List<Router> findAll();
    public boolean save(Router router);
    public void deleteById(Long id);
    public <T> T findById(Long editId,Class<T> classType);
    public Router findById(Long id);
    public boolean update(Router router);

    Router findRouterByName(String routerName);
    Router findRouterByIp(String ip);

    List<RouterViewDTO> findBy(Class<RouterViewDTO> routerViewDTOClass);
    List<SimpleRouterDTO> findAllSimpleRouter();
}
