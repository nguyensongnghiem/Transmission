package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.FoContractCreateDTO;
import com.mobifone.transmission.dto.FoContractUpdateDTO;
import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.exception.ErrorResponse;

import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.service.IFoContractService;
import com.mobifone.transmission.service.IHiredFoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// @CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contracts")
public class FoContractRestController {
    @Autowired
    private IFoContractService contractService;
    @Autowired
    private IHiredFoService hiredFoService;
    // @Autowired
    // private RouterMapper foContractMapper;

    @GetMapping
    public ResponseEntity<Object> getAllContracts() {
        try {
            List<FoContractViewDTO> foContracts = contractService.findAllViewDTO();
            if (foContracts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(foContracts, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContractDetail(@PathVariable("id") int id) {
        try {
            FoContractViewDTO foContract = contractService.findById(id, FoContractViewDTO.class);
            if (foContract == null) {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                        String.format("Không có hợp đồng với id %d", id));
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(foContract, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}/hired-fo")
    public ResponseEntity<Object> getHiredFoByContractId(@PathVariable("id") int id) {
        try {
            List<HiredFoLineViewDTO> hiredFoLineViewDTOList = hiredFoService.getHiredFoLineViewDTOByContractId(id);
            if (hiredFoLineViewDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(hiredFoLineViewDTOList, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContract(@Valid @RequestBody FoContractUpdateDTO foContractDTO,
            @PathVariable("id") int id) {

        FoContract targetFoContract = contractService.findById(id, FoContract.class);
        if (targetFoContract == null) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                    String.format("Không có hợp đồng với id %d", id));
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(foContractDTO, targetFoContract);
        contractService.save(targetFoContract);
        return ResponseEntity.ok("Đã cập nhật thành công hợp đồng");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoContractById(@PathVariable("id") int id) {
        // HiredFoLineViewDTO hiredFoLineViewDTO = hiredFoService.findById(id,
        // HiredFoLineViewDTO.class);
        // if (hiredFoLineViewDTO==null) {throw new SiteNotFoundException("Site ID không
        // tồn tại !");}
        // else {
        contractService.deleteById(id);
        // };
        return ResponseEntity.ok("Đã xóa thành công Hợp đồng FO thuê");
    }

    @PostMapping
    public ResponseEntity<?> createFoContract(@Valid @RequestBody FoContractCreateDTO foContractCreateDTO) {
        FoContract targetContract = new FoContract();
        BeanUtils.copyProperties(foContractCreateDTO, targetContract);
        System.out.println("Đã tạo hợp đồng : " + targetContract.toString());
        contractService.save(targetContract);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(targetContract.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
