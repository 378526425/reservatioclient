package com.wxmblog.reservation.common.rest.request.fruser.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-08-17 17:23
 **/

@Data
public class FrUserPageRequest {

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "人员名称")
    private String personName;
}
