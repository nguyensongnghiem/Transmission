package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.SiteTransmissionTypeDTO;
import com.mobifone.transmission.service.ISiteTransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/site-transmission-types/reports")
@CrossOrigin(origins = "*")
public class SiteTranmissionTypeReportController {
    @Autowired
    ISiteTransmissionTypeService transmissionTypeService;


    @GetMapping("/totalFo")
    public ResponseEntity<?> getTotalFo() {
        int total = transmissionTypeService.findById(1).get().getSiteList().size()
                + transmissionTypeService.findById(2).get().getSiteList().size()
                + transmissionTypeService.findById(3).get().getSiteList().size();
        return new ResponseEntity<>(total,HttpStatus.OK);
    }

    @GetMapping("/totalMW")
    public ResponseEntity<?> getTotalMW() {
        int total = transmissionTypeService.findById(5).get().getSiteList().size()
                + transmissionTypeService.findById(6).get().getSiteList().size();
        return new ResponseEntity<>(total,HttpStatus.OK);
    }

    @GetMapping("/totalLL")
    public ResponseEntity<?> getTotalLL() {
        int total = transmissionTypeService.findById(4).get().getSiteList().size();
        return new ResponseEntity<>(total,HttpStatus.OK);
    }
    

}
