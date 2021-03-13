package com.github.oliverschen.olimq.core;


import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 这个类的实例对象保存当前 topic 的数据
 * @author chenkui
 */
@Slf4j
@NoArgsConstructor
public class OliMq {

    /**
     * 绑定 topic
     */
    private String topic;

    /**
     * topic 阻塞队列
     */
    private LinkedBlockingQueue<OliMsg> queue;

    public OliMq(final String topic,final Integer capital) {
        this.topic = topic;
        this.queue = new LinkedBlockingQueue<>(capital);
    }


    /**
     * 发送消息
     * @param msg 消息
     * @return 是否添加成功
     */
    public <T> boolean sendMsg(OliMsg<T> msg) {
        log.info("topic [{}] add msg [{}]", this.topic, msg);
        return queue.offer(msg);
    }

    /**
     * 消费消息
     * @param timeout 超时
     * @return OliMsg
     */
    @SneakyThrows
    public <T> OliMsg<T> consume(long timeout) {
        OliMsg<T> msg = queue.poll(timeout, TimeUnit.SECONDS);
        log.info("consume topic [{}], msg info is [{}]", this.topic, msg);
        return msg;
    }
}
