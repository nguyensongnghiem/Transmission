package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.HiredFoLine;
import com.mobifone.transmission.service.IFoContractService;
import com.mobifone.transmission.service.IHiredFoService;
import com.mobifone.transmission.service.impl.FoContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContractDetail(@PathVariable int id) {
        FoContractViewDTO foContract = contractService.findById(id,FoContractViewDTO.class);
        return new ResponseEntity<>(foContract,HttpStatus.OK);
    }

    @GetMapping("/{id}/hired-fo")
    public ResponseEntity<Object> getHiredFoByContractId(@PathVariable int id) {
        List<HiredFoLineViewDTO> hiredFoLineViewDTOList = hiredFoService.getHiredFoLineViewDTOByContractId(id);
        return new ResponseEntity<>(hiredFoLineViewDTOList,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoContractById(@PathVariable int id) {
//        HiredFoLineViewDTO hiredFoLineViewDTO = hiredFoService.findById(id, HiredFoLineViewDTO.class);
//        if (hiredFoLineViewDTO==null) {throw new SiteNotFoundException("Site ID không tồn tại !");}
//        else {
        contractService.deleteById(id);
//        };
        return ResponseEntity.ok("Đã xóa thành công Hợp đồng FO thuê");
    }



}
