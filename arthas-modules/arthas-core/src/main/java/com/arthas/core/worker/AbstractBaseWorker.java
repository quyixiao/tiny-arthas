package com.arthas.core.worker;


import com.arthas.core.lock.ReentrantLockUtils;
import com.arthas.core.t.Tuple;
import com.arthas.core.utils.Constants;
import com.arthas.core.utils.LogUtil;
import com.arthas.core.utils.StringUtils;
import com.taobao.middleware.logger.Logger;

public abstract class AbstractBaseWorker<T> {

    public static Logger logger = LogUtil.getArthasLogger();

    public void commonInit(Tuple tuple) throws Exception {

    }

    public void init(Tuple tuple) throws Exception {

    }

    public Tuple verify(Tuple tuple) {

        return new Tuple(true);
    }

    public boolean getLock(String _LOCK) {
        String key = getKey(_LOCK);
        boolean flag = true;
        if (StringUtils.isNotBlank(key)) {                             //如果需要添加分布式锁
            flag = ReentrantLockUtils.getLock(key, Constants.SECOND_OF_ONE_MINITS);
        }
        return flag;
    }


    public boolean getLockTryTimes(String _LOCK) {
        String key = getKey(_LOCK);
        logger.info("get lock key =" + key);
        boolean flag = true;
        if (StringUtils.isNotBlank(key)) {                             //如果需要添加分布式锁
            flag = ReentrantLockUtils.lockTryTimes(key, Constants.SECOND_OF_ONE_MINITS, Constants.SECOND_OF_ONE_MINITS);
        }
        return flag;
    }


    public String getKey(String _LOCK) {

        return null;
    }


    public T invoke(Tuple tuple) throws Exception {


        return null;
    }


    public T handler(Tuple tuple) throws Exception {


        return null;

    }


    protected String getSimpleClassName() {

        return this.getClass().getSimpleName();

    }


}
