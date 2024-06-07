package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.exception.InvalidFileTypeException;
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
    @Autowired
    private ExcelUploadService excelUploadService;

    @Override
    public void uploadExcelFile(MultipartFile file) {
        if (excelUploadService.isValidExcelFile(file)) {
            try {
                List<HiredFoLine> hiredFoLines = excelUploadService.getHiredFoDataFromExcel(file.getInputStream());
                hiredFoLineRepository.saveAll(hiredFoLines);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to save file to database.");
            }
        } else {
            throw new InvalidFileTypeException("Invalid excel file.");

        }
    }

    @Override
    public List<HiredFoLineViewDTO> getAllHiredFoLineViewDTO() {
        return hiredFoLineRepository.findBy(HiredFoLineViewDTO.class);
    }

    @Override
    public HiredFoLineViewDTO getHiredFoLineViewDTOById(int id) {
        return hiredFoLineRepository.findById(id, HiredFoLineViewDTO.class);
    }

    @Override
    public List<HiredFoLineViewDTO> getHiredFoLineViewDTOByContractNumber(String contractNumber) {
        return hiredFoLineRepository.findByFoContract_ContractNumber(contractNumber,HiredFoLineViewDTO.class);
    }

    @Override
    public List<HiredFoLineViewDTO> getHiredFoLineViewDTOByContractId(int id) {
        return hiredFoLineRepository.findByFoContract_Id(id, HiredFoLineViewDTO.class);
    }
}
