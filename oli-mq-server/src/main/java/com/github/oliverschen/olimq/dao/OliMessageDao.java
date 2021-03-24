package com.github.oliverschen.olimq.dao;

import com.github.oliverschen.olimq.pojo.OliMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ck
 */
public interface OliMessageDao extends JpaRepository<OliMessage,Long> {
}
