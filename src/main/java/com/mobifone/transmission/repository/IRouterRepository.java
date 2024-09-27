package com.mobifone.transmission.repository;

import com.mobifone.transmission.dto.inf.SimpleRouterDTO;
import com.mobifone.transmission.model.Router;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRouterRepository extends JpaRepository<Router,Long> {
    
    Router findRouterByName(String routerName);
    Router findRouterByIp(String ip);
    <T> List<T> findBy(Class<T> classType);
    <T> T findById(Long editId,Class<T> classType);
    @Query(nativeQuery = true,value =
            "SELECT r.id, r.name, rt.name as routerType, tdt.name as transmissionDeviceType, s.site_id as siteId from \n" +
            "router r join transmission_device_type tdt on r.transmission_device_type_id = tdt.id\n" +
            "join router_type rt on rt.id = r.router_type_id\n" +
            "join site s on s.id = r.site_id;")
    List<SimpleRouterDTO> findAllSimpleRouter();
}
