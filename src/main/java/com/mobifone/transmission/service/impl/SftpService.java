package com.mobifone.transmission.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.mobifone.transmission.model.Router;
import io.jsonwebtoken.io.IOException;
@Service
public class SftpService {
    public String downloadFileSftp(Router router, String remoteFile, String localFile) {
        StringBuilder result = new StringBuilder();        
        final String USERNAME = "nghiem";
        final String PASSWORD = "nghiem@123";       
        try {
            // Thiết lập kết nối SSH
            JSch jsch = new JSch();
            Session session = jsch.getSession(USERNAME, router.getIp(), 22);
            session.setPassword(PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            // session.setConfig("kex",
            // "diffie-hellman-group14-sha1,diffie-hellman-group1-sha1,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group-exchange-sha1,diffie-hellman-group1-sha1");
            // session.setConfig("PubkeyAcceptedAlgorithms", "ssh-rsa,ssh-dss");
            // session.setConfig("cipher.s2c", "aes256-ctr,aes192-ctr,aes128-ctr");
            // session.setConfig("cipher.c2s", "aes256-ctr,aes192-ctr,aes128-ctr");
            session.connect();

            // Thực thi lệnh trên router
            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            channel.get(remoteFile, localFile);        
       
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to connect or execute command: " + e.getMessage();
        }

        return "Đã tải tệp thành công";
    }

    public String executeSshShell(Router router, String command) {
        String username = "nghiem";
        String password = "nghiem@123";
        int port = 22;
        String finalString = "";
        String result = "";
        // SSH connect
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, router.getIp(), port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            // session.setConfig("kex",
            // "diffie-hellman-group14-sha1,diffie-hellman-group1-sha1,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group-exchange-sha1,diffie-hellman-group1-sha1");
            // session.setConfig("PubkeyAcceptedAlgorithms", "ssh-rsa,ssh-dss");
            // session.setConfig("cipher.s2c", "aes256-ctr,aes192-ctr,aes128-ctr");
            // session.setConfig("cipher.c2s", "aes256-ctr,aes192-ctr,aes128-ctr");
            session.connect();
            Channel channel = session.openChannel("shell");
            channel.connect();
            InputStream inputStream = channel.getInputStream();
            OutputStream outputStream = channel.getOutputStream();
            // send commands
            String vendorName = router.getRouterType().getVendor().getName();
            String noPagingCmd = "";
            if (vendorName.equals("Juniper"))
                noPagingCmd = "set cli screen-length 0" + "\n";
            else if (vendorName.equals("Nokia"))
                noPagingCmd = "environment no more" + "\n";
            // String command1 = "environment no more" +"\n";
            String command2 = command + "\n";
            String endCommand = "";
            System.out.println(router.getRouterType().getVendor().getName());
            if (vendorName.equals("Juniper"))
                endCommand = "exit\n";
            else if (vendorName.equals("Nokia"))
                endCommand = "logout\n";
            outputStream.write(noPagingCmd.getBytes());
            outputStream.write(command2.getBytes());
            outputStream.write(endCommand.getBytes());
            outputStream.flush();
            // read inputStream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while (true) {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    // System.out.println(bytesRead);
                    String output = new String(buffer, 0, bytesRead);
                    result = result + "\n" + output;
                    System.out.println(output);

                }
                channel.disconnect();
                session.disconnect();
                if (channel.isClosed()) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failed to connect or execute command: " + e.getMessage();
        }
        return result;
    }

}
