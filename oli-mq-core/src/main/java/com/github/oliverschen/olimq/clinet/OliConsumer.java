package com.github.oliverschen.olimq.clinet;

import com.github.oliverschen.olimq.core.OliBroker;
import com.github.oliverschen.olimq.core.OliMq;
import com.github.oliverschen.olimq.core.OliMsg;
import com.github.oliverschen.olimq.exception.OliException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenkui
 * 消费者
 */
@Slf4j
@NoArgsConstructor
public class OliConsumer {

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
        OliMq oliMq = this.broker.getTopic(topic);
        Optional.ofNullable(oliMq).orElseThrow(() -> new OliException("topic [" + topic + "] is not found"));
        this.mq = oliMq;
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
            log.info("consumer offset is [{}]", andIncrement);
        } catch (Exception e) {
            log.error("message consumer exception : ：", e);
        }
        return msg;
    }

}
