package com.wxmblog.reservation.common.rest.request.product.front;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-17 14:35
 **/

@Data
public class ProductRequest {

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;
}
