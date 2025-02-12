package com.mobifone.transmission.service;

import org.springframework.stereotype.Service;

import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.service.impl.JuniperRouterCmdService;
import com.mobifone.transmission.service.impl.NokiaRouterCmdService;
@Service
public class RouterCmdSerFactory {
    public IRouterCmdService getRouterCmdService(Router router) {
        String vendorName = router.getRouterType().getVendor().getName();
        switch (vendorName) {
            case "Juniper":
                return new JuniperRouterCmdService();                
            case "Nokia":
                return new NokiaRouterCmdService();                
            default:
                break;
        }       
        return null;
    };

}
