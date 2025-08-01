package com.mobifone.transmission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.service.impl.CiscoRouterCmdService;
import com.mobifone.transmission.service.impl.JuniperRouterCmdService;
import com.mobifone.transmission.service.impl.NokiaRouterCmdService;

@Service
public class RouterCmdSerFactory {
    @Autowired
    JuniperRouterCmdService juniperRouterCmdService;
    @Autowired
    NokiaRouterCmdService nokiaRouterCmdService;
    @Autowired
    CiscoRouterCmdService ciscoRouterCmdService;
    public IRouterCmdService getRouterCmdService(Router router) {
        String vendorName = router.getRouterType().getVendor().getName();
        switch (vendorName) {
            case "Juniper":
                return juniperRouterCmdService;
            case "Nokia":
                return nokiaRouterCmdService;
                case "Cisco":
                return ciscoRouterCmdService;
            default:
                break;
        }
        return null;
    };

}
