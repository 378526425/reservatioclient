package com.wxmblog.reservation.common.rest.response.product.front;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-10 13:42
 **/

@Data
public class SummaryResponse {

    @ApiModelProperty(value = "主图")
    private String mainImg;

    @ApiModelProperty(value = "预览图")
    private String preImg;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "营业时间")
    private String time;

    @ApiModelProperty(value = "位置")
    private String location;
}
