package com.mobifone.transmission.restcontroller;

import java.util.List;

import com.mobifone.transmission.dto.SiteTransmissionTypeDTO;
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
@RequestMapping("/api/site-transmission-types")
@CrossOrigin(origins = "*")
public class SiteTranmissionTypeRestController {
    @Autowired
    ISiteTransmissionTypeService transmissionTypeService;
    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<SiteTransmissionTypeDTO> list  = transmissionTypeService.findBy(SiteTransmissionTypeDTO.class);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    

}
