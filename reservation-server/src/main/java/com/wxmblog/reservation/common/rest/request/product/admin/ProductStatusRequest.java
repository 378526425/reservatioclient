package com.wxmblog.reservation.common.rest.request.product.admin;

import com.wxmblog.base.common.annotation.ForeignTable;
import com.wxmblog.reservation.common.enums.ProductStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-08-31 13:44
 **/

@Data
public class ProductStatusRequest {

    @ApiModelProperty(value = "产品状态")
    @NotNull(message = "请选择项目状态")
    private ProductStatus status;

    @ApiModelProperty(value = "产品id")
    @ForeignTable(value = "product")
    @NotNull(message = "请选择项目")
    private Integer id;
}
