package com.github.oliverschen.olimq.pojo;

import com.github.oliverschen.olimq.core.OliBroker;
import com.github.oliverschen.olimq.core.OliMq;
import com.github.oliverschen.olimq.exception.OliException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenkui
 * 消费者
 */
public class OliConsumer {

    Logger log = LoggerFactory.getLogger(OliConsumer.class);

    private OliBroker broker;

    private OliMq mq;

    /**
     * 记录当前消费者消费位置
     */
    private final AtomicInteger offset = new AtomicInteger();

    public OliConsumer(OliBroker broker) {
        this.broker = broker;
    }

    /**
     * 订阅主题
     * @param topic 主题名称
     */
    public void subscribe(String topic) {
        this.mq = Optional.ofNullable(this.broker.getTopic(topic))
                .orElseThrow(() -> new OliException("topic [" + topic + "] is not found"));
    }

    /**
     * 真正消费消息
     * @param <T> 泛型
     * @return OliMsg
     */
    public <T> OliMsg<T> consumer() {
        OliMsg<T> msg = null;
        try {
            msg = mq.consume(offset.get());
            // 消息确认消费成功 ack 之后再增加 offset 位置
            int andIncrement = offset.getAndIncrement();
            log.info("consumer offset is ===> [{}]", andIncrement);
        } catch (Exception e) {
            log.error("message consumer exception : ：", e);
        }
        return msg;
    }

}
