package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.RouterDTO;
import com.mobifone.transmission.dto.SiteCreateDTO;
import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.exception.RouterNotFoundException;
import com.mobifone.transmission.exception.SiteNotFoundException;
import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.IRouterService;
import com.mobifone.transmission.service.ISiteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RouterRestController {
    @Autowired
    private IRouterService routerService;
    @Autowired
    private ISiteService siteService;
    @GetMapping("/api/routers")
    public ResponseEntity<?> getRouters() {
        List<RouterViewDTO> routerViewDTOList = routerService.findBy(RouterViewDTO.class);
        return new ResponseEntity<>(routerViewDTOList, HttpStatus.OK);
    }

    @GetMapping("/api/routers/{id}")
    public ResponseEntity<?> getRouter(@PathVariable Long id) {
        RouterViewDTO router = routerService.findById(id,RouterViewDTO.class);
        if (router==null) throw new RouterNotFoundException("Router không tồn tại trong hệ thống");
        return ResponseEntity.ok(router);
    }

    @GetMapping("/api/routers/total")
    public ResponseEntity<?> getTotalRouters() {
        List<RouterViewDTO> routerViewDTOList = routerService.findBy(RouterViewDTO.class);
        return new ResponseEntity<>(routerViewDTOList.size(), HttpStatus.OK);
    }
    @PostMapping("/api/routers")
    public ResponseEntity<?> createRouter(@Valid @RequestBody RouterDTO routerDTO) {

        System.out.println(routerDTO);
//        Site targetSite = siteCreateDTOToSite.apply(siteDTO);
//        if (targetSite.getId()!=null) {
//            Site existSite = siteService.findById(targetSite.getId(), Site.class);
//            BeanUtils.copyProperties(targetSite,existSite);
//            siteService.save(existSite);
//        } else {siteService.save(targetSite); }
//        System.out.println(targetSite);
        return ResponseEntity.ok("ok");

    }
    @GetMapping("/api/routers/simple-list")
    @ResponseBody
    public Object getSimpleRouterList() {
        return routerService.findAllSimpleRouter();
    }

    @DeleteMapping("/api/routers/{id}")
    public ResponseEntity<?> deleteRouterById(@PathVariable Long id) {
        Router router = routerService.findById(id, Router.class);
        if (router==null) {throw new RouterNotFoundException("Thiết bị không tồn tại");}
        else {
            routerService.deleteById(id);
        };
        return ResponseEntity.ok("Đã xóa thành công thiết bị");
    }
}
