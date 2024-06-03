package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.HiredFoLine;
import com.mobifone.transmission.repository.IHiredFoLineRepository;
import com.mobifone.transmission.service.ExcelUploadService;
import com.mobifone.transmission.service.IHiredFoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class HiredFoService implements IHiredFoService {
    @Autowired
    private IHiredFoLineRepository hiredFoLineRepository;
    @Override
    public void uploadExcelFile(MultipartFile file) {
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<HiredFoLine> hiredFoLines = ExcelUploadService.getHiredFoDataFromExcel(file.getInputStream());
                hiredFoLineRepository.saveAll(hiredFoLines);
            } catch (IOException e) {
                throw new IllegalArgumentException("Không phải file Excel");
            }
        }
    }
}
