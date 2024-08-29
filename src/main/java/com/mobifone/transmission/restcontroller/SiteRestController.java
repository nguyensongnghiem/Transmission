package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.exception.SiteIdExistedException;
import com.mobifone.transmission.exception.SiteNotFoundException;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.ISiteService;


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


    @GetMapping("/api/sites")
    public ResponseEntity<?> getSitesByPage(
        @RequestParam(required = false, defaultValue = "", name = "siteId") String siteId,
        @RequestParam(required = false, defaultValue = "0", name = "page") int page
    ) {
        if (siteId == null) siteId="";
        Pageable pageable = PageRequest.of(page, 15);
        Page<SiteViewDTO> siteListPage = siteService.findBySiteIdContainingIgnoreCase(siteId,pageable, SiteViewDTO.class);

        return new ResponseEntity<>(siteListPage, HttpStatus.OK);
    }
    
    // @GetMapping("/api/sites")
    // public ResponseEntity<?> getAllSites(       
    // ) {
    //     List<Site> siteList = siteService.findBy(Site.class);
    //     return new ResponseEntity<>(siteList, HttpStatus.OK);
    // }

    @PostMapping("/api/sites")
    public ResponseEntity<?> createSite(@Valid @RequestBody SiteDTO siteDTO) {
        Site targetSite = new Site();
        BeanUtils.copyProperties(siteDTO,targetSite);
        siteService.save(targetSite);
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
        if (site==null) {throw new SiteNotFoundException("Site not found.");}
        else {
            siteService.deleteById(id);
        };
        return ResponseEntity.ok("Deleted Site Successful.");
    }

}
