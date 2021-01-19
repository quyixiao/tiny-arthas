package com.arthas.business.utils;

import com.alibaba.fastjson.JSON;
import com.arthas.business.utils.process.ProcessUtils;
import com.arthas.business.utils.shell.ExecutingCommand;
import org.apache.log4j.Logger;

import javax.net.ServerSocketFactory;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author hengyunabc 2018-11-07
 */

public class SocketUtils {

    private static final Logger log = Logger.getLogger(SocketUtils.class);
    private final static Map<Integer, String> packages = new HashMap<>();


    public static int findTcpListenProcess(int port) {
        try {
            if (OSUtils.isWindows()) {
                String[] command = {"netstat", "-ano", "-p", "TCP"};
                List<String> lines = ExecutingCommand.runNative(command);
                for (String line : lines) {
                    if (line.contains("LISTENING")) {
                        // TCP 0.0.0.0:49168 0.0.0.0:0 LISTENING 476
                        String[] strings = line.trim().split("\\s+");
                        if (strings.length == 5) {
                            if (strings[1].endsWith(":" + port)) {
                                return Integer.parseInt(strings[4]);
                            }
                        }
                    }
                }
            }

            if (OSUtils.isLinux() || OSUtils.isMac()) {
                String pid = ExecutingCommand.getFirstAnswer("lsof -t -s TCP:LISTEN -i TCP:" + port);
                if (!pid.trim().isEmpty()) {
                    return Integer.parseInt(pid);
                }
            }
        } catch (Throwable e) {
            // ignore
        }
        return -1;
    }


    public static String findPackage(int port) {
        try {
            String packageName = packages.get(port);
            if (StringUtil.isNotBlank(packageName)) {
                return packageName;
            }
            int pid = findTcpListenProcess(port);
            Map<Integer, String> map = ProcessUtils.listProcessByJps(false);
            String value = map.get(pid);
            if (StringUtil.isNotBlank(value)) {
                value = value.replaceAll(pid + "", "");
                value = value.trim();
            }
            packages.put(port, value);
            return value;
        } catch (Exception e) {
            log.error("findPackage package port=" + port, e);
        }
        return "";
    }

    public static boolean isTcpPortAvailable(int port) {
        try {
            ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port, 1,
                    InetAddress.getByName("localhost"));
            serverSocket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
/*

    public static void main(String[] args) {
        Map<Integer, String> map = ProcessUtils.listProcessByJps(false);
        System.out.println(JSON.toJSONString(map));
        System.out.println(findPackage(8001));
    }*/
}
