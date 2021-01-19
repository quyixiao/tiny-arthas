package com.arthas.core.worker.impl;

import com.arthas.core.utils.ThreadUtil;
import com.arthas.core.worker.AbstractWorker;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map;

public class ThreadWorker extends AbstractWorker<String> {

    private int sampleInterval = 100;

    private static ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    private Integer topNBusy = null;


    @Override
    public String getMessage() {
        topNBusy = 3;
        logger.info("ThreadWorker ......");
        Map<Long, Long> topNThreads = ThreadUtil.getTopNThreads(sampleInterval, topNBusy);
        Long[] tids = topNThreads.keySet().toArray(new Long[0]);
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(ArrayUtils.toPrimitive(tids), true, true);
        if (threadInfos == null) {
            logger.info("thread do not exist! id ");
            return "thread do not exist! id \n";
        } else {
            for (ThreadInfo info : threadInfos) {
                String stacktrace = ThreadUtil.getFullStacktrace(info, topNThreads.get(info.getThreadId()));
                logger.info("stackTrace  = " + stacktrace);
                sendDingTalk(stacktrace);
            }
        }
        return null;
    }
}
