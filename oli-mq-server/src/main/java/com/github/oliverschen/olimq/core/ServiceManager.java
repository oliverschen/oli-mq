package com.github.oliverschen.olimq.core;

import com.github.oliverschen.olimq.dao.OliMessageDao;
import com.github.oliverschen.olimq.exception.OliException;
import com.github.oliverschen.olimq.pojo.OliMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
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
     * 如果有偏移量，按照偏移量每次拉取消费，如果没有偏移量从 0 开始拉取
     *
     * @param offset 偏移量
     * @param topic  topic
     */
    public List<OliMessage> obtain(Long offset, String topic) {
        if (Objects.isNull(offset)) {
            offset = 0L;
        }
        return messageDao.findByIdAndTopicName(offset, topic);
    }
}
