package com.mobifone.transmission.service;

import com.mobifone.transmission.model.HiredFoLine;
import com.mobifone.transmission.repository.IFoContractRepository;
import com.mobifone.transmission.repository.ISiteRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
@Service
public class ExcelUploadService {

    @Autowired
    private  IFoContractRepository foContractRepository;
    @Autowired
    private  ISiteRepository siteRepository;
    public boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
    public List<HiredFoLine> getHiredFoDataFromExcel(InputStream inputStream){
        List<HiredFoLine> hiredFoLines = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("data");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                HiredFoLine hiredFoLine = new HiredFoLine();
                for (int i = 0; i<row.getLastCellNum() ; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch (i){
                        case 0 -> hiredFoLine.setFoContract(foContractRepository.findByContractNumber(cell.getStringCellValue()));
                        case 1 -> hiredFoLine.setNearSite(siteRepository.findSitesBySiteId(cell.getStringCellValue()));
                        case 2 -> hiredFoLine.setFarSite(siteRepository.findSitesBySiteId(cell.getStringCellValue()));
                        case 3 -> hiredFoLine.setCoreQuantity((int) cell.getNumericCellValue());
                        case 4 -> hiredFoLine.setDesignedDistance((float) cell.getNumericCellValue());
                        case 5 -> hiredFoLine.setFinalDistance((float) cell.getNumericCellValue());
                        case 6 -> hiredFoLine.setCost((int) cell.getNumericCellValue());
                        case 7 -> hiredFoLine.setNote(cell.getStringCellValue());
                        default -> {
                        }
                    }
                }
                hiredFoLines.add(hiredFoLine);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return hiredFoLines;
    }
}
