package com.github.oliverschen.olimq.clinet;

import com.github.oliverschen.olimq.core.OliBroker;
import com.github.oliverschen.olimq.core.OliMq;
import com.github.oliverschen.olimq.core.OliMsg;
import com.github.oliverschen.olimq.exception.OliException;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author chenkui
 * 消费者
 */
@NoArgsConstructor
public class OliConsumer {

    private OliBroker broker;

    private OliMq mq;

    public OliConsumer(OliBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        OliMq oliMq = this.broker.getTopic(topic);
        Optional.ofNullable(oliMq).orElseThrow(() -> new OliException("topic [" + topic + "] is not found"));
        this.mq = oliMq;
    }

    /**
     * 真正消费消息
     * @param timeout 超时时间
     * @param <T> 泛型
     * @return OliMsg
     */
    public <T> OliMsg<T> consumer(long timeout) {
        return mq.consume(timeout);
    }

}
