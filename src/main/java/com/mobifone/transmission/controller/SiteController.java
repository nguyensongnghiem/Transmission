package com.mobifone.transmission.controller;

import com.mobifone.transmission.model.Province;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.model.SiteOwner;
import com.mobifone.transmission.service.IProvinceService;
import com.mobifone.transmission.service.ISiteOwnerService;
import com.mobifone.transmission.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SiteController {
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ISiteOwnerService siteOwnerService;

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

    @GetMapping("/site/list")
    public String list(Model model) {
        List<Site> siteList = siteService.findAll();
        model.addAttribute("siteList", siteList);
        return "/site/site-list";
    }
    @GetMapping("/site/list/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Site> page = siteService.findAll(currentPage);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<Site> siteList = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("siteList", siteList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        return "/site/site-list";
    }

// show the create form
    @GetMapping("/site/create")
    public String showCreateForm(Model model) {
        model.addAttribute("site",new Site());
        return "/site/site-create";
    }

//    save new site to DB
    @PostMapping("/site/create")
    public String create(@ModelAttribute Site site) {
        siteService.save(site);
        return "redirect:/site/list";
    }
    @PostMapping("/site/delete")
    public String deleteSite(@RequestParam Long deleteId) {
        siteService.deleteById(deleteId);
        return "redirect:/site/list";
    }
    @GetMapping("/site/edit/{editId}")
    public String showEditForm(Model model, @PathVariable Long editId) {
        Site site = siteService.findById(editId);
        model.addAttribute("site",site);
        return "/site/site-edit";
    }

    @PostMapping("/site/edit")
    public String edit(@ModelAttribute Site site) {
        siteService.save(site);
        return "redirect:/site/list";
    }
}

