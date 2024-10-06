package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.RouterDTO;
import com.mobifone.transmission.dto.SiteCreateDTO;
import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.exception.RouterNotFoundException;
import com.mobifone.transmission.exception.SiteIdExistedException;
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
@RequestMapping("/api/routers")
@RestController
@CrossOrigin(origins = "*")
public class RouterRestController {
    @Autowired
    private IRouterService routerService;
    @Autowired
    private ISiteService siteService;
    @GetMapping
    public ResponseEntity<?> getRouters() {
        List<RouterViewDTO> routerViewDTOList = routerService.findBy(RouterViewDTO.class);
        return new ResponseEntity<>(routerViewDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRouter(@PathVariable Long id) {
        RouterViewDTO router = routerService.findById(id,RouterViewDTO.class);
        if (router==null) throw new RouterNotFoundException("Router không tồn tại trong hệ thống");
        return ResponseEntity.ok(router);
    }

//    @GetMapping("/api/routers/total")
//    public ResponseEntity<?> getTotalRouters() {
//        List<RouterViewDTO> routerViewDTOList = routerService.findBy(RouterViewDTO.class);
//        return new ResponseEntity<>(routerViewDTOList.size(), HttpStatus.OK);
//    }
    @PostMapping
    public ResponseEntity<?> createRouter(@Valid @RequestBody RouterDTO routerDTO) {
        Router targetRouter = new Router();
        BeanUtils.copyProperties(routerDTO, targetRouter);
        routerService.save(targetRouter);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRouter(@Valid @RequestBody RouterDTO routerDTO, @PathVariable long id) {

        Router targetRouter = routerService.findById(id, Router.class);
        if (targetRouter == null) throw new RouterNotFoundException("Không tồn tại Router với id " + id + " trong hệ thống.");
        BeanUtils.copyProperties(routerDTO, targetRouter);
        routerService.save(targetRouter);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/simple-list")
    @ResponseBody
    public Object getSimpleRouterList() {
        return routerService.findAllSimpleRouter();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRouterById(@PathVariable Long id) {
        Router router = routerService.findById(id, Router.class);
        if (router==null) {throw new RouterNotFoundException("Thiết bị không tồn tại");}
        else {
            routerService.deleteById(id);
        };
        return ResponseEntity.ok("Đã xóa thành công thiết bị");
    }
}
