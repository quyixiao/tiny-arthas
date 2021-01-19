package com.arthas.core.lock;



import com.arthas.core.utils.DateUtil;
import com.arthas.core.utils.LogUtil;
import com.taobao.middleware.logger.Logger;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/***
 * 只适用于单台机器
 */
public class ReentrantLockUtils {



    private static Logger log = LogUtil.getArthasLogger();


    public static final ReentrantLock LOCK = new ReentrantLock();

    final static Random random = new Random();

    public static final Map<String, Long> contain = new ConcurrentHashMap<String,Long>(128);

    public static int count = 0;


    public static int testCount = 0;


    public static int mapCount = 0;


    /**
     * 重试 获取锁
     *
     * @param key
     * @param lockTime ，获得锁以后的锁定时间
     * @return
     */
    public static boolean getLock(final String key, final Long lockTime) {
        boolean flag = false;
        try {
            if (set(key, lockTime)) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            log.error("getLock error", e.getMessage());
        }
        return flag;
    }


    /**
     * 重试 获取锁
     *
     * @param key
     * @param lockTime ，获得锁以后的锁定时间
     * @return
     */
    public static boolean lockTryTimes(final String key, final Long tryTimes, final Long lockTime) {
        boolean flag = false;
        try {
            // 请求锁超时时间，毫秒
            long timeout = tryTimes * 1000;
            // 系统当前时间，毫秒
            long nowTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - nowTime) < timeout) {
                if (set(key, lockTime)) {
                    flag = true;
                    break;
                }
                log.info("reentrantLock lockTryTimes key=" + key + ",wait time =" + (System.currentTimeMillis() - nowTime), 2);
                // 每次请求等待一段时间
                seleep(10, 50000);
            }
        } catch (Exception e) {
            log.error("getLock error", e.getMessage());
        }
        return flag;
    }


    /**
     * @param millis 毫秒
     * @param nanos  纳秒
     * @Title: seleep
     * @Description: 线程等待时间
     * @author yuhao.wang
     */
    private static void seleep(long millis, int nanos) {
        try {
            Thread.sleep(millis, random.nextInt(nanos));
        } catch (InterruptedException e) {
            log.info("获取分布式锁休眠被中断：", e);
        }
    }

    public static boolean set(String key, Long time) {
        if (contain.containsKey(key)) {
            return false;
        }
        Date date = DateUtil.addSecond(new Date(), time.intValue());
        LOCK.lock();
        try {
            if (contain.containsKey(key)) {
                return false;
            }
            ++count;
            mapCount++;
            if (count / 1000 > 0) {            //如果数据使用次数超过1000次，将清理数据
                count = 0;                      //重置数据
                log.info("ReentrantLock 重新清理数据");
                mapCount = 0;
                if (!contain.isEmpty()) {
                    Map<String, Long> contains = new ConcurrentHashMap<String, Long>();
                    Long currentTime = new Date().getTime();
                    for (Map.Entry<String, Long> m : contain.entrySet()) {
                        if (m.getValue() > currentTime) {         // 表示没有过期
                            contains.put(m.getKey(), m.getValue());
                            mapCount++;
                        }
                    }
                    contain.clear();
                    contain.putAll(contains);
                }
            }
            //log.info("ReentrantLock add key =" + key + " ,expr=" + DateUtil.formatDateToYYYYMMddHHmmss(date) + ",count=" + count + ",mapCount=" + mapCount);
            contain.put(key, date.getTime());
            return true;
        } catch (Exception e) {
            log.error("ReentrantLock set exception key," + key,e.getMessage());
        } finally {
            LOCK.unlock();
        }
        return false;
    }

    public static void unLock(String key) {
        try {
            contain.remove(key);
            mapCount--;
            //  log.info("ReentrantLock remove key =" + key + ",mapCount=" + mapCount);
        } catch (Exception e) {
            log.error("ReentrantLock unLock exception key," + key, e.getMessage());
        }
    }



}
