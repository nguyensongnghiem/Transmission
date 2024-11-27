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
@RequestMapping("/api/test")

public class TestController {

    @GetMapping("")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
