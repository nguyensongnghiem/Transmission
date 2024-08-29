package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.Province;
import com.mobifone.transmission.model.SiteOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProvinceRepository extends JpaRepository<Province,String> {


}
