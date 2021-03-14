package com.github.oliverschen.olimq;


import com.github.oliverschen.olimq.clinet.OliConsumer;
import com.github.oliverschen.olimq.clinet.OliProducer;
import com.github.oliverschen.olimq.core.OliBroker;
import com.github.oliverschen.olimq.core.OliMsg;

import java.util.concurrent.ThreadLocalRandom;

public class MainTest {

    public static void main(String[] args) {
        String topic = "test" + ThreadLocalRandom.current().nextInt(1000);
        OliBroker broker = OliBroker.builder().build();
        broker.createTopic(topic);
        OliProducer producer = broker.createProducer();
        new Thread(() -> {
            while (true) {
                producer.send(topic, new OliMsg<>("哈哈"));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        OliConsumer consumer = broker.createConsumer();
        consumer.subscribe(topic);
        new Thread(() -> {
            while (true) {
                consumer.consumer();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
