package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.ISiteService;
import com.mobifone.transmission.service.impl.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
public class SiteRestController {
    @Autowired
    private ISiteService siteService;

//    @GetMapping
//    @ResponseBody
//    public Object getSiteList() {
//        return siteService.findBy(SiteViewDTO.class);
//    }

    @GetMapping
    public ResponseEntity<List<SiteViewDTO>> getSites() {
        List<SiteViewDTO> siteList = siteService.findBy(SiteViewDTO.class);
        return new ResponseEntity<>(siteList, HttpStatus.OK);
    }
}
