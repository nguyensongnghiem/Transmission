package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.ProvinceDTO;
import com.mobifone.transmission.dto.inf.ProvinceViewDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.mapper.SiteCreateDTOToSite;
import com.mobifone.transmission.model.Province;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// @CrossOrigin(origins = "*")
@RequestMapping("/api/sites/reports")
public class SiteReportController {
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

    @GetMapping("/total")
    public ResponseEntity<?> getTotalSite() {
        List<SiteViewDTO> siteList = siteService.findBy(SiteViewDTO.class);
        return new ResponseEntity<>(siteList.size(), HttpStatus.OK);
    }

    @GetMapping("/count-by-province")
    public ResponseEntity<?> countByProvince() {
        List<Site> siteList = siteService.findBy(Site.class);
        Map<String, Integer> provinceCountMap = new HashMap<>();
        for (Site site : siteList) {
            String province = site.getProvince().getName();
            provinceCountMap.put(province, provinceCountMap.getOrDefault(province, 0) + 1);
        }
        return new ResponseEntity<>(provinceCountMap, HttpStatus.OK);
    }

    @GetMapping("/count-by-transmission-type")
    public ResponseEntity<?> countByTransmisionType(
            @RequestParam(required = false, defaultValue = "", name = "transmission-type") String transType) {
        if (transType == null || transType.equals("undefined"))
            transType = "";

        List<SiteViewDTO> siteList = siteService.searchAllByTransmissionType(transType, SiteViewDTO.class);
        Map<String, Integer> transTypeCountMap = new HashMap<>();
        for (SiteViewDTO site : siteList) {
            if (site.getSiteTransmissionType() != null) {
                String type = site.getSiteTransmissionType().getName();
                transTypeCountMap.put(type, transTypeCountMap.getOrDefault(type, 0) + 1);
            }
        }
        return new ResponseEntity<>(transTypeCountMap, HttpStatus.OK);
    }

    @GetMapping("/count-by-transmission-type-in-province")
    public ResponseEntity<?> countByTransmisionTypeInProvince() {
        List<ProvinceViewDTO> provinceList = provinceService.findBy(ProvinceViewDTO.class);
        Map<String, Object> provinceTransCount = new HashMap<>();
        provinceList.forEach(provinceDTO -> {
            List<SiteViewDTO> siteList = siteService.searchAllByProvince(provinceDTO.getName(), SiteViewDTO.class);

            Map<String, Integer> transTypeCountMap = new HashMap<>();
            for (SiteViewDTO site : siteList) {
                if (site.getSiteTransmissionType() != null) {
                    String type = site.getSiteTransmissionType().getName();
                    transTypeCountMap.put(type, transTypeCountMap.getOrDefault(type, 0) + 1);
                }
            }
            provinceTransCount.put(provinceDTO.getName(), transTypeCountMap);
        });

        return new ResponseEntity<>(provinceTransCount, HttpStatus.OK);
    }

}
