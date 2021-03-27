package com.github.oliverschen.olimq.api;

import com.github.oliverschen.olimq.core.ServiceManager;
import com.github.oliverschen.olimq.pojo.OliMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String create(@RequestBody OliMessage msg) {
        serviceManager.addOrCover(msg);
        return OK;
    }

    /**
     * 获取消息
     * 没有指定 offset 时全量拉取，如果传了 offset 时在 offset 处拉取
     *
     * @param offset 偏移量
     * @param topic topic
     * @return oliMsg<T>
     */
    @GetMapping
    public List<OliMessage> obtain(@RequestParam("offset") Long offset,
                                       @RequestParam("topic") String topic) {
        return serviceManager.obtain(offset,topic);
    }


}
