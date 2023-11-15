package com.wxmblog.reservation.common.rest.request.product.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wxmblog.base.common.annotation.ForeignTable;
import com.wxmblog.base.file.annotation.FileListSave;
import com.wxmblog.base.file.annotation.FileSave;
import com.wxmblog.base.file.annotation.FileSaveService;
import com.wxmblog.reservation.common.enums.TimeUnit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: fast
 * @description:
 * @author: Mr.Wang
 * @create: 2023-06-20 17:07
 **/

@Data
public class EditProductRequest {

    @ApiModelProperty(value = "id 无添加 有修改")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @NotBlank(message = "请输入标题")
    private String title;

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类")
    @NotNull(message = "请输入分类")
    @ForeignTable(value = "category", message = "分类不存在")
    private Integer categoryId;


    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 预览图片
     */
    @ApiModelProperty(value = "预览图片")
    @FileSave
    @NotBlank(message = "请选择预览图片")
    private String preImg;

    /**
     * 时长
     */
    @ApiModelProperty(value = "服务时长")
    private Integer duration;
    /**
     * 时长单位
     */
    @ApiModelProperty(value = "时长单位 DAY(\"天\"),\n" +
            "HOUR(\"小时\"),\n" +
            "MINUTE(\"分钟\")")
    private TimeUnit timeUnit;

    /**
     * 文件地址
     */
    @ApiModelProperty(value = "详情图片")
    @FileListSave
    @NotEmpty
    private List<String> info;

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
    @ApiModelProperty(value = "原价格")
    private BigDecimal oriPrice;

    @ApiModelProperty(value = "主图")
    @FileSave
    @NotBlank(message = "请选择主图")
    private String mainImg;
}
