package com.wxmblog.reservation.service;

import com.wxmblog.reservation.entity.ProductOrderEntity;
import org.springframework.scheduling.annotation.Async;

public interface SmsSendService {

    /**
     * @Description: 通知用户已服务
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2023/8/21 下午4:00
     */
    @Async
    void sendMsg(Integer id);

    /*
     * @Author wanglei
     * @Description  用户下单提醒服务人员
     * @Date 16:39 2023/8/19
     * @Param
     * @return
     **/
    @Async
    void sendMsgToPerson(ProductOrderEntity productOrderEntity);


    /**
     * @Description: 用户取消提醒服务人员
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2023/8/21 下午4:00
     */
    @Async
    void sendMsgToPersonCancel(ProductOrderEntity productOrderEntity);

    /**
     * @Description: 提醒用户即将开始
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2023/8/21 下午3:59
     */
    @Async
    void serviceStart(Integer id);
}
