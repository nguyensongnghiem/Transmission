package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.LeaseLineConnectTypeDTO;
import com.mobifone.transmission.dto.ProvinceDTO;
import com.mobifone.transmission.dto.SiteOwnerDTO;
import com.mobifone.transmission.dto.inf.LeaselineConnectTypeViewDTO;
import com.mobifone.transmission.dto.inf.ProvinceViewDTO;
import com.mobifone.transmission.model.LeaseLineConnectType;
import com.mobifone.transmission.service.ILeaseLineConnectTypeService;
import com.mobifone.transmission.service.IProvinceService;
import com.mobifone.transmission.service.ISiteOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaseline-connect-type")
// @CrossOrigin(origins = "*")
public class LeaselineConnectTypeController {
    @Autowired
    ILeaseLineConnectTypeService leaselineConnectTypeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<LeaseLineConnectType> list = leaselineConnectTypeService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
