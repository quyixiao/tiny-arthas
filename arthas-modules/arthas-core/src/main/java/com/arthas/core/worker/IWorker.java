package com.arthas.core.worker;


import com.arthas.core.t.Tuple;

public interface IWorker<T> {

    //初始化数据
    void commonInit(Tuple tuple) throws Exception;

    //初始化数据
    void init(Tuple tuple) throws Exception;

    //验证号码有效性
    Tuple verify(Tuple tuple);

    /**
     * 执行入口
     *
     * @param mobileMsgTemplate 信息
     */
    T invoke(Tuple t) throws Exception;

    // 处理业务数据
    T getMessage();

    /***
     * 发送钉钉消息
     */
    void sendDingTalk(T message);

}
