package com.wxmblog.reservation.common.rest.response.fruser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-17 16:43
 **/

@Data
public class PersonalResponse {

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headPortrait;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "预约记录")
    private Long allOrder;

    @ApiModelProperty(value = "待服务")
    private Long waitOrder;

    @ApiModelProperty(value = "已完成")
    private Long completeOrder;
}
