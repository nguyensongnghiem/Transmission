package com.mobifone.transmission.controller;

import com.mobifone.transmission.dto.RouterDTO;
import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/router")
@Controller
public class RouterController {
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ISiteOwnerService siteOwnerService;
    @Autowired
    private IRouterTypeService routerTypeService;
    @Autowired
    private ITransmissionDeviceTypeService transmissionDeviceTypeService;
    @Autowired
    private IRouterService routerService;



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
    @ModelAttribute("siteList")
    public List<Site> getSiteList(){
        return siteService.findAll();
    }
    @ModelAttribute("routerTypes")
    public List<RouterType> getRouterTypes(){
        return routerTypeService.findAll();
    }
    @ModelAttribute("transDevTypes")
    public List<TransmissionDeviceType> getTransDevTypes(){
        return transmissionDeviceTypeService.findAll();
    }

    @GetMapping("/list-old")
    public String list(Model model) {
        List<Router> routerList = routerService.findAll();
        List<RouterViewDTO> routerViewDTOList = routerService.findBy(RouterViewDTO.class);
        model.addAttribute("routerList", routerViewDTOList);
        return "router-list-old";
    }

    @GetMapping("/list")
    public String listByPageApi(Model model) {
        model.addAttribute("totalRouters", routerService.findAll().size());
        return "/router/router-list";
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
        model.addAttribute("routerDTO",new RouterDTO());
        return "/router/router-create";
    }

//    save new site to DB
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute RouterDTO routerDTO, BindingResult bindingResult) {
        Router targetRouter =new Router();
        if (bindingResult.hasErrors()){
            return "router/router-create";
        }
        BeanUtils.copyProperties(routerDTO,targetRouter);
        routerService.save(targetRouter);
        return "redirect:/router/list";
    }
    @PostMapping("/delete")
    public String deleteRouter(@RequestParam(name= "deleteId") Long deleteId) {
        routerService.deleteById(deleteId);
        return "redirect:/router/list";
    }
    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable(name = "editId") Long editId) {
        Router router = routerService.findById(editId);
        model.addAttribute("router",router);
        return "router/router-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Router router) {
        routerService.save(router);
        return "redirect:/router/list";
    }
}

