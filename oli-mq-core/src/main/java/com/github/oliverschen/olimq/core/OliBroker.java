package com.github.oliverschen.olimq.core;

import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;


/**
 * broker 信息
 * 1. 创建 topic
 * 2. 缓存 topic 信息，一个 topic 中包含一个 队列，队列里面存放多条同类的消息
 *
 * @author chenkui
 */
@Data
public class OliBroker {

    /**
     * 队列的容量
     */
    private static final Integer CAPITAL = 1000;

    /**
     * topic 容器
     */
    private static ConcurrentHashMap<String, Object> topics = new ConcurrentHashMap<>(64);

    /**
     * 创建 topic
     * @param topic 主题名称
     * @return result
     */
    public boolean createTopic(String topic) {
        topics.putIfAbsent(topic, new OliMq(topic, CAPITAL));
        return true;
    }
}
