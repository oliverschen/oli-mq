package com.github.oliverschen.olimq;


import com.github.oliverschen.olimq.clinet.OliConsumer;
import com.github.oliverschen.olimq.clinet.OliProducer;
import com.github.oliverschen.olimq.core.OliBroker;
import com.github.oliverschen.olimq.core.OliMsg;

public class MainTest {

    public static void main(String[] args) {
        String topic = "test";
        OliBroker broker = OliBroker.builder().build();
        broker.createTopic(topic);
        OliProducer producer = broker.createProducer();
        producer.send(topic, new OliMsg<>("哈哈"));

        OliConsumer consumer = broker.createConsumer();
        consumer.subscribe(topic);
        consumer.consumer(100000);
    }
}
