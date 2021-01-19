package com.arthas.service.shell.system.impl;

import com.arthas.service.GlobalOptions;
import com.arthas.service.shell.cli.CliToken;
import com.arthas.service.shell.handlers.Handler;
import com.arthas.service.shell.impl.ShellImpl;
import com.arthas.service.shell.system.Job;
import com.arthas.service.utils.LogUtil;
import com.taobao.middleware.logger.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 全局的Job Controller，不应该存在启停的概念，不需要在连接的断开时关闭，
 * 
 * @author gehui 2017年7月31日 上午11:55:41
 */
public class GlobalJobControllerImpl extends JobControllerImpl {

    private Timer timer = new Timer("job-timeout", true);
    private Map<Integer, TimerTask> jobTimeoutTaskMap = new HashMap<Integer, TimerTask>();
    private static final Logger logger = LogUtil.getArthasLogger();

    @Override
    public void close(final Handler<Void> completionHandler) {
        if (completionHandler != null) {
            completionHandler.handle(null);
        }
    }

    @Override
    public void close() {
        timer.cancel();
        jobTimeoutTaskMap.clear();
        for (Job job : jobs()) {
            job.terminate();
        }
    }

    @Override
    public boolean removeJob(int id) {
        TimerTask jobTimeoutTask = jobTimeoutTaskMap.remove(id);
        if (jobTimeoutTask != null) {
            jobTimeoutTask.cancel();
        }
        return super.removeJob(id);
    };

    @Override
    public Job createJob(InternalCommandManager commandManager, List<CliToken> tokens, ShellImpl shell) {
        final Job job = super.createJob(commandManager, tokens, shell);

        /*
         * 达到超时时间将会停止job
         */
        TimerTask jobTimeoutTask = new TimerTask() {
            @Override
            public void run() {
                job.terminate();
            }
        };
        Date timeoutDate = new Date(System.currentTimeMillis() + (getJobTimeoutInSecond() * 1000));
        timer.schedule(jobTimeoutTask, timeoutDate);
        jobTimeoutTaskMap.put(job.id(), jobTimeoutTask);
        job.setTimeoutDate(timeoutDate);

        return job;
    }

    private long getJobTimeoutInSecond() {
        long result = -1;
        String jobTimeoutConfig = GlobalOptions.jobTimeout.trim();
        try {
            char unit = jobTimeoutConfig.charAt(jobTimeoutConfig.length() - 1);
            String duration = jobTimeoutConfig.substring(0, jobTimeoutConfig.length() - 1);
            switch (unit) {
            case 'h':
                result = TimeUnit.HOURS.toSeconds(Long.parseLong(duration));
                break;
            case 'd':
                result = TimeUnit.DAYS.toSeconds(Long.parseLong(duration));
                break;
            case 'm':
                result = TimeUnit.MINUTES.toSeconds(Long.parseLong(duration));
                break;
            case 's':
                result = Long.parseLong(duration);
                break;
            default:
                result = Long.parseLong(jobTimeoutConfig);
                break;
            }
        } catch (Exception e) {
        }

        if (result < 0) {
            // 如果设置的属性有错误，那么使用默认的1天
            result = TimeUnit.DAYS.toSeconds(1);
            logger.warn("Configuration with job timeout " + jobTimeoutConfig + " is error, use 1d in default.");
        }
        return result;
    }
}
