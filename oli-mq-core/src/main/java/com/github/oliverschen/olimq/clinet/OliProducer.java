package com.github.oliverschen.olimq.clinet;

import com.github.oliverschen.olimq.core.OliBroker;
import com.github.oliverschen.olimq.core.OliMq;
import com.github.oliverschen.olimq.core.OliMsg;
import com.github.oliverschen.olimq.exception.OliException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author chenkui
 * 生产者
 */
@Slf4j
@NoArgsConstructor
public class OliProducer {

    private OliBroker broker;

    public OliProducer(OliBroker broker) {
        this.broker = broker;
    }

    public <T> void send(String topic, OliMsg<T> msg) {
        OliMq mq = Optional.ofNullable(this.broker.getTopic(topic))
                .orElseThrow(() -> new OliException("topic [" + topic + "] is not found"));
        boolean b = mq.sendMsg(msg);
        log.info("send msg [{}] to topic [{}] is {}", msg.toString(), topic, b);
    }

}
