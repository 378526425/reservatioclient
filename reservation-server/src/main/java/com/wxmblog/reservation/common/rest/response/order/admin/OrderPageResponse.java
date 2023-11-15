package com.wxmblog.reservation.common.rest.response.order.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxmblog.reservation.common.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-14 17:23
 **/
@Data
public class OrderPageResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

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


}
