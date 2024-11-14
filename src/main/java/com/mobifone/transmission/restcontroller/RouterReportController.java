package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.service.IRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @CrossOrigin(origins = "*")
@RequestMapping("/api/routers/reports")
public class RouterReportController {
    @Autowired
    private IRouterService routerService;

//    @GetMapping("/api/list")
//    @ResponseBody
//    public Object getRouterList() {
//        return routerService.findBy(RouterViewDTO.class);
//    }



    @GetMapping("/total")
    public ResponseEntity<?> getTotalRouters() {
        List<RouterViewDTO> routerViewDTOList = routerService.findBy(RouterViewDTO.class);
        return new ResponseEntity<>(routerViewDTOList.size(), HttpStatus.OK);
    }

    @GetMapping("/api/simple-list")
    @ResponseBody
    public Object getSimpleRouterList() {
        return routerService.findAllSimpleRouter();
    }
}
