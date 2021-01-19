package com.arthas.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolManager {
    private final static int CORE_POOL_SIZE = 10;
    private final static int MAXIMUM_POOL_SIZE = Integer.MAX_VALUE;
    private final static int KEEP_ALIVE_TIME = 60;
    private static ExecutorService instance = null;

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolManager.class);


    public static synchronized ExecutorService getInstance() {
        return newInstance();
    }

    public static synchronized ExecutorService newInstance() {
        if (null == instance) {
            instance = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE_TIME, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        }
        return instance;
    }


}
