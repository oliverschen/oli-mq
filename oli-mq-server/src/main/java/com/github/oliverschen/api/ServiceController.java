package com.github.oliverschen.api;

import com.github.oliverschen.pojo.OliMsg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.oliverschen.constant.SystemConstant.OK;
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
    public String create(@RequestBody OliMsg<Object> msg) {


        return OK;
    }




}
