package com.mobifone.transmission.controller;

import com.mobifone.transmission.dto.FoContractDTO;
import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.exception.InvalidFileTypeException;
import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;
import com.mobifone.transmission.validator.FoContractCreationValidator;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/contract")
@Controller
public class FoContractController {
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IHiredFoService hiredFoService;

    @Autowired
    private ITransmissionOwnerService transmissionOwnerService;
    @Autowired
    private IFoContractService foContractService;
    @Autowired
    private ExcelUploadService excelUploadService;
    @Autowired
    private FoContractCreationValidator foContractCreationValidator;
    @ModelAttribute("siteList")
    public List<Site> getSiteList() {
        return siteService.findAll();
    }
    @ModelAttribute("transOwners")
    public List<TransmissionOwner> getTransOwner(){
        return transmissionOwnerService.findAll();
    }
    @ModelAttribute("totalContract")
    public int getTotalContract(){
        return foContractService.findAllViewDTO().size();
    }


    @GetMapping("/list")
    public String listByPageApi() {
        return "/contract/contract-list";
    }
// show the create form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("foContractDTO", new FoContractDTO());
        return "/contract/contract-create";
    }

    //    save new  to DB
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute FoContractDTO foContractDTO, BindingResult bindingResult, @RequestParam MultipartFile file) {
        FoContract targetFoContract = new FoContract();
        foContractCreationValidator.validate(foContractDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return "contract/contract-create";
        }
        BeanUtils.copyProperties(foContractDTO,targetFoContract);
        if (excelUploadService.isValidExcelFile(file)) {
            try {
                List<HiredFoLine> hiredFoLines = excelUploadService.getHiredFoDataFromExcel(file.getInputStream());
                hiredFoLines.forEach(hiredFoLine -> { hiredFoLine.setFoContract(targetFoContract);
                });
                targetFoContract.setHiredFoLineList(hiredFoLines);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to save file to database.");
            }
        } else {
            throw new InvalidFileTypeException("Invalid excel file.");

        }

        foContractService.save(targetFoContract);
        return "redirect:/contract/list";
    }

    @PostMapping("/delete")
    public String deleteRouter(@RequestParam(name = "deleteId") int deleteId) {
        foContractService.deleteById(deleteId);
        return "redirect:/leaseline/list";
    }

    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable(name = "editId") int editId) {

        FoContract foContract = foContractService.findById(editId);
        FoContractDTO foContractDTO = new FoContractDTO();
        BeanUtils.copyProperties(foContract,foContractDTO);
        model.addAttribute("foContractDTO", foContractDTO);
        return "contract/contract-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute FoContractDTO foContractDTO, BindingResult bindingResult) {
        FoContract targetFoContract = new FoContract();

        if (bindingResult.hasErrors()){
            return "contract/contract-edit";
        }
        BeanUtils.copyProperties(foContractDTO,targetFoContract);
        foContractService.save(targetFoContract);
        return "redirect:/contract/list";
    }
    @GetMapping("/detail")
    public String showDetail(Model model, @RequestParam(required = false, defaultValue = "",name = "id") int id) {
        FoContractViewDTO foContract = foContractService.findViewDTOById(id);
        List<HiredFoLineViewDTO> hiredFoList = hiredFoService.getHiredFoLineViewDTOByContractId(id);
        double totalLength = hiredFoList.stream().mapToDouble(HiredFoLineViewDTO::getFinalDistance).sum();
        double totalCostPerMonth = hiredFoList.stream().mapToDouble(h-> h.getCost()*h.getFinalDistance()).sum();
        model.addAttribute("hiredFoList", hiredFoList);
        model.addAttribute ("foContract", foContract);
        model.addAttribute ("totalLength", totalLength);
        model.addAttribute ("totalCostPerMonth", totalCostPerMonth);
        return "/contract/contract-detail";
    }
    @GetMapping("/download-template")
    public ResponseEntity<Resource> downloadTemplate() {
        // Tạo một file Excel template (ví dụ: template.xlsx)
        ClassPathResource resource = new ClassPathResource("/static/assets/template/hired-fo-import-template.xlsx");

        // Thiết lập header
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fo-template.xlsx");

        // Trả về file cho client
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}

