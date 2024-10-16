package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.model.TransmissionDeviceType;
import com.mobifone.transmission.service.ITransmissionDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transmission-device-types")
@CrossOrigin(origins = "*")
public class TransmissionDeviceTypeRestController {
    @Autowired
    ITransmissionDeviceTypeService transmissionDeviceTypeService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<TransmissionDeviceType> list = transmissionDeviceTypeService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //
    // @GetMapping("/api/site-transmission-types/totalFo")
    // public ResponseEntity<?> getTotalFo() {
    // int total =
    // transmissionDeviceTypeService.findById(1).get().getSiteList().size()
    // + transmissionDeviceTypeService.findById(2).get().getSiteList().size()
    // + transmissionDeviceTypeService.findById(3).get().getSiteList().size();
    // return new ResponseEntity<>(total,HttpStatus.OK);
    // }
    //
    // @GetMapping("/api/site-transmission-types/totalMW")
    // public ResponseEntity<?> getTotalMW() {
    // int total =
    // transmissionDeviceTypeService.findById(5).get().getSiteList().size()
    // + transmissionDeviceTypeService.findById(6).get().getSiteList().size();
    // return new ResponseEntity<>(total,HttpStatus.OK);
    // }
    //
    // @GetMapping("/api/site-transmission-types/totalLL")
    // public ResponseEntity<?> getTotalLL() {
    // int total =
    // transmissionDeviceTypeService.findById(4).get().getSiteList().size();
    // return new ResponseEntity<>(total,HttpStatus.OK);
    // }
    //
    //
}
