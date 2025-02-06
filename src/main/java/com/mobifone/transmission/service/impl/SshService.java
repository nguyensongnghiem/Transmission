package com.mobifone.transmission.service.impl;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mobifone.transmission.model.Router;

@Service
public class SshService {
    public String executeSSHCommand(Router router, String command) {
        String result = "";
        Session session = null;
        final String USERNAME = "nghiem";
        final String PASSWORD = "nghiem@123";
        ChannelExec channel = null;
        try {
            // Thiết lập kết nối SSH
            JSch jsch = new JSch();
            session = jsch.getSession(USERNAME, router.getIp(), 22);
            session.setPassword(PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig("kex",
                    "diffie-hellman-group14-sha1,diffie-hellman-group1-sha1,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group-exchange-sha1,diffie-hellman-group1-sha1");
            session.setConfig("PubkeyAcceptedAlgorithms", "ssh-rsa,ssh-dss");
            session.setConfig("cipher.s2c", "aes256-ctr,aes192-ctr,aes128-ctr");
            session.setConfig("cipher.c2s", "aes256-ctr,aes192-ctr,aes128-ctr");
            session.connect();

            // Thực thi lệnh trên router
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setInputStream(null);
            InputStream in = channel.getInputStream();
            channel.connect();

            // Đọc kết quả trả về
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) > 0) {
                result += new String(buffer, 0, read);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failed to connect or execute command: " + e.getMessage();
        } finally {
            if (channel != null)
                channel.disconnect();
            if (session != null)
                session.disconnect();
        }

        return result;
    }

}
