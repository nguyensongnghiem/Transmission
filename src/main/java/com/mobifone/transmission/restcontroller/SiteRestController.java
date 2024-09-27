package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.SiteCreateDTO;
import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.exception.SiteNotFoundException;
import com.mobifone.transmission.mapper.SiteCreateDTOToSite;
import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;


import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SiteRestController {
    @Autowired
    private ISiteService siteService;
    @Autowired
    private ISiteOwnerService siteOwnerService;
    @Autowired
    private ISiteTransmissionTypeService siteTransmissionTypeService;
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ITransmissionOwnerService transmissionOwnerService;
    @Autowired
    private SiteCreateDTOToSite siteCreateDTOToSite;
    @GetMapping("/api/sites/search")
    public ResponseEntity<?> getSitesByPage(
        @RequestParam(required = false, defaultValue = "", name = "siteId") String siteId,
        @RequestParam(required = false, defaultValue = "0", name = "page") int page,
          @RequestParam(required = false, defaultValue = "", name = "transOwner") String transOwner,
        @RequestParam(required = false, defaultValue = "", name = "transType") String transType,
        @RequestParam(required = false, defaultValue = "", name = "province") String province
    ) {
        if (siteId == null) siteId="";
        if (transOwner == null) transOwner="";
        if (transType == null) transType="";
        if (province == null) province="";
        Pageable pageable = PageRequest.of(page, 15);
        Page<SiteViewDTO> siteListPage = siteService.searchAllSite(siteId,transOwner,transType, province, pageable, SiteViewDTO.class);

        return new ResponseEntity<>(siteListPage, HttpStatus.OK);
    }

    @GetMapping("/api/sites/total")
    public ResponseEntity<?> getTotalSite() {
        List<SiteViewDTO> siteList = siteService.findBy( SiteViewDTO.class);
        return new ResponseEntity<>(siteList.size(), HttpStatus.OK);
    }

    
     @GetMapping("/api/sites")
     public ResponseEntity<?> getAllSites(
     ) {
         List<SiteViewDTO> siteList = siteService.findBy(SiteViewDTO.class);
         return new ResponseEntity<>(siteList, HttpStatus.OK);
     }

    @GetMapping("/api/sites/totalByProvince")
    public ResponseEntity<?> getSiteTallyByProvince(
    ) {
        List<Site> siteList = siteService.findBy(Site.class);
        Map<String, Integer> provinceCountMap = new HashMap<>();
        for (Site site : siteList) {
            String province = site.getProvince().getName();
            provinceCountMap.put(province, provinceCountMap.getOrDefault(province, 0) + 1);
        }
        return new ResponseEntity<>(provinceCountMap, HttpStatus.OK);
    }

    @GetMapping("/api/sites/totalByTransmissionType")
    public ResponseEntity<?> getSiteTallyByTransmissionType(
    ) {
        List<Site> siteList = siteService.findBy(Site.class);
        Map<String, Integer> transTypeCountMap = new HashMap<>();
        for (Site site : siteList) {
            if (site.getSiteTransmissionType() != null) {
                String transType = site.getSiteTransmissionType().getName();
                transTypeCountMap.put(transType, transTypeCountMap.getOrDefault(transType, 0) + 1);
            }
        }
        return new ResponseEntity<>(transTypeCountMap, HttpStatus.OK);
    }


    @GetMapping("/api/sites/transmissionTypeTallyByProvince")
    public ResponseEntity<?> searchSiteTallyByTransmissionType(
            @RequestParam(required = false, defaultValue = "", name = "province") String province
    ) {
        if (province == null) province="";
        List<SiteViewDTO> siteList = siteService.searchAllByProvince(province,SiteViewDTO.class);

        Map<String, Integer> transTypeCountMap = new HashMap<>();
        for (SiteViewDTO site : siteList) {
            if (site.getSiteTransmissionType() != null) {
                String transType = site.getSiteTransmissionType().getName();
                transTypeCountMap.put(transType, transTypeCountMap.getOrDefault(transType, 0) + 1);
            }
        }
        return new ResponseEntity<>(transTypeCountMap, HttpStatus.OK);
    }


//    @PostMapping("/api/sites")
//    public ResponseEntity<?> createSite(@Valid @RequestBody SiteDTO siteDTO) {
//        System.out.println(siteDTO.toString());
//        Site targetSite = new Site();
//        BeanUtils.copyProperties(siteDTO,targetSite);
//        siteService.save(targetSite);
//        return ResponseEntity.ok(targetSite);
//    }

    @PostMapping("/api/sites")
    public ResponseEntity<?> createRestSite(@Valid @RequestBody SiteCreateDTO siteDTO) {
        Site targetSite = siteCreateDTOToSite.apply(siteDTO);
        if (targetSite.getId()!=null) {
            Site existSite = siteService.findById(targetSite.getId(), Site.class);
            BeanUtils.copyProperties(targetSite,existSite);
            siteService.save(existSite);
        } else {siteService.save(targetSite);}
        System.out.println(targetSite);
        return ResponseEntity.ok(targetSite);
    }

    @PutMapping("/api/sites")
    public ResponseEntity<?> updateRestSite(@Valid @RequestBody SiteCreateDTO siteDTO) {
        Site targetSite = siteCreateDTOToSite.apply(siteDTO);
        siteService.save(targetSite);
        System.out.println(targetSite);
        return ResponseEntity.ok(targetSite);
    }

    @GetMapping("/api/sites/{id}")
    public ResponseEntity<?> getSite(@PathVariable Long id) {
        SiteViewDTO site = siteService.findById(id, SiteViewDTO.class);
        if (site==null) throw new SiteNotFoundException("Site not found.");
        return ResponseEntity.ok(site);
    }
   
    @DeleteMapping("/api/sites/{id}")
    public ResponseEntity<?> deleteSiteById(@PathVariable Long id) {
        Site site = siteService.findById(id, Site.class);
        if (site==null) {throw new SiteNotFoundException("Site ID không tồn tại");}
        else {
            siteService.deleteById(id);
        };
        return ResponseEntity.ok("Deleted Site Successful.");
    }

}
