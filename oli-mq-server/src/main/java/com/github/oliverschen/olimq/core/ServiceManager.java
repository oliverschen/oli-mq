package com.github.oliverschen.olimq.core;

import com.github.oliverschen.olimq.dao.OliMessageDao;
import com.github.oliverschen.olimq.exception.OliException;
import com.github.oliverschen.olimq.pojo.OliMessage;
import com.github.oliverschen.olimq.pojo.OliMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ck
 */
@Component
public class ServiceManager {

    Logger log = LoggerFactory.getLogger(ServiceManager.class);

    @Autowired
    private OliMessageDao messageDao;

    /**
     * 添加或者覆盖 topic
     *
     * @param msg 消息内容
     */
    public void addOrCover(OliMessage msg) {
        String topic = msg.getTopicName();
        OliMessage save = messageDao.save(msg);
        OliMessage mq = Optional.of(save).orElseThrow(() -> new OliException("topic [" + topic + "] is not found"));
        log.info("send msg [{}] to topic [{}] is {}", msg.getData(), topic,mq.getId());
    }

    /**
     * 拉取消息
     * @param offset 偏移量
     * @return
     */
    public OliMsg<Object> obtain(Integer offset) {
        return null;
    }
}
