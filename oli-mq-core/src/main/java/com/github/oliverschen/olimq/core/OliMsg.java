package com.github.oliverschen.olimq.core;


import lombok.Data;

import java.util.UUID;

/**
 * @author chenkui
 * 消息实体
 */
@Data
public class OliMsg<T> {

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

    private String omId;
    private T data;
}
