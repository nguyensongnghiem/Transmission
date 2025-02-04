package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.service.IRouterExecService;

public class NokiaRouterExecService implements IRouterExecService {

    @Override
    public String getConfigFile() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getOsInfoCmd() {
        // TODO Auto-generated method stub
        return "show system";
    }

}
