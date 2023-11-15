package com.wxmblog.reservation.common.rest.response.order.front;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxmblog.reservation.common.enums.OrderStatus;
import com.wxmblog.reservation.common.enums.TimeUnit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-18 10:57
 **/

@Data
public class UserOrderPageResponse {

    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 预览图片
     */
    @ApiModelProperty(value = "预览图片")
    private String preImg;


    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private OrderStatus status;
}
