package com.github.oliverschen.olimq.pojo;


import java.util.UUID;

/**
 * @author chenkui
 * 消息实体
 */
public class OliMsg<T> {

    private String omId;
    private String topicName;
    private T data;

    /**
     * 设置唯一消息ID
     */
    public OliMsg() {
        this.omId = UUID.randomUUID().toString();
    }

    public OliMsg(T data) {
        this();
        this.data = data;
    }

    public OliMsg(String topicName, T data) {
        this();
        this.topicName = topicName;
        this.data = data;
    }

    public void setOmId(String omId) {
        this.omId = omId;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getOmId() {
        return omId;
    }

    public String getTopicName() {
        return topicName;
    }

    public T getData() {
        return data;
    }

}
