package com.github.oliverschen.olimq.core;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列
 * 这个类的实例对象保存当前 topic 的数据
 * @author chenkui
 */
@Data
@NoArgsConstructor
public class OliMq {

    /**
     * 绑定 topic
     */
    private String topic;

    /**
     * topic 阻塞队列
     */
    private LinkedBlockingQueue<Object> queue;

    public OliMq(final String topic,final Integer capital) {
        this.topic = topic;
        this.queue = new LinkedBlockingQueue<>(capital);
    }

}
