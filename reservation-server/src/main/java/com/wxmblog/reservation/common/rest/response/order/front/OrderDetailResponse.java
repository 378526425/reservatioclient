package com.wxmblog.reservation.common.rest.response.order.front;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxmblog.reservation.common.enums.OrderStatus;
import com.wxmblog.reservation.common.enums.TimeUnit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-18 14:12
 **/

@Data
public class OrderDetailResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @ApiModelProperty(value = "预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;


    /**
     * 时长
     */
    @ApiModelProperty(value = "时长")
    private Integer duration;
    /**
     * 时长单位
     */
    @ApiModelProperty(value = "时长单位 DAY(\"天\"),\n" +
            "    HOUR(\"小时\"),\n" +
            "    MINUTE(\"分钟\")")
    private TimeUnit timeUnit;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduction;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    /**
     * 原来的价格
     */
    @ApiModelProperty(value = "原来的价格")
    private BigDecimal oriPrice;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 预览图片base64
     */
    @ApiModelProperty(value = "产品图片")
    private String preImg;


    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private OrderStatus status;

    /**
     * 预约人姓名
     */
    @ApiModelProperty(value = "预约人姓名")
    private String contacts;
    /**
     * 联系手机号
     */
    @ApiModelProperty(value = "联系手机号")
    private String phone;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 人员姓名
     */
    @ApiModelProperty(value = "人员姓名")
    private String personName;

    @ApiModelProperty(value = "产品主图")
    private String mainImg;
}
