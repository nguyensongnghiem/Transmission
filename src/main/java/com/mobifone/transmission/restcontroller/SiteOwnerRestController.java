package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.SiteOwnerDTO;
import com.mobifone.transmission.dto.TransmissionOwnerDTO;
import com.mobifone.transmission.dto.inf.SiteOwnerViewDTO;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.ISiteOwnerService;
import com.mobifone.transmission.service.ITransmissionOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/siteOwners")
@CrossOrigin(origins = "*")
public class SiteOwnerRestController {
    @Autowired
    ISiteOwnerService siteOwnerService;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<SiteOwnerViewDTO> list = siteOwnerService.findBy(SiteOwnerViewDTO.class);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
