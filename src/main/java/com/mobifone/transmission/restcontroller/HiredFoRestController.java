package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.exception.SiteNotFoundException;
import com.mobifone.transmission.model.HiredFoLine;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.IHiredFoService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(Map.of("message" , "Danh sách tuyến cáp thuê đã được upload và cập nhật thành công"));
    }
    @GetMapping
    public ResponseEntity<?> getAllHiredFoLines() {
        List<HiredFoLineViewDTO> hiredFoLineViewDTOList = hiredFoService.getAllHiredFoLineViewDTO();
        return ResponseEntity.ok(hiredFoLineViewDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHiredFoById(@PathVariable int id) {
//        HiredFoLineViewDTO hiredFoLineViewDTO = hiredFoService.findById(id, HiredFoLineViewDTO.class);
//        if (hiredFoLineViewDTO==null) {throw new SiteNotFoundException("Site ID không tồn tại !");}
//        else {
            hiredFoService.deleteById(id);
//        };
        return ResponseEntity.ok("Đã xóa thành công tuyến FO thuê");
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> getAllDeletedHiredFoLines() {
        List<HiredFoLine> hiredFoLines = hiredFoService.findAllDeletedFo();
        return ResponseEntity.ok(hiredFoLines);
    }


}
