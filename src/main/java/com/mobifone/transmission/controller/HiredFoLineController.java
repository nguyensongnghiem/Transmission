package com.mobifone.transmission.controller;

import com.mobifone.transmission.dto.HiredFoLineDTO;
import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.HiredFoLine;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.model.TransmissionOwner;
import com.mobifone.transmission.service.IFoContractService;
import com.mobifone.transmission.service.IHiredFoService;
import com.mobifone.transmission.service.ISiteService;
import com.mobifone.transmission.service.ITransmissionOwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/hired-fo")
@Controller
public class HiredFoLineController {
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IHiredFoService hiredFoService;

    @Autowired
    private ITransmissionOwnerService transmissionOwnerService;
    @Autowired
    private IFoContractService foContractService;

    @ModelAttribute("siteList")
    public List<Site> getSiteList() {
        return siteService.findAll();
    }
    @ModelAttribute("transOwners")
    public List<TransmissionOwner> getTransOwner(){
        return transmissionOwnerService.findAll();
    }
    @ModelAttribute("totalHiredFoLine")
    public int getTotalHiredFoLine(){
        return hiredFoService.getAllHiredFoLineViewDTO().size();
    }


    @GetMapping("/list")
    public String listByPageApi() {
        return "/hiredFo/hired-fo-list";
    }
// show the create form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("hiredFo", new HiredFoLine());
        return "/hiredFo/hired-fo-create";
    }

    //    save new  to DB
    @PostMapping("/create")
    public String create(@ModelAttribute HiredFoLine hiredFoLine) {
        hiredFoService.save(hiredFoLine);
        return "redirect:/hired-fo/list";
    }

    @PostMapping("/delete")
    public String deleteFoLine(@RequestParam(name = "deleteId") int deleteId) {
        foContractService.deleteById(deleteId);
        return "redirect:/hired-fo/list";
    }

    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable(name = "editId") int editId) {
        HiredFoLine hiredFoLine = hiredFoService.findById(editId,HiredFoLine.class);
        HiredFoLineDTO hiredFoLineDTO = new HiredFoLineDTO();
        BeanUtils.copyProperties(hiredFoLine,hiredFoLineDTO);
        model.addAttribute("hiredFoLineDTO", hiredFoLineDTO);
        return "hiredFo/hired-fo-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute HiredFoLineDTO hiredFoLineDTO, BindingResult bindingResult) {
        HiredFoLine hiredFoLine = new HiredFoLine();
        if (bindingResult.hasErrors()){
            return "hiredFo/hired-fo-edit";
        }
        BeanUtils.copyProperties(hiredFoLineDTO,hiredFoLine);
        hiredFoService.save(hiredFoLine);
        return "redirect:/hired-fo/list";
    }
    @GetMapping("/detail")
    public String showDetail(Model model, @RequestParam(required = false, defaultValue = "",name = "id") int id) {
        FoContractViewDTO foContract = foContractService.findViewDTOById(id);
        model.addAttribute("foContract", foContract);
        model.addAttribute("hiredFoList", hiredFoService.getHiredFoLineViewDTOByContractId(id));
        return "/contract/contract-detail";
    }

}

