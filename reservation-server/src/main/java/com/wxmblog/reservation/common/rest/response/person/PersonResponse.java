package com.wxmblog.reservation.common.rest.response.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-11 14:18
 **/

@Data
public class PersonResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 预览图片
     */
    @ApiModelProperty(value = "预览图片")
    private String preImg;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "手机号码")
    private String phone;
}
