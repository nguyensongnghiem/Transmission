package com.mobifone.transmission.controller;

import com.mobifone.transmission.dto.RouterDTO;
import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;
import com.mobifone.transmission.validator.RouterCreationValidator;
import com.mobifone.transmission.validator.RouterEditValidator;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private RouterCreationValidator routerCreationValidator;
    @Autowired
    private RouterEditValidator routerEditValidator;


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
        routerCreationValidator.validate(routerDTO,bindingResult);
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
        RouterDTO routerDTO = new RouterDTO();
        RouterDTO oldRouterDTO = new RouterDTO();
        BeanUtils.copyProperties(router,routerDTO);
        BeanUtils.copyProperties(router,oldRouterDTO);
        model.addAttribute("routerDTO",routerDTO);
        model.addAttribute("oldRouterDTO",oldRouterDTO);

        return "router/router-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute RouterDTO routerDTO, BindingResult bindingResult) {
        Router targetRouter = new Router();
        routerEditValidator.validate(routerDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return "router/router-edit";
        }
        BeanUtils.copyProperties(routerDTO,targetRouter);
        routerService.save(targetRouter);
        return "redirect:/router/list";
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

