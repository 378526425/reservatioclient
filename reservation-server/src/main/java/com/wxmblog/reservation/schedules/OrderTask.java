package com.wxmblog.reservation.schedules;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wxmblog.base.file.common.enums.FileStatusEnum;
import com.wxmblog.base.file.entity.MsfFileEntity;
import com.wxmblog.reservation.common.enums.OrderStatus;
import com.wxmblog.reservation.entity.ProductOrderEntity;
import com.wxmblog.reservation.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 需求相关定时任务
 * @Author: Mr.Wang
 * @date 2021/5/24 上午11:29
 */

@EnableScheduling
@Component
public class OrderTask {

    @Autowired
    ProductOrderService productOrderService;

    /**
     * 凌晨同步 设置过期
     *
     * @return
     */
    @Transactional
    @Scheduled(cron = "0 01 00 ? * *")
    public void autoEvaluation() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -4);
        Wrapper<ProductOrderEntity> wrapper = new UpdateWrapper<ProductOrderEntity>().lambda()
                .eq(ProductOrderEntity::getStatus, OrderStatus.WAIT)
                .le(ProductOrderEntity::getStartTime, calendar.getTime())
                .set(ProductOrderEntity::getStatus, OrderStatus.EXPIRED);
        productOrderService.update(wrapper);
    }

}
