package com.arthas.core;

import com.arthas.core.config.Configure;
import com.arthas.core.utils.AnsiLog;
import com.arthas.core.utils.JavaVersionUtils;
import com.arthas.core.utils.ProcessUtils;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.taobao.middleware.cli.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * Arthas启动器
 */
public class Arthas {

    public final static boolean isTest = true;


    private static final String DEFAULT_TELNET_PORT = "3658";
    private static final String DEFAULT_HTTP_PORT = "8563";

    private Arthas(String[] args) throws Exception {
        attachAgent(parse(args));
    }

    private Configure parse(String[] args) {
        com.taobao.middleware.cli.Option pid = new TypedOption<Integer>().setType(Integer.class).setShortName("pid").setRequired(true);
        com.taobao.middleware.cli.Option core = new TypedOption<String>().setType(String.class).setShortName("core").setRequired(true);
        com.taobao.middleware.cli.Option agent = new TypedOption<String>().setType(String.class).setShortName("agent").setRequired(true);
        com.taobao.middleware.cli.Option target = new TypedOption<String>().setType(String.class).setShortName("target-ip");
        com.taobao.middleware.cli.Option telnetPort = new TypedOption<Integer>().setType(Integer.class)
                .setShortName("telnet-port").setDefaultValue(DEFAULT_TELNET_PORT);
        com.taobao.middleware.cli.Option httpPort = new TypedOption<Integer>().setType(Integer.class)
                .setShortName("http-port").setDefaultValue(DEFAULT_HTTP_PORT);
        com.taobao.middleware.cli.Option sessionTimeout = new TypedOption<Integer>().setType(Integer.class)
                .setShortName("session-timeout").setDefaultValue("" + Configure.DEFAULT_SESSION_TIMEOUT_SECONDS);
        CLI cli = CLIs.create("arthas").addOption(pid).addOption(core).addOption(agent).addOption(target)
                .addOption(telnetPort).addOption(httpPort).addOption(sessionTimeout);

        CommandLine commandLine = cli.parse(Arrays.asList(args));

        Configure configure = new Configure();

        if (isTest) {
            //远程执行命令
            Integer javaPid = 0;
            Map<Integer, String> processMap = ProcessUtils.listProcessByJps(false);
            for (Map.Entry<Integer, String> m : processMap.entrySet()) {
                if (m.getValue().contains("arthas-demo")) {
                    javaPid = m.getKey();
                }
            }
            AnsiLog.info(" test arthas-demo pid = " + javaPid);
            configure.setJavaPid(javaPid);
        } else {
            AnsiLog.info(" not to test ");
            configure.setJavaPid((Integer) commandLine.getOptionValue("pid"));
        }


        configure.setArthasAgent((String) commandLine.getOptionValue("agent"));
        configure.setArthasCore((String) commandLine.getOptionValue("core"));
        configure.setSessionTimeout((Integer) commandLine.getOptionValue("session-timeout"));
        if (commandLine.getOptionValue("target-ip") == null) {
            throw new IllegalStateException("as.sh is too old to support web console, " +
                    "please run the following command to upgrade to latest version:" +
                    "\ncurl -sLk https://alibaba.github.io/arthas/install.sh | sh");
        }
        configure.setIp((String) commandLine.getOptionValue("target-ip"));
        configure.setTelnetPort((Integer) commandLine.getOptionValue("telnet-port"));
        configure.setHttpPort((Integer) commandLine.getOptionValue("http-port"));
        //Configure parse javapid=7330,arthasCore=/home/admin/tiny-arthas/arthas-core.jar,sessionTimeOut=1800,targetIp=127.0.0.1,telnetPort=3658,httpPort=8563
        AnsiLog.info("Configure parse javapid=" + configure.getJavaPid() + ",arthasCore=" + configure.getArthasCore()
                + ",sessionTimeOut=" + configure.getSessionTimeout() + ",targetIp=" + configure.getIp() + ",telnetPort=" + configure.getTelnetPort()
                + ",httpPort=" + configure.getHttpPort());

        // 解析启动参数作为配置，并填充到configure对象里面
        return configure;
    }

