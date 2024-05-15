package com.mobifone.transmission.controller;

import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ISiteOwnerService siteOwnerService;
    @Autowired
    private ISiteTransmissionTypeService siteTransmissionTypeService;
    @Autowired
    private ITransmissionOwnerService transmissionOwnerService;
    @ModelAttribute("transOwners")
    public List<TransmissionOwner> getTransOwner(){
        return transmissionOwnerService.findAll();
    }

    @ModelAttribute("provinces")
    public List<Province> getProvinces(){
        return provinceService.findAll();
    }

    @ModelAttribute("siteOwners")
    public List<SiteOwner> getSiteOwners(){
        return siteOwnerService.findAll();
    }
    @ModelAttribute("totalSites")
    public int getTotalSites(){
        return siteService.findAll().size();
    }

    @ModelAttribute("siteTransTypes")
    public List<SiteTransmissionType> getSiteTransType(){return siteTransmissionTypeService.findAll();}

//    @GetMapping("/list")
//    public String list(Model model) {
//        List<Site> siteList = siteService.findAll();
//        model.addAttribute("siteList", siteList);
//        return "/site/site-list";
//    }
    @GetMapping("/list")
    public String listByPage(Model model,
                             @RequestParam(required = false,defaultValue = "") String searchSiteId,
                             @RequestParam(required = false,defaultValue = "") String searchProvince,
                             @RequestParam(required = false,defaultValue = "0") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20);
        if (searchSiteId==null) searchSiteId="";
        if (searchProvince==null) searchProvince="";
//        Pageable pageable = Pageable.unpaged();
//        Page<Site> page = siteService.findAll(pageable);
//        Page<Site> page = siteService.findSiteBySiteIdContainingIgnoreCase(searchSiteId,pageable);
          Page<Site> page = siteService.findSitesBySiteIdAndProvince_Name(searchSiteId,searchProvince, pageable);


//        int totalPages = page.getTotalPages();
//        long totalElements = page.getTotalElements();
//        List<Site> siteList = page.getContent();
        model.addAttribute("page", page);
        model.addAttribute("searchSiteId", searchSiteId);
        model.addAttribute("searchProvince", searchProvince);
//        model.addAttribute("siteList", siteList);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalElements", totalElements);
        return "/site/site-list";
    }

// show the create form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("site",new Site());
        return "/site/site-create";
    }

//    save new site to DB
    @PostMapping("/create")
    public String create(@ModelAttribute Site site) {
        siteService.save(site);
        return "redirect:/site/list";
    }
    @PostMapping("/delete")
    public String deleteSite(@RequestParam Long deleteId) {
        siteService.deleteById(deleteId);
        return "redirect:/site/list";
    }
    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable Long editId) {
        Site site = siteService.findById(editId);
        model.addAttribute("site",site);
        return "/site/site-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Site site) {
        siteService.save(site);
        return "redirect:/site/list";
    }
    @GetMapping("/detail")
    public String showDetail(Model model, @RequestParam(required = false,defaultValue = "") String siteId) {
        Site site = siteService.findSitesBySiteId(siteId);
        model.addAttribute("site",site);
        return "/site/site-detail";
    }

}

