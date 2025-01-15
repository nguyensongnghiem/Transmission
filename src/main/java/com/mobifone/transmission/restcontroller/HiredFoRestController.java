package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.HiredFoLineDTO;
import com.mobifone.transmission.dto.LeaselineDTO;
import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.dto.inf.LeaseLineViewDTO;
import com.mobifone.transmission.exception.FoNotFoundException;
import com.mobifone.transmission.exception.LeaselineNotFoundException;
import com.mobifone.transmission.exception.RouterNotFoundException;
import com.mobifone.transmission.exception.SiteNotFoundException;
import com.mobifone.transmission.model.HiredFoLine;
import com.mobifone.transmission.model.LeaseLine;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.IHiredFoService;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequestMapping("api/hired-fos")
@RestController
public class HiredFoRestController {
    @Autowired
    private IHiredFoService hiredFoService;

    @PostMapping("/upload-excel")
    public ResponseEntity<?> updateExcelFile(MultipartFile file) {
        hiredFoService.uploadExcelFile(file);
        return ResponseEntity.ok(Map.of("message", "Danh sách tuyến cáp thuê đã được upload và cập nhật thành công"));
    }

    @GetMapping
    public ResponseEntity<?> getAllHiredFoLines() {
        List<HiredFoLineViewDTO> hiredFoLineViewDTOList = hiredFoService.getAllHiredFoLineViewDTO();
        return ResponseEntity.ok(hiredFoLineViewDTOList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getLeaseline(@PathVariable(name = "id") Integer id) {
        HiredFoLineViewDTO foLine = hiredFoService.findById(id, HiredFoLineViewDTO.class);
        if (foLine == null)
            throw new FoNotFoundException("Tuyến FO không tồn tại trong hệ thống");
        return ResponseEntity.ok(foLine);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFo(@Valid @RequestBody HiredFoLineDTO foLineDTO,
            @PathVariable(name = "id") int id) {

        HiredFoLine targetFoline = hiredFoService.findById(id,  HiredFoLine.class);
        if (targetFoline == null)
            throw new FoNotFoundException("Không tồn tại tuyến FO thuê với id " + id + " trong hệ thống.");
        BeanUtils.copyProperties(foLineDTO, targetFoline);
        hiredFoService.save(targetFoline);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHiredFoById(@PathVariable(name = "id") int id) {
        // HiredFoLineViewDTO hiredFoLineViewDTO = hiredFoService.findById(id,
        // HiredFoLineViewDTO.class);
        // if (hiredFoLineViewDTO==null) {throw new SiteNotFoundException("Site ID không
        // tồn tại !");}
        // else {
        hiredFoService.deleteById(id);
        // };
        return ResponseEntity.ok("Đã xóa thành công tuyến FO thuê");
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> getAllDeletedHiredFoLines() {
        List<HiredFoLine> hiredFoLines = hiredFoService.findAllDeletedFo();
        return ResponseEntity.ok(hiredFoLines);
    }

}