    // 程序先用获取的所有的java虚拟机，根据已知的pid和对应的VirtualMachine，然后进行VirtualMachine.loadAgent()加载arthas.jar
    // 这里加载agent在主程序启动后获取pid进行进行后加载，这个程序是在java6的新特性，可以在运行后对加载的class进行重新加载
    private void attachAgent(Configure configure) throws Exception {
        // 根据pid获取对应的java虚拟机容器
        VirtualMachineDescriptor virtualMachineDescriptor = null;
        for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
            String pid = descriptor.id();
            AnsiLog.info("virtualMachineDescriptor pid = " + pid);
            //获取pid对应的虚拟机容器
            if (pid.equals(Integer.toString(configure.getJavaPid()))) {
                virtualMachineDescriptor = descriptor;
            }
        }
        VirtualMachine virtualMachine = null;
        try {
            //获取对应需要监控的虚拟机对象，对于jvm内部的attach实现，是通过tools.jar这个包com.sun.tools.attach.VirtualMachine以及
            // VirtualMachine.attach(pid)这种方式来实现的
            // 上面的具体执行内容在arthas-core.jar的主类中，我们来看具体的内容
            if (null == virtualMachineDescriptor) { // 使用 attach(String pid) 这种方式
                AnsiLog.info("virtualMachineDescriptor is null ,attach pid = " + configure.getJavaPid());
                virtualMachine = VirtualMachine.attach("" + configure.getJavaPid());
            } else {
                AnsiLog.info("virtualMachineDescriptor is not null ,attach virtualMachineDescriptor");
                virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
            }
            //获取虚拟机系统参数
            Properties targetSystemProperties = virtualMachine.getSystemProperties();
            String targetJavaVersion = JavaVersionUtils.javaVersionStr(targetSystemProperties);
            AnsiLog.info("targetJavaVersion =" + targetJavaVersion);
            String currentJavaVersion = JavaVersionUtils.javaVersionStr();
            AnsiLog.info("currentJavaVersion =" + currentJavaVersion);
            //比较当前虚拟机和目标虚拟机是否是一致的
            if (targetJavaVersion != null && currentJavaVersion != null) {
                if (!targetJavaVersion.equals(currentJavaVersion)) {
                    AnsiLog.info("Current VM java version: {} do not match target VM java version: {}, attach may fail.",
                            currentJavaVersion, targetJavaVersion);
                    AnsiLog.info("Target VM JAVA_HOME is {}, arthas-boot JAVA_HOME is {}, try to set the same JAVA_HOME.",
                            targetSystemProperties.getProperty("java.home"), System.getProperty("java.home"));
                }
            }

            String arthasAgentPath = configure.getArthasAgent();
            AnsiLog.info("arthasAgentPath =" + arthasAgentPath);
            //convert jar path to unicode string
            configure.setArthasAgent(encodeArg(arthasAgentPath));
            configure.setArthasCore(encodeArg(configure.getArthasCore()));
            //加载agent代理
            // 第一个参数为agent路径。
            // 第二个参数向jar包中的agentmain()方法传递参数，（agent-core.jar包的路径和config序列化之后的字符串)，加载
            //arthas-agent.jar包并运行
            // 动态加载Agent
            //动态加载 arthasAgentPath =/home/admin/tiny-arthas/arthas-agent.jar,
            // arthasCore=%2Fhome%2Fadmin%2Ftiny-arthas%2Farthas-core.jar;;telnetPort=3658;httpPort=8563;ip=127.0.0.1;arthasAgent=%2Fhome%2Fadmin%2Ftiny-arthas%2Farthas-agent.jar;sessionTimeout=1800;arthasCore=%2Fhome%2Fadmin%2Ftiny-arthas%2Farthas-core.jar;javaPid=7330;
            AnsiLog.info("动态加载 arthasAgentPath =" + arthasAgentPath + ",arthasCore=" + (configure.getArthasCore() + ";" + configure.toString()));

            virtualMachine.loadAgent(arthasAgentPath,
                    configure.getArthasCore() + ";" + configure.toString());
        } finally {
            if (null != virtualMachine) {
                AnsiLog.info("virtualMachine to detach");
                virtualMachine.detach();
            }
        }
    }

    private static String encodeArg(String arg) {
        try {
            return URLEncoder.encode(arg, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return arg;
        }
    }

    public static void main(String[] args) {
        try {
            new Arthas(args);
        } catch (Throwable t) {
            AnsiLog.error("Start arthas failed, exception stack trace: ");
            t.printStackTrace();
            System.exit(-1);
        }
    }
}
