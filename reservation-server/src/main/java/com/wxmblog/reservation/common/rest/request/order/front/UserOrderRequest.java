package com.wxmblog.reservation.common.rest.request.order.front;

import com.wxmblog.reservation.common.enums.UserOrderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-18 10:40
 **/

@Data
public class UserOrderRequest {

    @ApiModelProperty("订单类型 WAIT(\"待服务\"),\n" +
            "SERVEB(\"已服务\")\n" +
            "INVALID(\"过期/取消\")")
    private UserOrderEnum orderType;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userId;
}
