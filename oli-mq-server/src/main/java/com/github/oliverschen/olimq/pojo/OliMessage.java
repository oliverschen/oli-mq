package com.github.oliverschen.olimq.pojo;

import java.io.Serializable;

/**
 * @author ck
 */
public class OliMessage implements Serializable {

    public OliMessage() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 消息头
     */
    private Object header;

    /**
     * 唯一消息 ID，自增
     */
    private Long messageId;

    /**
     * 数据
     */
    private Object data;

    /**
     * 时间戳
     */
    private long timestamp;


    /**
     * 分组
     */
    private String groupId;

    /**
     * topic 名称
     */
    private String topicName;

    public void setHeader(Object header) {
        this.header = header;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Object getHeader() {
        return header;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Object getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getTopicName() {
        return topicName;
    }
}
