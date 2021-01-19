package com.arthas.core.worker;


import com.alibaba.fastjson.JSON;
import com.arthas.core.lock.ReentrantLockUtils;
import com.arthas.core.t.Tuple;
import com.arthas.core.t.TwoTuple;
import com.arthas.core.utils.LogUtil;
import com.arthas.core.utils.StringUtils;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.middleware.logger.Logger;

public abstract class AbstractWorker<T> extends AbstractBaseWorker<T> implements IWorker<T> {

    public final static String _LOCK = "_USER_TRANSFER_LOCK";
    //https://oapi.dingtalk.com/robot/send?access_token=cdb4e271d0c2803fa4be1ab75d13cc3ae49f901d98a44b6cca7891e3f6a6a294

    protected String dingTalkUrlPre = "https://oapi.dingtalk.com/robot/send?access_token=";

    public static Logger logger = LogUtil.getArthasLogger();

    @Override
    public T invoke(Tuple tuple) {
        T t = null;
        boolean flag = false;
        try {
            init(tuple);
            if (getLock(_LOCK)) {
                flag = true;
                TwoTuple<Boolean, T> data = verify(tuple).getData();
                if (data.getFirst()) {
                    sendDingTalk(getMessage());
                    return t;
                }
                return data.getSecond();
            } else {
                logger.info(getSimpleClassName() + " worker key is =" + getKey(_LOCK) + " not get lock");
            }
        } catch (Exception e) {
            logger.error(getSimpleClassName() + " worker 请求参数 =" + JSON.toJSONString(tuple) + " ，返回参数=" + JSON.toJSONString(t), e.getMessage());
        } finally {
            if (flag) {
                ReentrantLockUtils.unLock(getKey(_LOCK));
            }
        }
        return null;
    }

    public void sendDingTalk(T message) {
        try {
            if (message == null) {
                logger.info("message is null");
                return;
            }
            String txt = "";
            if (message instanceof String) {
                txt = (String) message;
                if (StringUtils.isBlank(txt)) {
                    logger.info("txt is null");
                    return;
                }
            }
            String token = "cdb4e271d0c2803fa4be1ab75d13cc3ae49f901d98a44b6cca7891e3f6a6a294";
            DingTalkClient client = new DefaultDingTalkClient(dingTalkUrlPre + token);
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");

            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(txt);
            request.setText(text);
            // 被@人的手机号(在text内容里要有@手机号)
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setIsAtAll("true");
            request.setAt(at);
            OapiRobotSendResponse response = client.execute(request);
            logger.info("response=" + JSON.toJSONString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
