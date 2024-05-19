package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.service.ISiteService;
import com.mobifone.transmission.service.impl.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site")
public class SiteRestController {
    @Autowired
    private ISiteService siteService;

    @GetMapping("/api/list")
    @ResponseBody
    public Object getSiteList() {
        return siteService.findBy(SiteViewDTO.class);
    }
}
