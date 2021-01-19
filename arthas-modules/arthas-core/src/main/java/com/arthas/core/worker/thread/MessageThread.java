package com.arthas.core.worker.thread;

import com.alibaba.fastjson.JSON;
import com.arthas.core.t.OneTuple;
import com.arthas.core.t.Tuple;
import com.arthas.core.utils.LogUtil;
import com.arthas.core.worker.AbstractWorker;
import com.taobao.middleware.logger.Logger;

import java.lang.reflect.Modifier;


public class MessageThread implements Runnable {


    private static Logger logger = LogUtil.getArthasLogger();
    private Tuple tuple;

    public MessageThread() {

    }

    public MessageThread(Tuple tuple) {
        this.tuple = tuple;
    }

    @Override
    public void run() {
        try {
            OneTuple<String> data = tuple.getData();
            String className = data.getFirst();
            Class obj = Class.forName(className); // 以String类型的className实例化类
            boolean isAbstract = Modifier.isAbstract(obj.getModifiers());
            if (!isAbstract) {
                logger.info("is not abstract className=" + className +  " to new instance ");
                AbstractWorker worker = (AbstractWorker) obj.newInstance();
                worker.invoke(new Tuple());
            }
        } catch (Exception e) {
            logger.error("NoticeThread exception = " + JSON.toJSONString(this.tuple), e.getMessage());
        } finally {

        }
    }


}
