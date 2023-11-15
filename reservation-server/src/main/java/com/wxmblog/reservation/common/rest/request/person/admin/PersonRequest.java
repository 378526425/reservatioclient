package com.wxmblog.reservation.common.rest.request.person.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-11 14:23
 **/

@Data
public class PersonRequest {

    @ApiModelProperty(value = "姓名")
    private String name;
}
