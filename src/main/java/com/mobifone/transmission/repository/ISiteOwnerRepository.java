package com.mobifone.transmission.repository;

import com.mobifone.transmission.controller.SiteController;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.model.SiteOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISiteOwnerRepository extends JpaRepository<SiteOwner,Integer> {

}
