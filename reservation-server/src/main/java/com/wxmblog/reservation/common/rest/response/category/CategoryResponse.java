package com.wxmblog.reservation.common.rest.response.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @program: fast
 * @description:
 * @author: Mr.Wang
 * @create: 2023-06-21 16:57
 **/

@Data
public class CategoryResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
