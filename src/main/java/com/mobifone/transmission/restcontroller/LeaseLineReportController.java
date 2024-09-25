package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.inf.LeaseLineViewDTO;
import com.mobifone.transmission.model.LeaseLine;
import com.mobifone.transmission.service.ILeaseLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/leaselines/reports")
public class LeaseLineReportController {
    @Autowired
    private ILeaseLineService leaseLineService;

//    @GetMapping("/api/list")
//    @ResponseBody
//    public Object getLeaseLineList() {
//        return leaseLineService.findBy(LeaseLineViewDTO.class);
//    }



    @GetMapping("/total")
    public ResponseEntity<?> getTotalLeaseLines() {


        List<LeaseLineViewDTO> leaseLineViewDTOS = leaseLineService.findBy(LeaseLineViewDTO.class);
        return new ResponseEntity<>(leaseLineViewDTOS.size(), HttpStatus.OK);
    }

    @GetMapping("/total-cost-per-month")
    public ResponseEntity<?> getTotalCost() {
        List<LeaseLine> leaseLines = leaseLineService.findAll();
        float cost = 0f ;
        for (LeaseLine ll : leaseLines) {
            cost += ll.getCost()*ll.getQuantity();
        }

        return new ResponseEntity<>(cost, HttpStatus.OK);
    }


}
