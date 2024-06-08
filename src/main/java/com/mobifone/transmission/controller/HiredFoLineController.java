package com.mobifone.transmission.controller;

import com.mobifone.transmission.dto.inf.FoContractViewDTO;
import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.HiredFoLine;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.model.TransmissionOwner;
import com.mobifone.transmission.service.IFoContractService;
import com.mobifone.transmission.service.IHiredFoService;
import com.mobifone.transmission.service.ISiteService;
import com.mobifone.transmission.service.ITransmissionOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "redirect:/hired-fo-list/hired-fo-list";
    }

    @PostMapping("/delete")
    public String deleteRouter(@RequestParam(name = "deleteId") int deleteId) {
        foContractService.deleteById(deleteId);
        return "redirect:/leaseline/list";
    }

    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable(name = "editId") int editId) {
        FoContractViewDTO foContract = foContractService.findViewDTOById(editId);
        model.addAttribute("foContract", foContract);
        return "contract/contract-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute FoContract contract) {
        foContractService.save(contract);
        return "redirect:/contract/list";
    }
    @GetMapping("/detail")
    public String showDetail(Model model, @RequestParam(required = false, defaultValue = "",name = "id") int id) {
        FoContractViewDTO foContract = foContractService.findViewDTOById(id);
        model.addAttribute("foContract", foContract);
        model.addAttribute("hiredFoList", hiredFoService.getHiredFoLineViewDTOByContractId(id));
        return "/contract/contract-detail";
    }

}

