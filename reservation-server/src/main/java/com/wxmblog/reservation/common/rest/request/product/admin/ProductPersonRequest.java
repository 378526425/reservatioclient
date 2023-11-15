package com.wxmblog.reservation.common.rest.request.product.admin;

import com.wxmblog.base.common.annotation.ForeignTable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-12 11:20
 **/

@Data
public class ProductPersonRequest {

    @ApiModelProperty(value = "产品id")
    @NotNull(message = "请选择项目")
    @ForeignTable(value = "product")
    private Integer productId;

    @ApiModelProperty(value = "人员集合")
    private List<Integer> personIds;
}
