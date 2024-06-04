package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.service.IRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routers")
public class RouterRestController {
    @Autowired
    private IRouterService routerService;

//    @GetMapping("/api/list")
//    @ResponseBody
//    public Object getRouterList() {
//        return routerService.findBy(RouterViewDTO.class);
//    }

    @GetMapping
    public ResponseEntity<List<RouterViewDTO>> getRouters() {
        List<RouterViewDTO> routerViewDTOList = routerService.findBy(RouterViewDTO.class);
        return new ResponseEntity<>(routerViewDTOList, HttpStatus.OK);
    }

    @GetMapping("/api/simple-list")
    @ResponseBody
    public Object getSimpleRouterList() {
        return routerService.findAllSimpleRouter();
    }
}
