package com.github.oliverschen.olimq.pojo;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ck
 */
@Entity
@Table(name = "oli_message")
@EntityListeners(AuditingEntityListener.class)
public class OliMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header",columnDefinition = "varchar(1024) comment '消息头'")
    private String header;

    @Column(name = "data",columnDefinition = "text comment '数据'")
    private String data;

    @CreatedDate
    @Column(name = "timestamp",columnDefinition = "bigint comment '时间戳'")
    private long timestamp;

    @Column(name = "group_id",columnDefinition = "varchar(30) comment '分组'")
    private String groupId;

    @Column(name = "topic_name",columnDefinition = "varchar(40) comment 'topic名称'")
    private String topicName;

    public void setHeader(String header) {
        this.header = header;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
