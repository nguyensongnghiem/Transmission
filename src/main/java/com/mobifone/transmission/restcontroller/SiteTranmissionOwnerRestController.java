package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.TransmissionOwnerDTO;
import com.mobifone.transmission.model.SiteTransmissionType;
import com.mobifone.transmission.model.TransmissionOwner;
import com.mobifone.transmission.service.ISiteTransmissionTypeService;
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
@RequestMapping("/api/transmissionOwner")
@CrossOrigin(origins = "*")
public class SiteTranmissionOwnerRestController {
    @Autowired
    ITransmissionOwnerService transmissionOwnerService;
    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<TransmissionOwnerDTO> list  = transmissionOwnerService.findBy(TransmissionOwnerDTO.class);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}
