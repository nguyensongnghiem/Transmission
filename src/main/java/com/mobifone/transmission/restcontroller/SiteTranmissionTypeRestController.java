package com.mobifone.transmission.restcontroller;

import java.util.List;

import com.mobifone.transmission.dto.SiteTransmissionTypeDTO;
import com.mobifone.transmission.dto.inf.TransmissionOwnerViewDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobifone.transmission.model.SiteTransmissionType;
import com.mobifone.transmission.service.ISiteTransmissionTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController

// @CrossOrigin(origins = "*")
public class SiteTranmissionTypeRestController {
    @Autowired
    ISiteTransmissionTypeService transmissionTypeService;

    @GetMapping("/api/site-transmission-types")
    public ResponseEntity<?> findAll() {
        List<TransmissionOwnerViewDTO> list = transmissionTypeService.findBy(TransmissionOwnerViewDTO.class);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/api/site-transmission-types/totalFo")
    public ResponseEntity<?> getTotalFo() {
        int total = transmissionTypeService.findById(1).get().getSiteList().size()
                + transmissionTypeService.findById(2).get().getSiteList().size()
                + transmissionTypeService.findById(3).get().getSiteList().size();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @GetMapping("/api/site-transmission-types/totalMW")
    public ResponseEntity<?> getTotalMW() {
        int total = transmissionTypeService.findById(5).get().getSiteList().size()
                + transmissionTypeService.findById(6).get().getSiteList().size();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @GetMapping("/api/site-transmission-types/totalLL")
    public ResponseEntity<?> getTotalLL() {
        int total = transmissionTypeService.findById(4).get().getSiteList().size();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

}
