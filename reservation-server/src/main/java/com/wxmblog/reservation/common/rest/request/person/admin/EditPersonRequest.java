package com.wxmblog.reservation.common.rest.request.person.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wxmblog.base.file.annotation.FileSave;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-07-11 14:05
 **/

@Data
public class EditPersonRequest {


    @ApiModelProperty(value = "没有添加 有则修改")
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "请输入姓名")
    private String name;

    /**
     * 预览图片
     */
    @ApiModelProperty(value = "预览图片")
    @FileSave
    private String preImg;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "手机号码")
    private String phone;


}
