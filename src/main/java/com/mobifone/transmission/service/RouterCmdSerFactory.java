package com.mobifone.transmission.service;

import org.springframework.stereotype.Service;

import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.service.impl.JuniperRouterCmdService;
import com.mobifone.transmission.service.impl.NokiaRouterCmdService;
@Service
public class RouterCmdSerFactory {
    public IRouterCmdService getRouterCmdService(Router router) {
        if (router.getRouterType().getVendor().getName().equals("Juniper")) {
            return new JuniperRouterCmdService();
        } else if (router.getRouterType().getVendor().getName().equals("Nokia")) {
            return new NokiaRouterCmdService();
        }
        return null;
    };

}
