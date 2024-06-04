package com.mobifone.transmission.controller;

import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/contract")
@Controller
public class FoContractController {
    @Autowired
    private ISiteService siteService;

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
    @ModelAttribute("totalContract")
    public int getTotalContract(){
        return foContractService.findAll().size();
    }


    @GetMapping("/list")
    public String listByPageApi() {
        return "/contract/contract-list";
    }
// show the create form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contract", new FoContract());
        return "/contract/contract-create";
    }

    //    save new  to DB
    @PostMapping("/create")
    public String create(@ModelAttribute FoContract foContract) {
        foContractService.save(foContract);
        return "redirect:/contract/list";
    }

    @PostMapping("/delete")
    public String deleteRouter(@RequestParam(name = "deleteId") int deleteId) {
        foContractService.deleteById(deleteId);
        return "redirect:/leaseline/list";
    }

    @GetMapping("/edit/{editId}")
    public String showEditForm(Model model, @PathVariable(name = "editId") int editId) {
        Optional<FoContract> foContract = foContractService.findById(editId);
        model.addAttribute("leaseLine", foContract.get());
        return "contract/contract-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute FoContract contract) {
        foContractService.save(contract);
        return "redirect:/contract/list";
    }

}

