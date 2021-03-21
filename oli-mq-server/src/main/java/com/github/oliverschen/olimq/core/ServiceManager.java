package com.github.oliverschen.olimq.core;

import com.github.oliverschen.olimq.exception.OliException;
import com.github.oliverschen.olimq.pojo.OliMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ck
 */
@Component
public class ServiceManager {

    Logger log = LoggerFactory.getLogger(ServiceManager.class);

    public static final OliBroker BROKER = new OliBroker();

    /**
     * 添加或者覆盖 topic
     *
     * @param msg 消息内容
     */
    public void addOrCover(OliMsg<Object> msg) {
        String topic = msg.getTopicName();
        BROKER.createTopic(topic);

        OliMq mq = Optional.ofNullable(BROKER.getTopic(topic))
                .orElseThrow(() -> new OliException("topic [" + topic + "] is not found"));
        boolean b = mq.sendMsg(msg);
        log.info("send msg [{}] to topic [{}] is {}", msg.getData(), topic, b);
    }
}
