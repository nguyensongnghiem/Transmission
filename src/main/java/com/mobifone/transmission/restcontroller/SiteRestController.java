package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.SiteCreateDTO;
import com.mobifone.transmission.dto.inf.TransmissionOwnerViewDTO;
import com.mobifone.transmission.dto.inf.SimpleSiteViewDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.exception.ErrorResponse;
import com.mobifone.transmission.exception.SiteIdExistedException;
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
@RequestMapping("/api/sites")
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

    @GetMapping("/search")
    public ResponseEntity<?> getSitesByPage(
            @RequestParam(required = false, defaultValue = "", name = "siteId") String siteId,
            @RequestParam(required = false, defaultValue = "0", name = "page") int page,
            @RequestParam(required = false, defaultValue = "", name = "transOwner") String transOwner,
            @RequestParam(required = false, defaultValue = "", name = "transType") String transType,
            @RequestParam(required = false, defaultValue = "", name = "province") String province) {
        if (siteId == null)
            siteId = "";
        if (transOwner == null)
            transOwner = "";
        if (transType == null)
            transType = "";
        if (province == null)
            province = "";
        Pageable pageable = PageRequest.of(page, 15);
        Page<SiteViewDTO> siteListPage = siteService.searchAllSite(siteId, transOwner, transType, province, pageable,
                SiteViewDTO.class);

        return new ResponseEntity<>(siteListPage, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalSite() {
        List<SiteViewDTO> siteList = siteService.findBy(SiteViewDTO.class);
        return new ResponseEntity<>(siteList.size(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllSites() {
        List<SiteViewDTO> siteList = siteService.findBy(SiteViewDTO.class);
        return new ResponseEntity<>(siteList, HttpStatus.OK);
    }

    @GetMapping("/simple-list")
    public ResponseEntity<?> getAllSimpleSites() {
        List<SimpleSiteViewDTO> siteList = siteService.findBy(SimpleSiteViewDTO.class);
        return new ResponseEntity<>(siteList, HttpStatus.OK);
    }

    @GetMapping("/totalByProvince")
    public ResponseEntity<?> getSiteTallyByProvince() {
        List<Site> siteList = siteService.findBy(Site.class);
        Map<String, Integer> provinceCountMap = new HashMap<>();
        for (Site site : siteList) {
            String province = site.getProvince().getName();
            provinceCountMap.put(province, provinceCountMap.getOrDefault(province, 0) + 1);
        }
        return new ResponseEntity<>(provinceCountMap, HttpStatus.OK);
    }

    @GetMapping("/totalByTransmissionType")
    public ResponseEntity<?> getSiteTallyByTransmissionType() {
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

    @GetMapping("/transmissionTypeTallyByProvince")
    public ResponseEntity<?> searchSiteTallyByTransmissionType(
            @RequestParam(required = false, defaultValue = "", name = "province") String province) {
        if (province == null)
            province = "";
        List<SiteViewDTO> siteList = siteService.searchAllByProvince(province, SiteViewDTO.class);

        Map<String, Integer> transTypeCountMap = new HashMap<>();
        for (SiteViewDTO site : siteList) {
            if (site.getSiteTransmissionType() != null) {
                String transType = site.getSiteTransmissionType().getName();
                transTypeCountMap.put(transType, transTypeCountMap.getOrDefault(transType, 0) + 1);
            }
        }
        return new ResponseEntity<>(transTypeCountMap, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRestSite(@Valid @RequestBody SiteCreateDTO siteDTO) {
        Site targetSite = siteCreateDTOToSite.apply(siteDTO);
        if (siteService.findSitesBySiteId(siteDTO.getSiteId()) != null) {
            throw new SiteIdExistedException("Trạm " + siteDTO.getSiteId() + " đã tồn tại trong hệ thống.");
        }
        siteService.save(targetSite);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestSite(@Valid @RequestBody SiteCreateDTO siteDTO, @PathVariable long id) {

        Site targetSite = siteService.findById(id, Site.class);
        if (targetSite == null)
            throw new SiteNotFoundException("Không tồn tại trạm với id " + id + " trong hệ thống.");
        BeanUtils.copyProperties(siteDTO, targetSite);
        siteService.save(targetSite);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSiteById(@PathVariable Long id) {

        SiteViewDTO site = siteService.findById(id, SiteViewDTO.class);
        if (site == null)
            throw new SiteNotFoundException("Không tồn tại trạm với id " + id + " trong hệ thống.");
        return ResponseEntity.ok(site);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<?> getSiteDetailById(@PathVariable("id") Long id) {

        Site site = siteService.findById(id, Site.class);
        if (site == null)
            throw new SiteNotFoundException("Không tồn tại trạm với id " + id + " trong hệ thống.");
        return ResponseEntity.ok(site);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSiteById(@PathVariable Long id) {
        Site site = siteService.findById(id, Site.class);
        if (site == null)
            throw new SiteNotFoundException("Không tồn tại trạm trong  hệ thống.");
        siteService.deleteById(id);
        return ResponseEntity.ok("Deleted Site Successful.");

    }

}
