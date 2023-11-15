package com.wxmblog.reservation.common.rest.request.order.admin;

import com.wxmblog.reservation.common.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-14 17:50
 **/

@Data
public class OrderAdminRequest {

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private OrderStatus status;

    /**
     * 预约人姓名
     */
    @ApiModelProperty(value = "预约人姓名")
    private String contacts;
    /**
     * 联系手机号
     */
    @ApiModelProperty(value = "联系手机号")
    private String phone;


}
