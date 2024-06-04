package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.service.IFoContractService;
import com.mobifone.transmission.service.impl.FoContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class FoContractRestController {
    @Autowired
    private IFoContractService contractService;
    @GetMapping
    public ResponseEntity<List<FoContract>> getContracts() {
        List<FoContract> foContracts = contractService.findAll();
        return new ResponseEntity<>(contractService.findAll(),HttpStatus.OK);
    }


}
