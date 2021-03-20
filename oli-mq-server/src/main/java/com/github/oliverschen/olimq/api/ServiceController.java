package com.github.oliverschen.olimq.api;

import com.github.oliverschen.olimq.core.ServiceManager;
import com.github.oliverschen.olimq.pojo.OliMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.github.oliverschen.olimq.constant.SystemConstant.OK;
import static com.github.oliverschen.olimq.constant.SystemConstant.SYSTEM_NAME;

/**
 * @author ck
 */
@RestController
@RequestMapping(SYSTEM_NAME)
public class ServiceController {

    @Autowired
    private ServiceManager serviceManager;

    /**
     * 接收创建消息
     */
    @PostMapping
    public String create(@RequestBody OliMsg<Object> msg) {
        serviceManager.addOrCover(msg);
        return OK;
    }


}
