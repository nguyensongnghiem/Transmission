package com.mobifone.transmission.controller;

import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/leaseline")
@Controller
public class LeaseLineController {
    @Autowired
    private ISiteService siteService;

    @Autowired
    private ILeaseLineService leaseLineService;
    @Autowired
    private ILeaseLineConnectTypeService leaseLineConnectTypeService;
    @Autowired
    private ITransmissionOwnerService transmissionOwnerService;

    @ModelAttribute("siteList")
    public List<Site> getSiteList() {
        return siteService.findAll();
    }
    @ModelAttribute("transOwners")
    public List<TransmissionOwner> getTransOwner(){
        return transmissionOwnerService.findAll();
    }
    @ModelAttribute("totalLeaseLine")
    public int getTotalLeaseLine(){
        return leaseLineService.findAll().size();
    }

    @ModelAttribute("leaseLineConnectTypes")
    public List<LeaseLineConnectType> getLeaseLineConnectTypes() {
        return leaseLineConnectTypeService.findAll();
    }

    @GetMapping("/list-old")
    public String list(Model model) {
        List<LeaseLine> leaseLineList = leaseLineService.findAll();
        model.addAttribute("leaseLineList", leaseLineList);
        return "leaseline-list-old";
    }
    @GetMapping("/list")
    public String listByPageApi() {
        return "/leaseline/leaseline-list";
    }
// show the create form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("leaseLine", new LeaseLine());
        return "/leaseline/leaseline-create";
    }

    //    save new  to DB
    @PostMapping("/create")
    public String create(@ModelAttribute LeaseLine leaseLine) {
        leaseLineService.save(leaseLine);
        return "redirect:/leaseline/list";
    }

    @PostMapping("/delete")
    public String deleteRouter(@RequestParam(name = "deleteId") int deleteId) {
        leaseLineService.deleteById(deleteId);
        return "redirect:/leaseline/list";
    }

    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable(name = "editId") int editId) {
        LeaseLine leaseLine = leaseLineService.findById(editId);
        model.addAttribute("leaseLine", leaseLine);
        return "leaseline/leaseline-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute LeaseLine leaseLine) {
        leaseLineService.save(leaseLine);
        return "redirect:/leaseline/list";
    }
}

