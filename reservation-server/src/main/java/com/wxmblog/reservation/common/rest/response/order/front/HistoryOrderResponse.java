package com.wxmblog.reservation.common.rest.response.order.front;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-17 13:52
 **/

@Data
public class HistoryOrderResponse {

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
