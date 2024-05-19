package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.service.IRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("router")
public class RouterRestController {
    @Autowired
    private IRouterService routerService;

    @GetMapping("/api/list")
    @ResponseBody
    public Object getSiteList() {
        return routerService.findBy(RouterViewDTO.class);
    }
}
