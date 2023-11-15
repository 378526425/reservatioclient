package com.wxmblog.reservation.common.rest.response.product.admin;

import com.wxmblog.reservation.common.enums.ProductStatus;
import com.wxmblog.reservation.common.enums.TimeUnit;
import com.wxmblog.reservation.common.rest.response.person.PersonResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: fast
 * @description:
 * @author: Mr.Wang
 * @create: 2023-06-25 10:42
 **/

@Data
public class ProductResponse {

    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 分类
     */
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "排序")
    private Integer sort;


    /**
     * 预览图片
     */
    @ApiModelProperty(value = "预览图片")
    private String preImg;
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
     * 文件地址
     */
    @ApiModelProperty(value = "详情")
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
    @ApiModelProperty(value = "原来的价格")
    private BigDecimal oriPrice;

    @ApiModelProperty(value = "人员列表")
    private List<PersonResponse> personList;

    @ApiModelProperty(value = "主图")
    private String mainImg;

    @ApiModelProperty(value = "状态 UP(\"上架\"),\n" +
            "    DOWN(\"下架\")")
    private ProductStatus status;
}
