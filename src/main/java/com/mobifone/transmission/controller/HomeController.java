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
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Định dạng chuỗi rỗng thành null khi binding
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ISiteOwnerService siteOwnerService;
    @Autowired
    private IRouterService routerService;

    @Autowired
    private ITransmissionOwnerService transmissionOwnerService;
    @Autowired
    private ISiteTransmissionTypeService siteTransmissionTypeService;

    @Autowired
    private ILeaseLineService leaseLineService;
    @Autowired
    private ILeaseLineConnectTypeService leaseLineConnectTypeService;





    @GetMapping("/")
    public String home(Model model) {
        List<String>  provinceLabels = getProvincesLabel();
        List<Integer> siteChartData  = getSiteChartData();

        model.addAttribute("provinces",provinceService.findAll());
        model.addAttribute("transOwners",transmissionOwnerService.findAll());
        model.addAttribute("siteTransTypes",siteTransmissionTypeService.findAll());
        model.addAttribute("siteOwners",siteOwnerService.findAll());
        model.addAttribute("totalSites",siteService.findAll().size());
        model.addAttribute("totalFoSites",siteTransmissionTypeService.findById(1).getSiteList().size()
                + siteTransmissionTypeService.findById(2).getSiteList().size() );
        List<LeaseLine> leaseLines = leaseLineService.findAll();
        float cost = 0f ;
        for (LeaseLine ll : leaseLines) {
            cost += ll.getCost()*ll.getQuantity();
        }

        model.addAttribute("totalLeaseLineCostPerMonth",cost);
        model.addAttribute("totalRouters",routerService.findAll().size());
        model.addAttribute("provinceLabels",provinceLabels);
        model.addAttribute("siteChartData",siteChartData);
        model.addAttribute("transTypeLabels",getTransTypeLabel());
        model.addAttribute("transTypeData",getTransTypeData());
        return "index";
    }
//    Dữ liệu cho Site Chart
    private List<String> getProvincesLabel() {
        List<String> labels = new ArrayList<>();
        for (Province province: provinceService.findAll()) {
            labels.add(province.getName());
        }
        return labels;
    }
    private List<Integer> getSiteChartData() {
        List<Integer> siteNumber = new ArrayList<>();
        for (Province province: provinceService.findAll()) {
            siteNumber.add(province.getSiteList().size());
        }
        return siteNumber;
    }

//    Dữ liệu cho Trans Type Chart
    private List<String> getTransTypeLabel() {
        List<String> labels = new ArrayList<>();
        for (SiteTransmissionType siteTransmissionType: siteTransmissionTypeService.findAll()) {
            labels.add(siteTransmissionType.getName());
        }
        return labels;
    }
    private List<Integer> getTransTypeData() {
        List<Integer> siteNumber = new ArrayList<>();
        for (SiteTransmissionType siteTransmissionType: siteTransmissionTypeService.findAll()) {
            siteNumber.add(siteTransmissionType.getSiteList().size());
        }
        return siteNumber;
    }


}
