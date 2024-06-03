package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.service.IHiredFoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequestMapping("/api/hiredFo")
@RestController
public class HiredFoController {
    @Autowired
    private IHiredFoService hiredFoService;
    @PostMapping("/upload-excel")
    public ResponseEntity<?> updateExcelFile(MultipartFile file) {
        hiredFoService.uploadExcelFile(file);
        return ResponseEntity.ok(Map.of("message" , "Danh sách tuyến cáp thuê đã được upload và cập nhật thành công"));
    }

}
