package com.arthas.service.controller;

import com.arthas.business.utils.AnsiLog;
import com.arthas.business.utils.file.DownloadUtils;
import com.arthas.business.utils.process.ProcessUtils;
import com.arthas.business.utils.shell.ShellUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: djc
 * @Desc: 试算接口
 * @Date: 2019/3/26 10:44
 */
@RestController
@Slf4j
public class ServiceController {

    private static final int DEFAULT_TELNET_PORT = 3658;
    private static final int DEFAULT_HTTP_PORT = 8563;
    private static final String DEFAULT_TARGET_IP = "127.0.0.1";
    private static File ARTHAS_LIB_DIR;

    private boolean help = false;

    private int pid = -1;
    private String targetIp = DEFAULT_TARGET_IP;
    private int telnetPort = DEFAULT_TELNET_PORT;
    private int httpPort = DEFAULT_HTTP_PORT;

    private static File ARTHAS_DIR;

    static {
        ARTHAS_DIR = new File(System.getProperty("user.home") + File.separator + "tiny-arthas");
        try {
            ARTHAS_DIR.mkdirs();
        } catch (Throwable t) {
            //ignore
        }

    }


    @RequestMapping("/services")
    public String services(String host, Integer port) {
        if (StringUtils.isBlank(host)) {
            host = "172.16.158.170";
        }
        if (port == null) {
            port = 22;
        }
        //远程执行命令
        String command = "source /etc/profile; jps  -l";
        Map<String,String> map = Maps.newHashMap();
        List<String> list = ShellUtils.exec(host, port, ShellUtils.user, ShellUtils.privateKey, command);
        for (String project : list) {
           String projects[] =  project.trim().split(" ");
           map.put(projects[1],projects[0]);
           log.info("key="+projects[1] + ",value="+projects[0]);
        }


        String pid =  map.get("arthas-demo.jar");
        log.info("arthas-demo pid = "+ pid);


        String arthasHomeDir = "/home/admin/tiny-arthas";

        // start arthas-core.jar
        List<String> attachArgs = new ArrayList<String>();
        attachArgs.add("-jar");
        attachArgs.add(new File(arthasHomeDir, "arthas-core.jar").getAbsolutePath());
        attachArgs.add("-pid");
        attachArgs.add("" + pid);
        attachArgs.add("-target-ip");
        attachArgs.add(targetIp);
        attachArgs.add("-telnet-port");
        attachArgs.add("" + telnetPort);
        attachArgs.add("-http-port");
        attachArgs.add("" + httpPort);
        attachArgs.add("-core");
        attachArgs.add(new File(arthasHomeDir, "arthas-core.jar").getAbsolutePath());
        attachArgs.add("-agent");
        attachArgs.add(new File(arthasHomeDir, "arthas-agent.jar").getAbsolutePath());

        log.info("Try to attach process " + pid);
        log.info("Start arthas-core.jar args: " + attachArgs);

        ProcessUtils.startArthasCore(Integer.parseInt(pid), attachArgs);
        return "success";
    }


    @RequestMapping("/scan")
    public String scan(String pid) throws Exception {



        return "success";
    }


    private static boolean verifyArthasHome(String arthasHome) {
        File home = new File(arthasHome);
        if (home.isDirectory()) {
            String[] fileList = {"arthas-core.jar", "arthas-agent.jar", "arthas-spy.jar"};
            for (String fileName : fileList) {
                if (!new File(home, fileName).exists()) {

                    return true;
                }
            }
        }
        return false;

    }


}
