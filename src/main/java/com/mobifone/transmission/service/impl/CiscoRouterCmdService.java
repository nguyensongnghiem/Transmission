package com.mobifone.transmission.service.impl;

import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;


import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.service.IRouterCmdService;

public class CiscoRouterCmdService implements IRouterCmdService {
   
    SshService sshService = new SshService();
    @Override
    public String getOsInfo() {
        // TODO Auto-generated method stub
        return "show version";
    }

    @Override
    public String getConfigFile(Router router, String backupPath) {
        String remoteFile = "nvram:/running-config"; // Đường dẫn tệp trên router
        String fileName = router.getName() + "_"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".cfg"; 
        String localFile = backupPath + "/" + fileName;
        return sshService.downloadFileSftp(router, remoteFile, localFile);        
    }

}
