package com.mobifone.transmission.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mobifone.transmission.model.Router;
import com.mobifone.transmission.model.RouterBackup;
import com.mobifone.transmission.repository.IRouterBackupRepository;
import com.mobifone.transmission.service.IRouterBackupService;
import com.mobifone.transmission.service.IRouterCmdService;

@Service
public class NokiaRouterCmdService implements IRouterCmdService {

    @Autowired
    private IRouterBackupService routerBackupService ;

    @Override
    public String getConfigFile(Router router, String backupPath) {
        String user = "nghiem"; // Tài khoản người dùng
        String password = "nghiem@123"; // Mật khẩu
        String remoteFile = "cf3:/config.cfg"; // Đường dẫn tệp trên router
        String fileName = router.getName() + "_"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".cfg"; // Đường dẫn tệp
                                                                                                      // lưu trên máy
                                                                                                      // địa phương

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, router.getIp(), 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Mở channel để truyền tệp từ router
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("scp -f " + remoteFile); // Gửi lệnh scp

            // Tạo InputStream để nhận dữ liệu
            InputStream in = channel.getInputStream();
            OutputStream out = channel.getOutputStream();
            channel.connect();

            // Gửi lệnh để nhận tệp
            byte[] buffer = new byte[1024];
            buffer[0] = 0;
            out.write(buffer, 0, 1);
            out.flush();
            int bytesRead;
            StringBuilder fileInfoBuilder = new StringBuilder();
            // Đọc thông tin tệp
            while ((bytesRead = in.read(buffer)) != -1) {
                fileInfoBuilder.append(new String(buffer, 0, bytesRead));
                if (fileInfoBuilder.toString().contains(" ")) { // Kiểm tra đến khi có khoảng trắng
                    break;
                }
            }
            String fileInfo = fileInfoBuilder.toString();
            if (fileInfo.startsWith("C")) {
                // Giải mã thông tin tệp
                String[] parts = fileInfo.split(" ");
                long fileSize = Long.parseLong(parts[1]);
                String filename = parts[2];

                buffer[0] = 0;
                out.write(buffer, 0, 1);
                out.flush();
                // Ghi tệp vào local
                try (FileOutputStream fos = new FileOutputStream(backupPath + "/" + fileName)) {
                    while (fileSize > 0
                            && (bytesRead = in.read(buffer, 0, (int) Math.min(buffer.length, fileSize))) != -1) {
                        fos.write(buffer, 0, bytesRead);
                        fileSize -= bytesRead;
                        if (fileSize == 0L) {
                            break;
                        }
                    }
                    RouterBackup  routerBackup = new RouterBackup();
                    routerBackup.setPath(backupPath + "/" + fileName);
                    routerBackup.setRouter(router);
                    routerBackupService.save(routerBackup);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Kết thúc phiên
            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Đã sao lưu cấu hình router " + router.getName();
    }

    @Override
    public String getOsInfo() {
        // TODO Auto-generated method stub
        return "show system";
    }

}
