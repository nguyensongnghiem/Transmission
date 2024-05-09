package com.mobifone.transmission.controller;

import com.mobifone.transmission.model.*;
import com.mobifone.transmission.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ISiteOwnerService siteOwnerService;
    @Autowired
    private IRouterService routerService;
//    @Autowired
//    private IRouterTypeService routerTypeService;
//    @Autowired
//    private ITransmissionDeviceTypeService transmissionDeviceTypeService;
    @Autowired
    private ITransmissionOwnerService transmissionOwnerService;
    @Autowired
    private ISiteTransmissionTypeService siteTransmissionTypeService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Định dạng chuỗi rỗng thành null khi binding
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    @ModelAttribute("provinces")
    public List<Province> getProvinces(){
        return provinceService.findAll();
    }
    @ModelAttribute("transOwners")
    public List<TransmissionOwner> getTransOwner(){
        return transmissionOwnerService.findAll();
    }
    @ModelAttribute("siteTransTypes")
    public List<SiteTransmissionType> getSiteTransType(){return siteTransmissionTypeService.findAll();}
    @ModelAttribute("siteOwners")
    public List<SiteOwner> getSiteOwners(){
        return siteOwnerService.findAll();
    }
    @ModelAttribute("totalSites")
    public int getTotalSites(){
        return siteService.findAll().size();
    }
    @ModelAttribute("totalFoSites")
    public int getTotalFoSites(){
        int totalFoSites = siteTransmissionTypeService.findById(1).getSiteList().size()
                + siteTransmissionTypeService.findById(2).getSiteList().size() ;
        return totalFoSites;
    }
    @ModelAttribute("totalLeaseLineCostPerMonth")
    public int getTotalLeaseLineCostPerMonth(){
        List<LeaseLine> leaseLines = leaseLineService.findAll();
        int cost =0 ;
        for (LeaseLine ll : leaseLines) {
            cost += ll.getCost();
        }
        return cost;
    }

    @ModelAttribute("totalRouters")
    public int getTotalRouter(){
        return routerService.findAll().size();
    }
    @Autowired
    private ILeaseLineService leaseLineService;
    @Autowired
    private ILeaseLineConnectTypeService leaseLineConnectTypeService;

    @GetMapping("/")
    public String home(Model model) {
        List<String>  provinceLabels = getProvincesLabel();
        List<Integer> siteChartData  = getSiteChartData();
        model.addAttribute("provinceLabels",provinceLabels);
        model.addAttribute("siteChartData",siteChartData);
        model.addAttribute("transTypeLabels",getTransTypeLabel());
        model.addAttribute("transTypeData",getTransTypeData());
        return "index";
    }
//    Dữ liệu cho Site Chart
    private List<String> getProvincesLabel() {
        List<String> labels = new ArrayList<>();
        for (Province province: getProvinces()) {
            labels.add(province.getName());
        }
        return labels;
    }
    private List<Integer> getSiteChartData() {
        List<Integer> siteNumber = new ArrayList<>();
        for (Province province: getProvinces()) {
            siteNumber.add(province.getSiteList().size());
        }
        return siteNumber;
    }

//    Dữ liệu cho Trans Type Chart
    private List<String> getTransTypeLabel() {
        List<String> labels = new ArrayList<>();
        for (SiteTransmissionType siteTransmissionType: getSiteTransType()) {
            labels.add(siteTransmissionType.getName());
        }
        return labels;
    }
    private List<Integer> getTransTypeData() {
        List<Integer> siteNumber = new ArrayList<>();
        for (SiteTransmissionType siteTransmissionType: getSiteTransType()) {
            siteNumber.add(siteTransmissionType.getSiteList().size());
        }
        return siteNumber;
    }


}
