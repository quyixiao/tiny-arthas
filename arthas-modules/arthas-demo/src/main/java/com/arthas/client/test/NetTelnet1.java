package com.arthas.client.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.PrintStream;

@Slf4j
public class NetTelnet1 {
    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private char prompt = '$';

    private static final String PROMPT = "$";
    private static final int DEFAULT_CONNECTION_TIMEOUT = 5000; // 5000 ms
    private static final int DEFAULT_BUFFER_SIZE = 1024;


    private static final byte CTRL_C = 0x03;


    private Integer width = null;
    private Integer height = null;

    public static final int DEFAULT_WIDTH = 80;

    public static final int DEFAULT_HEIGHT = 24;

    // 普通用户结束
    public NetTelnet1(String ip, int port, String user, String password) {
        try {
            System.out.println(" to  connet ");
            telnet.connect(ip, port);
            System.out.println(" to  getInputStream ");
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            // 根据root用户设置结束符  
            this.prompt = user.equals("root") ? '#' : '$';
            System.out.println(" to  login");
            login(user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录 * * @param user * @param password
     */
    public void login(String user, String password) {
        readUntil("login:");
        write(user);
        readUntil("Password:");
        write(password);
        readUntil(prompt + " ");
    }

    /**
     * 读取分析结果 * * @param pattern * @return
     */
    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写操作 * * @param value
     */
    public void write(String value) {
        try {
            out.println(value);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向目标发送命令字符串 * * @param command * @return
     */
    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            log.info("启动Telnet...");
            String ip = "172.16.158.170";
            int port = 23;
            String user = "admin";
            String password = "Fxxy@9527";
            NetTelnet1 telnet = new NetTelnet1(ip, port, user, password);
            telnet.sendCommand("export LANG=en");
            String r1 = telnet.sendCommand("cd /home/admin/");
            String r2 = telnet.sendCommand("pwd");
            String r3 = telnet.sendCommand("ls ");
            log.info("显示结果");
            log.info(r1);
            log.info(r2);
            log.info(r3);
            telnet.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 