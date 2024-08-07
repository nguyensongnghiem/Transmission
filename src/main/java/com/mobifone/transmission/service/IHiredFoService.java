package com.mobifone.transmission.service;

import com.mobifone.transmission.dto.HiredFoLineDTO;
import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.model.HiredFoLine;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHiredFoService {
    public void uploadExcelFile(MultipartFile file);
    public List<HiredFoLineViewDTO> getAllHiredFoLineViewDTO();
    public HiredFoLineViewDTO getHiredFoLineViewDTOById(int id);
    public List<HiredFoLineViewDTO> getHiredFoLineViewDTOByContractNumber(String contractNumber);

    List<HiredFoLineViewDTO> getHiredFoLineViewDTOByContractId(int id);

    void save(HiredFoLine hiredFoLine);

    public <T> T findById(int editId, Class<T> classType);
}
