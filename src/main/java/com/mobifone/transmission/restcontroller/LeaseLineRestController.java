package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.LeaselineDTO;
import com.mobifone.transmission.dto.RouterDTO;
import com.mobifone.transmission.dto.inf.LeaseLineViewDTO;
import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.exception.LeaselineNotFoundException;
import com.mobifone.transmission.exception.RouterNotFoundException;
import com.mobifone.transmission.model.LeaseLine;
import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.service.ILeaseLineService;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.dgc.Lease;
import java.util.List;

@RestController
// @CrossOrigin(origins = "*")
@RequestMapping("/api/leaselines")
public class LeaseLineRestController {
    @Autowired
    private ILeaseLineService leaseLineService;

    // @GetMapping("/api/list")
    // @ResponseBody
    // public Object getLeaseLineList() {
    // return leaseLineService.findBy(LeaseLineViewDTO.class);
    // }

    @GetMapping
    public ResponseEntity<List<LeaseLineViewDTO>> getLeaseLines() {
        List<LeaseLineViewDTO> leaseLineViewDTOS = leaseLineService.findBy(LeaseLineViewDTO.class);
        return new ResponseEntity<>(leaseLineViewDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLeaseline(@PathVariable(name = "id") Integer id) {
        LeaseLineViewDTO leaseline = leaseLineService.findById(id, LeaseLineViewDTO.class);
        if (leaseline == null)
            throw new LeaselineNotFoundException("Kênh thuê không tồn tại trong hệ thống");
        return ResponseEntity.ok(leaseline);
    }

    @PostMapping
    public ResponseEntity<?> createLeaseline(@Valid @RequestBody LeaselineDTO leaselineDTO) {
        LeaseLine targetLeaseline = new LeaseLine();
        BeanUtils.copyProperties(leaselineDTO, targetLeaseline);
        leaseLineService.save(targetLeaseline);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFo(@Valid @RequestBody LeaselineDTO leaselineDTO,
            @PathVariable(name = "id") int id) {

        LeaseLine targetLeaseline = leaseLineService.findById(id);
        if (targetLeaseline == null)
            throw new RouterNotFoundException("Không tồn tại kênh thuê với id " + id + " trong hệ thống.");
        BeanUtils.copyProperties(leaselineDTO, targetLeaseline);
        leaseLineService.save(targetLeaseline);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLeaselineById(@PathVariable(name = "id") int id) {
        LeaseLine leaseline = leaseLineService.findById(id);
        if (leaseline == null) {
            throw new LeaselineNotFoundException("Kênh thuê không tồn tại");
        } else {
            leaseLineService.deleteById(id);
        }
        ;
        return ResponseEntity.ok("Đã xóa thành công kênh thuê");
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotalLeaseLines() {

        List<LeaseLineViewDTO> leaseLineViewDTOS = leaseLineService.findBy(LeaseLineViewDTO.class);
        return new ResponseEntity<>(leaseLineViewDTOS.size(), HttpStatus.OK);
    }

    @GetMapping("/totalCostPerMonth")
    public ResponseEntity<?> getTotalCost() {
        List<LeaseLine> leaseLines = leaseLineService.findAll();
        float cost = 0f;
        for (LeaseLine ll : leaseLines) {
            cost += ll.getCost() * ll.getQuantity();
        }

        return new ResponseEntity<>(cost, HttpStatus.OK);
    }

}
