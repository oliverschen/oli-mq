package com.github.oliverschen.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.oliverschen.constant.SystemConstant.SYSTEM_NAME;

/**
 * @author ck
 */
@RestController
@RequestMapping(SYSTEM_NAME)
public class ServiceController {

    /**
     * 接收创建消息
     */
    @PostMapping
    public void create() {

    }




}
