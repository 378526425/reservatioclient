package com.wxmblog.reservation.common.rest.request.product.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: fast
 * @description:
 * @author: Mr.Wang
 * @create: 2023-06-25 16:06
 **/

@Data
public class ProductAdminRequest {

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

}
