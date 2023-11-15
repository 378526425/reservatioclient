package com.wxmblog.reservation.common.rest.request.fruser.admin;

import com.wxmblog.base.common.annotation.ForeignTable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-08-18 15:12
 **/

@Data
public class UserToPersonRequest {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "请选择用户")
    @ForeignTable(value = "fr_user")
    private Integer userId;

    @ApiModelProperty(value = "人员id")
    @NotNull(message = "请选择人员")
    @ForeignTable(value = "person")
    private Integer personId;
}
