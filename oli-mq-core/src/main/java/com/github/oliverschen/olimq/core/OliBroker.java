package com.github.oliverschen.olimq.core;

import com.github.oliverschen.olimq.clinet.OliConsumer;
import com.github.oliverschen.olimq.clinet.OliProducer;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;


/**
 * broker 信息
 * 1. 创建 topic
 * 2. 缓存 topic 信息，一个 topic 中包含一个 队列，队列里面存放多条同类的消息
 *
 * @author chenkui
 */
@Data
@Builder
@NoArgsConstructor
public class OliBroker {

    /**
     * 数组容量
     */
    private static final Integer CAPITAL = 1000;

    /**
     * topic 容器
     */
    private static ConcurrentHashMap<String, OliMq> topics = new ConcurrentHashMap<>(64);

    /**
     * 创建 topic
     * @param topic 主题名称
     */
    public void createTopic(String topic) {
        topics.putIfAbsent(topic, new OliMq(topic, CAPITAL));
    }

    /**
     * 创建生产者
     * @return OliProducer
     */
    public OliProducer createProducer() {
        return new OliProducer(this);
    }

    /**
     * 创建消费者
     * @return OliConsumer
     */
    public OliConsumer createConsumer() {
        return new OliConsumer(this);
    }

    /**
     * @param topic topic
     * @return Olimq
     */
    public OliMq getTopic(String topic) {
        return topics.get(topic);
    }
}
