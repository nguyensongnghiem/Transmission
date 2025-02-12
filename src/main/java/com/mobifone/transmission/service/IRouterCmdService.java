package com.mobifone.transmission.service;

import com.mobifone.transmission.model.Router;

public interface IRouterCmdService {
    public String getOsInfo();
    public String getConfigFile(Router router, String backupPath);
}
