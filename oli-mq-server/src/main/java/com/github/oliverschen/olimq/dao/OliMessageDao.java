package com.github.oliverschen.olimq.dao;

import com.github.oliverschen.olimq.pojo.OliMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ck
 */
public interface OliMessageDao extends JpaRepository<OliMessage, Long> {

    /**
     * 通过 ID 和 topicName 查询
     * @param offset ID/偏移量
     * @param topicName topic
     * @return list
     */
    List<OliMessage> findByIdAndTopicName(Long offset, String topicName);
}
