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

@RestController
@RequestMapping
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
    @GetMapping("/api/sites")
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
    
    // @GetMapping("/api/sites")
    // public ResponseEntity<?> getAllSites(       
    // ) {
    //     List<Site> siteList = siteService.findBy(Site.class);
    //     return new ResponseEntity<>(siteList, HttpStatus.OK);
    // }

//    @PostMapping("/api/sites")
//    public ResponseEntity<?> createSite(@Valid @RequestBody SiteDTO siteDTO) {
//        System.out.println(siteDTO.toString());
//        Site targetSite = new Site();
//        BeanUtils.copyProperties(siteDTO,targetSite);
//        siteService.save(targetSite);
//        return ResponseEntity.ok(targetSite);
//    }

    @PostMapping("/api/sites/rest")
    public ResponseEntity<?> createRestSite(@Valid @RequestBody SiteCreateDTO siteDTO) {
        Site targetSite = siteCreateDTOToSite.apply(siteDTO);
//          Site targetSite = new Site();
//        BeanUtils.copyProperties(siteDTO,targetSite);
//        Optional<SiteOwner> siteOwner = siteOwnerService.findById(siteDTO.getSiteOwner().getId());
//        Optional<SiteTransmissionType> siteTransmissionType = siteTransmissionTypeService.findById(siteDTO.getSiteTransmissionType().getId());
//        Optional<TransmissionOwner> transmissionOwner = transmissionOwnerService.findById(siteDTO.getTransmissionOwner().getId());
//        Optional<Province> province = provinceService.findById(siteDTO.getProvince().getId());
//        targetSite.setSiteOwner(siteOwner.get());
//        targetSite.setSiteTransmissionType(siteTransmissionType.get());
//        targetSite.setSiteOwner(siteOwner.get());
//        targetSite.setTransmissionOwner(transmissionOwner.get());
//        targetSite.setProvince(province.get());
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
        if (site==null) {throw new SiteNotFoundException("Site ID không tồn tại !");}
        else {
            siteService.deleteById(id);
        };
        return ResponseEntity.ok("Deleted Site Successful.");
    }

}
