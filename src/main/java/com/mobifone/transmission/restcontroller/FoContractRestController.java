package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.service.IFoContractService;
import com.mobifone.transmission.service.IHiredFoService;
import com.mobifone.transmission.service.impl.FoContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class FoContractRestController {
    @Autowired
    private IFoContractService contractService;
    @Autowired
    private IHiredFoService hiredFoService;
    @GetMapping
    public ResponseEntity<Object> getContracts() {
        List<FoContractViewDTO> foContracts = contractService.findAllViewDTO();
        return new ResponseEntity<>(foContracts,HttpStatus.OK);
    }
    @GetMapping("/{id}/hired-fo")
    public ResponseEntity<Object> getHiredFoByContractId(@PathVariable int id) {
        List<HiredFoLineViewDTO> hiredFoLineViewDTOList = hiredFoService.getHiredFoLineViewDTOByContractId(id);
        return new ResponseEntity<>(hiredFoLineViewDTOList,HttpStatus.OK);
    }

}
