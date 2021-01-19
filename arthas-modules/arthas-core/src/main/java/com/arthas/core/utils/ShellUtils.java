package com.arthas.core.utils;


import com.jcabi.ssh.Shell;
import com.jcabi.ssh.Ssh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ShellUtils {


    public static final long SECOND_OF_ONE_MINITS = 1 * 60l;


    public static String user = "admin";

    public static String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEoQIBAAKCAQEA0s7W9ly2aBR98UGGUj+pC6VT+QmWDlCR4DxO4hcEsrLPHYrZ\n" +
            "Wa9khvcNy6nQgdzm8e/30fA5WinhyCJLKw0cnaqONH6aJC6Oox21R2cyKFnWa9WS\n" +
            "n8DdjqjcnI7pICEKzrYRjrWKtPd26Q+Nc7FfjaglQ7rR4a9UOCX0Fh+xsTPRmTl+\n" +
            "1J30o/x4zNRer6lI6o0vzL04nG6p1rI8g0H33BhXuYEyzj7h0k310yaAONyQxXH+\n" +
            "jG8b0uABghEzi4m5IBkkyEFyZAM4ytEczil8OJBJulBWupUwOsFVwKegv9l+A5BR\n" +
            "iQCAxY1vFOxTZFeYpi7zzJ0H58a7i1G4ZEmh8wIBIwKCAQEAkI3VOzhCkIMUiCzu\n" +
            "ZEjo8gr3wLYdwKxGxaWybyW6FCLXKjNTNi8gXIwmt4pjFzhj2RmT/awKEfDVVgjj\n" +
            "FjTgbB0uT+HIyFpwb9nbZCmBesFCkxYbZjsblQYTno3hr6GhAsX9aSwHV4UeVquU\n" +
            "MhM6NUAZjYdrWO1BEI8NxgcawqXxh2Hcx0RqQIvu43/zMdTB9UHI1NYyBm7DOmH4\n" +
            "JXtLZsmO16Aq95JXPUO+QpGVe3R4Xa4fc9xtWUxm+zCdWBsGMYD02ZJWXNKNMLIY\n" +
            "0OAmsr1d3ktL8uhf1sEKsgvQaTyLjyLXcpJXJacEGBHGcjs6ECuCrfMbYrrA3ktm\n" +
            "FgV9ywKBgQD17Ak5RUo3mcnsPWBs0p+WjbyfEFqk6X/FBkxsWaX0ny3Wyt1lUhqS\n" +
            "aGMRSGbMAP4KreujHv8ZLjwzmrnWsjuSoDX+61sczp9svWB81mX8c7bDTcZnzl60\n" +
            "T/La2tX7+o5RRGe0MyOpoFXzBUKPuSFiYroS38UFBWpzBpAtYgaGvQKBgQDbcmw4\n" +
            "7LqiFrvQLllZ3wjCLHtL21TgFDgIG0GbnZ2KnFnl9C+O0xmQMJLEaatk49Sr5D3C\n" +
            "5Hk4gmm3uQXMuNFoIlxrdPDs+LC+JmzmcdHyADAIMwbK1KOa0Z+goisU1n2L48B4\n" +
            "eXIYDk6WFzqCRJ/W6+AXryrQR7aZ6z5AzNBubwKBgD88seonwqCVQo00Wp+j3+Ti\n" +
            "njeH3MtR/EibODkeXd/RKQtYvJZlkc3gU/0hPwFCFXB13YG3g2zgANoR2AQB8g/C\n" +
            "zA5ZxvjHatLRnHffXA23WuHDiso8YX7SusqQCyOJkk9pXH7LUlAwiyE73epxbvwK\n" +
            "wiIcSJrkIq/dHcKG67w/AoGAOG3hUHdjMP6IEPX5uAYm0tg89j+2vUcHGAcCPfVU\n" +
            "ZXipZwRGvlOKOwUtDe9JVHUZbgd9mIPkqCGI5hmiZ9fA0aJviViVuUdDZBiCZySV\n" +
            "ElfR1jkBvyC8WwK0BLv8cxKVT9t6oqLi0vxdWct1cfRjnapeMfnXzyhiJ5RDNTv7\n" +
            "FRUCgYBiJYo3QsVMM4b7vkyh7uYP9hbhjoNB2/9N0ETKUwX1DN6mWTATUpnEOY41\n" +
            "9iHxKdUmUWiZIORdy7zcYUoVz0SgzOWMGKHm08mptC0rXUTa3/jtSGPMCq9ltQH3\n" +
            "pmBuaXR5nPqVVb8RZPg9v7fx6pLR2p0+hnZXIVCIRZe/OYHagg==\n" +
            "-----END RSA PRIVATE KEY-----";


    /**
     * @param millis 毫秒
     * @param nanos  纳秒
     * @Title: seleep
     * @Description: 线程等待时间
     * @author yuhao.wang
     */
    public static void seleep(long millis, int nanos) {
        long nowTime = System.currentTimeMillis();
        try {
            final Random random = new Random();
            Thread.sleep(millis, random.nextInt(nanos));
        } catch (InterruptedException e) {
            AnsiLog.info("中断：", e);
        }
        AnsiLog.info("sleep time =" + (System.currentTimeMillis() - nowTime));
    }

    public static String exeShell(String host, int port, String user, String privateKey, String command) {
        // 请求锁超时时间，毫秒3分钟
        long timeout = SECOND_OF_ONE_MINITS * 1000;
        // 系统当前时间，毫秒
        long nowTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - nowTime) < timeout) {
            try {
                String result = new Shell.Plain(
                        new Ssh(
                                host, port,
                                user, privateKey)
                ).exec(command);

                return result;
            } catch (Exception e) {
                AnsiLog.error(" host=" + host + ",port=" + port + ",user=" + user + ",shell.Plain exception ,command=" + command, e);
            }
            AnsiLog.info(" host=" + host + ",port=" + port + ",user=" + user + ", redis lockTryTimes command=" + command + ",wait time =" + (System.currentTimeMillis() - nowTime));
            // 每次请求等待一段时间
            seleep(5000, 50000);
        }
        return null;
    }

    /***
     *
     */
    public static List<String> exec(String host, int port, String user, String privateKey, String command) {
        List<String> list = new ArrayList<String>();
        try {
            String hello = exeShell(host, port, user, privateKey, command);
            if (StringUtils.isNotBlank(hello)) {
                String hellos[] = hello.split("\n");
                if (hellos != null && hellos.length > 0) {
                    for (String value : hellos) {
                        list.add(value);
                    }
                }
            }
        } catch (Exception e) {
            AnsiLog.error("执行命令异常", e);
        }
        return list;
    }


    /***
     *
     */
    public static String execGetFirst(String host, int port, String user, String privateKey, String command) {
        try {

            String hello = exeShell(host, port, user, privateKey, command);

            if (StringUtils.isNotBlank(hello)) {
                String hellos[] = hello.split("\n");
                if (hellos != null && hellos.length > 0) {
                    return hellos[0];
                }
            }
        } catch (Exception e) {
            AnsiLog.error("执行命令异常", e);
        }
        return null;
    }


    /***
     *
     */
    public static String execGetLast(String host, int port, String user, String privateKey, String command) {
        try {
            String hello = exeShell(host, port, user, privateKey, command);
            if (StringUtils.isNotBlank(hello)) {
                String hellos[] = hello.split("\n");
                if (hellos != null && hellos.length > 0) {
                    return hellos[hellos.length - 1];
                }
            }
        } catch (Exception e) {
            AnsiLog.error("执行命令异常", e);
        }
        return null;
    }


}
