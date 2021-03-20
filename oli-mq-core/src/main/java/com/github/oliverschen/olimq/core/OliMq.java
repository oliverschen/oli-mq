package com.github.oliverschen.olimq.core;

import com.github.oliverschen.olimq.exception.OliException;
import com.github.oliverschen.olimq.pojo.OliMsg;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个类的实例对象保存当前 topic 的数据
 * 使用数组记录当前生产者生产的消息位置和消费者已经消费的位置
 * 不足：
 *     1. 没有持久化，重启之后会丢失数据
 *     2. 数据保存在内存中，有 OOM 的风险
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
     * 消息数组：数组的形式记录，设置消费指针和生产指针
     * 消费指针：当前消费者消费消息的偏移位置
     * 生产指针：当前生产者生产消息的偏移位置
     */
    private OliMsg[] queue;

    /**
     * 生产者生产位置指针
     */
    private final AtomicInteger head = new AtomicInteger(0);


    public OliMq(final String topic,final Integer capital) {
        this.topic = topic;
        this.queue = new OliMsg[capital];
    }


    /**
     * 发送消息
     * @param msg 消息
     * @return 是否添加成功
     */
    public <T> boolean sendMsg(OliMsg<T> msg) {
        log.info("topic [{}] this head is===> [{}] add msg [{}]", this.topic, head.get(), msg);
        if (queue.length == head.get()) {
            throw new OliException("error: queue is full");
        }
        queue[head.get()] = msg;
        head.incrementAndGet();
        return true;
    }

    /**
     * 消费消息
     * @param offset 消费位置
     * @return OliMsg
     * 抛出异常让外层可以捕获到，以便进行消息是否消费成功，实现一个简单的 ack 机制
     */
    public <T> OliMsg<T> consume(final Integer offset) throws Exception{
        if (offset < 0 || offset > queue.length) {
            throw new OliException("offset is invalid !");
        }
        if (offset.equals(0) && queue[0] == null) {
            log.warn("offset == 0 and queue[0] is null");
            // offset 外层要 +1 这里先 -1，当消费者消费是第一个位置没有元素的情况时出现
            return null;
        }
        OliMsg<T> msg = queue[offset];
        log.info("consume topic [{}], msg info is [{}]", this.topic, msg);
        return msg;
    }
}
