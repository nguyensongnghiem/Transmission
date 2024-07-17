package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.exception.SiteIdExistedException;
import com.mobifone.transmission.exception.SiteNotFoundException;
import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.ISiteService;
import com.mobifone.transmission.service.impl.SiteService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class SiteRestController {
    @Autowired
    private ISiteService siteService;

//    @GetMapping
//    @ResponseBody
//    public Object getSiteList() {
//        return siteService.findBy(SiteViewDTO.class);
//    }

    @GetMapping("/api/sites/search")
    public ResponseEntity<?> getSitesByPage(
        @RequestParam(required = false, defaultValue = "", name = "siteId") String siteId,
        @RequestParam(required = false, defaultValue = "0", name = "page") int page
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<SiteViewDTO> siteListPage = siteService.searchBySiteId(siteId,pageable, SiteViewDTO.class);
        return new ResponseEntity<>(siteListPage, HttpStatus.OK);
    }
    
    @GetMapping("/api/sites")
    public ResponseEntity<?> getAllSites(       
    ) {
        List<SiteViewDTO> siteList = siteService.findBy(SiteViewDTO.class);
        return new ResponseEntity<>(siteList, HttpStatus.OK);
    }

    @PostMapping("/api/sites")
    public void createSite(@RequestBody Site site) {
//        if (siteService.findSitesBySiteId(site.getSiteId())!=null) {
//            throw new SiteIdExistedException("SiteID existed in database");
//        }
        siteService.save(site);
    }
    @GetMapping("/api/sites/{id}")
    public ResponseEntity<?> getSite(@PathVariable Long id) {
        Site site = siteService.findById(id, Site.class);
        if (site==null) throw new SiteNotFoundException("Site not found.");
        return ResponseEntity.ok(site);
    }
    @GetMapping("/api/siteViewDtos/{id}")
    public ResponseEntity<?> getSiteViewDTO(@PathVariable Long id) {
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
