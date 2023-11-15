package com.wxmblog.reservation.common.rest.request.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: fast
 * @description:
 * @author: Mr.Wang
 * @create: 2023-06-20 15:28
 **/

@Data
public class EditCategoryRequest {

    @ApiModelProperty(value = "id 无添加 有修改")
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    @NotBlank(message = "请选择分类")
    private String name;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "请输入排序")
    private Integer sort;
}
