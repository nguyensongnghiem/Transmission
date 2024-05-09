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
    //    @Autowired
//    private IProvinceService provinceService;
//    @Autowired
//    private ISiteOwnerService siteOwnerService;
//    @Autowired
//    private IRouterTypeService routerTypeService;
//    @Autowired
//    private ITransmissionDeviceTypeService transmissionDeviceTypeService;
//    @Autowired
//    private IRouterService routerService;
    @Autowired
    private ILeaseLineService leaseLineService;
    @Autowired
    private ILeaseLineConnectTypeService leaseLineConnectTypeService;
    @Autowired
    private ITransmissionOwnerService transmissionOwnerService;

    //    @ModelAttribute("provinces")
//    public List<Province> getProvinces(){
//        return provinceService.findAll();
//    }
//    @ModelAttribute("siteOwners")
//    public List<SiteOwner> getSiteOwners(){
//        return siteOwnerService.findAll();
//    }
//    @ModelAttribute("totalSites")
//    public int getTotalSites(){
//        return siteService.findAll().size();
//    }
    @ModelAttribute("siteList")
    public List<Site> getSiteList() {
        return siteService.findAll();
    }
    @ModelAttribute("transOwners")
    public List<TransmissionOwner> getTransOwner(){
        return transmissionOwnerService.findAll();
    }

    //    @ModelAttribute("routerTypes")
//    public List<RouterType> getRouterTypes(){
//        return routerTypeService.findAll();
//    }
//    @ModelAttribute("transDevTypes")
//    public List<TransmissionDeviceType> getTransDevTypes(){
//        return transmissionDeviceTypeService.findAll();
//    }
    @ModelAttribute("leaseLineConnectTypes")
    public List<LeaseLineConnectType> getLeaseLineConnectTypes() {
        return leaseLineConnectTypeService.findAll();
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<LeaseLine> leaseLineList = leaseLineService.findAll();
        model.addAttribute("leaseLineList", leaseLineList);
        return "/leaseline/leaseline-list";
    }

    //    @GetMapping("/site/list/{pageNumber}")
//    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {
//        Page<Site> page = siteService.findAll(currentPage);
//        int totalPages = page.getTotalPages();
//        long totalElements = page.getTotalElements();
//        List<Site> siteList = page.getContent();
//        model.addAttribute("currentPage", currentPage);
//        model.addAttribute("siteList", siteList);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalElements", totalElements);
//        return "/site/site-list";
//    }
//
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
    public String deleteRouter(@RequestParam int deleteId) {
        leaseLineService.deleteById(deleteId);
        return "redirect:/leaseline/list";
    }

    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable int editId) {
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

