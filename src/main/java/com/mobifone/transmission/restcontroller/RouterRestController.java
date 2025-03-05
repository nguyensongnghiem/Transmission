package com.mobifone.transmission.restcontroller;

import com.mobifone.transmission.dto.RouterDTO;
import com.mobifone.transmission.dto.SiteCreateDTO;
import com.mobifone.transmission.dto.inf.RouterViewDTO;
import com.mobifone.transmission.exception.RouterNotFoundException;
import com.mobifone.transmission.exception.SiteIdExistedException;
import com.mobifone.transmission.exception.SiteNotFoundException;
import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.IRouterCmdService;
import com.mobifone.transmission.service.IRouterService;
import com.mobifone.transmission.service.ISiteService;
import com.mobifone.transmission.service.RouterCmdSerFactory;
import com.mobifone.transmission.service.impl.NokiaRouterCmdService;
import com.mobifone.transmission.service.impl.SftpService;
import com.mobifone.transmission.service.impl.SshService;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping("/api/routers")
@RestController
// @CrossOrigin(origins = "*")
public class RouterRestController {
    @Autowired
    private IRouterService routerService;
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IRouterCmdService routerCmdService;
    @Autowired
    private RouterCmdSerFactory routerCmdSerFactory;

    @Autowired
    private SshService sshService;

    @GetMapping("/testSSH/{name}")
    public ResponseEntity<?> getRouterName(@PathVariable(name = "name") String name) {
        Router router = routerService.findRouterByName(name);

        if (router == null)
            throw new RouterNotFoundException("Router không tồn tại trong hệ thống");
        else
            routerCmdService = routerCmdSerFactory.getRouterCmdService(router);
        return ResponseEntity.ok(sshService.executeSSHCommand(router, "show version"));
    }

    @GetMapping("/backup/{name}")
    public ResponseEntity<?> getConfig(@PathVariable(name = "name") String name) {

        Router router = routerService.findRouterByName(name);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String directoryName = currentDate.format(formatter);

        // Đường dẫn tới thư mục backup
        Path backupDirectory = Paths.get("backup", directoryName); // "backup" là thư mục gốc

        try {
            // Kiểm tra và tạo thư mục nếu cần
            if (!Files.exists(backupDirectory)) {
                Files.createDirectories(backupDirectory);
                System.out.println("Thư mục đã được tạo: " + backupDirectory.toString());
            } else {
                System.out.println("Thư mục đã tồn tại: " + backupDirectory.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fullBackupFolder = "D:/" +backupDirectory.toString();
        // String fullBackupFolder = "/" + backupDirectory.toString();
        if (router == null)
            throw new RouterNotFoundException("Router không tồn tại trong hệ thống");
        else
            routerCmdService = routerCmdSerFactory.getRouterCmdService(router);
        // Lấy ngày hiện tại

        return ResponseEntity.ok(routerCmdService.getConfigFile(router, fullBackupFolder));
    }

    @GetMapping
    public ResponseEntity<?> getRouters() {
        List<RouterViewDTO> routerViewDTOList = routerService.findBy(RouterViewDTO.class);
        return new ResponseEntity<>(routerViewDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRouter(@PathVariable(name = "id") Long id) {
        RouterViewDTO router = routerService.findById(id, RouterViewDTO.class);
        if (router == null)
            throw new RouterNotFoundException("Router không tồn tại trong hệ thống");
        return ResponseEntity.ok(router);
    }

    // @GetMapping("/api/routers/total")
    // public ResponseEntity<?> getTotalRouters() {
    // List<RouterViewDTO> routerViewDTOList =
    // routerService.findBy(RouterViewDTO.class);
    // return new ResponseEntity<>(routerViewDTOList.size(), HttpStatus.OK);
    // }
    @PostMapping
    public ResponseEntity<?> createRouter(@Valid @RequestBody RouterDTO routerDTO) {
        Router targetRouter = new Router();
        BeanUtils.copyProperties(routerDTO, targetRouter);
        routerService.save(targetRouter);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRouter(@Valid @RequestBody RouterDTO routerDTO, @PathVariable(name = "id") long id) {

        Router targetRouter = routerService.findById(id, Router.class);
        if (targetRouter == null)
            throw new RouterNotFoundException("Không tồn tại Router với id " + id + " trong hệ thống.");
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
    public ResponseEntity<?> deleteRouterById(@PathVariable(name = "id") Long id) {
        Router router = routerService.findById(id, Router.class);
        if (router == null) {
            throw new RouterNotFoundException("Thiết bị không tồn tại");
        } else {
            routerService.deleteById(id);
        }
        ;
        return ResponseEntity.ok("Đã xóa thành công thiết bị");
    }
}
