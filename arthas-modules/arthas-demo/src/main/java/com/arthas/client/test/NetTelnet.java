package com.arthas.client.test;

import jline.console.ConsoleReader;
import jline.console.KeyMap;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetOptionHandler;
import org.apache.commons.net.telnet.WindowSizeOptionHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class NetTelnet {
    private TelnetClient telnet = new TelnetClient();
    private static  InputStream in;
    private static PrintStream out;
    private static char prompt = '$';

    private static final String PROMPT = "$";
    private static final int DEFAULT_CONNECTION_TIMEOUT = 5000; // 5000 ms
    private static final int DEFAULT_BUFFER_SIZE = 1024;






    private static final byte CTRL_C = 0x03;


    private Integer width = null;
    private Integer height = null;

    public static final int DEFAULT_WIDTH = 80;

    public static final int DEFAULT_HEIGHT = 24;

    // 普通用户结束
    public NetTelnet(String ip, int port, String user, String password) {
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
    public static void login(String user, String password) {
        readUntil("login:");
        write(user);
        readUntil("Password:");
        write(password);
        readUntil(prompt + " ");
    }

    /**
     * 读取分析结果 * * @param pattern * @return
     */
    public static String readUntil(String pattern) {
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
    public  static void write(String value) {
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
            final TelnetClient telnet = new TelnetClient();
            telnet.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);

            int width = DEFAULT_WIDTH;
            int height = DEFAULT_HEIGHT;

            final ConsoleReader consoleReader = new ConsoleReader(System.in, System.out);
            // send init terminal size
            TelnetOptionHandler sizeOpt = new WindowSizeOptionHandler(width, height, true, true, false, false);
            try {
                telnet.addOptionHandler(sizeOpt);
            } catch (InvalidTelnetOptionException e) {
                // ignore
            }

            // ctrl + c event callback
            consoleReader.getKeys().bind(new Character((char) CTRL_C).toString(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        consoleReader.getCursorBuffer().clear(); // clear current line
                        telnet.getOutputStream().write(CTRL_C);
                        telnet.getOutputStream().flush();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

            });

            // ctrl + d event call back
            consoleReader.getKeys().bind(new Character(KeyMap.CTRL_D).toString(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


            String targetIp = "172.16.158.170";
            int port = 23;

            String user = "admin";
            String password = "Fxxy@9527";

            try {
                telnet.connect(targetIp, port);
                in = telnet.getInputStream();
                out = new PrintStream(telnet.getOutputStream());


                login(user,password);



            } catch (IOException e) {
                System.out.println("Connect to telnet server error: " +targetIp + " "
                        + 22);
                throw e;
            }

            com.arthas.client.utils.IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(), System.in,
                    consoleReader.getOutput());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}