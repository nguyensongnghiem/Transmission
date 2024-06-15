package com.mobifone.transmission.controller;

import com.mobifone.transmission.dto.SiteDTO;
//import com.mobifone.transmission.mapper.SiteMapper;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;
import com.mobifone.transmission.validator.SiteCreationValidator;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @Autowired
    private SiteCreationValidator siteCreationValidator;


    @ModelAttribute("transOwners")
    public List<TransmissionOwner> getTransOwner() {
        return transmissionOwnerService.findAll();
    }

    @ModelAttribute("provinces")
    public List<Province> getProvinces() {
        return provinceService.findAll();
    }

    @ModelAttribute("siteOwners")
    public List<SiteOwner> getSiteOwners() {
        return siteOwnerService.findAll();
    }

    @ModelAttribute("totalSites")
    public int getTotalSites() {
        return siteService.findAll().size();
    }

    @ModelAttribute("siteTransTypes")
    public List<SiteTransmissionType> getSiteTransType() {
        return siteTransmissionTypeService.findAll();
    }

    //    @GetMapping("/list")
//    public String list(Model model) {
//        List<Site> siteList = siteService.findAll();
//        model.addAttribute("siteList", siteList);
//        return "/site/site-list";
//    }
    @GetMapping("/list-old")
    public String listByPage(
            Model model,
            @RequestParam(required = false, defaultValue = "", name = "searchSiteId") String searchSiteId,
            @RequestParam(required = false, defaultValue = "",name = "searchProvince") String searchProvince,
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") int pageNumber) {
//        List<Site> siteList = siteService.findAll();

//        List<SiteViewDTO> siteViewDTOList= new ArrayList<>();
//        for (Site site:siteList) {
//            siteViewDTOList.add(SiteMapper.toSiteViewDTO(site));
//        }

        Pageable pageable = PageRequest.of(pageNumber, 15);
        if (searchSiteId == null) searchSiteId = "";
        if (searchProvince == null) searchProvince = "";
//        Pageable pageable = Pageable.unpaged();
//        Page<Site> page = siteService.findAll(pageable);
//        Page<Site> page = siteService.findSiteBySiteIdContainingIgnoreCase(searchSiteId,pageable);
        Page<SiteViewDTO> page = siteService.searchBySiteIdAndProvince(searchSiteId, searchProvince, pageable,SiteViewDTO.class);
//
        model.addAttribute("page", page);
        model.addAttribute("searchSiteId", searchSiteId);
        model.addAttribute("searchProvince", searchProvince);
//        model.addAttribute("siteList", siteList);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalElements", totalElements);
        return "site-list-old";
    }

    @GetMapping("/list")
    public String listByPageApi(Model model) {
//        Pageable pageable = PageRequest.of(pageNumber, 15);
//        if (searchSiteId == null) searchSiteId = "";
//        if (searchProvince == null) searchProvince = "";
//        Page<SiteViewDTO> page = siteService.searchBySiteIdAndProvince(searchSiteId, searchProvince, pageable,SiteViewDTO.class);
//        model.addAttribute("page", page);
//        model.addAttribute("searchSiteId", searchSiteId);
//        model.addAttribute("searchProvince", searchProvince);
        return "/site/site-list";
    }

    // show the create form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("siteDTO", new SiteDTO());
        return "/site/site-create";
    }

    //    save new site to DB
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute SiteDTO siteDTO, BindingResult bindingResult) {
        Site targetSite = new Site();
        siteCreationValidator.validate(siteDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return "site/site-create";
        }
        BeanUtils.copyProperties(siteDTO,targetSite);
        siteService.save(targetSite);
        return "redirect:/site/list";
    }

    @PostMapping("/delete")
    public String deleteSite(@RequestParam(name = "deleteId") Long deleteId) {
        siteService.deleteById(deleteId);
        return "redirect:/site/list";
    }

    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable(name = "editId") Long editId) {
        Site site = siteService.findById(editId, Site.class);
        SiteDTO siteDTO = new SiteDTO();
        BeanUtils.copyProperties(site,siteDTO);
        model.addAttribute("siteDTO", siteDTO);
        return "/site/site-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute SiteDTO siteDTO, BindingResult bindingResult) {
        Site targetSite = new Site();
//        new SiteCreationValidator().validate(siteDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return "site/site-edit";
        }
        BeanUtils.copyProperties(siteDTO,targetSite);
        siteService.save(targetSite);
        return "redirect:/site/list";
    }

    @GetMapping("/detail")
    public String showDetail(Model model, @RequestParam(required = false, defaultValue = "",name = "siteId") String siteId) {
        Site site = siteService.findSitesBySiteId(siteId);
        model.addAttribute("site", site);
        model.addAttribute("siteList", siteService.findAll());
        return "/site/site-detail";
    }
public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return "Anonymous";
    }

}

