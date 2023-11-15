package com.wxmblog.reservation.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxmblog.base.common.entity.BaseEntity;
import com.wxmblog.base.common.handler.BaseListTypeHandler;
import com.wxmblog.base.common.handler.BaseStringListTypeHandler;
import com.wxmblog.reservation.common.enums.ProductStatus;
import com.wxmblog.reservation.common.enums.TimeUnit;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-06 10:59:54
 */
@Data
@TableName(value = "product", autoResultMap = true)
public class ProductEntity extends BaseEntity {


    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 预览图片
     */
    @TableField("pre_img")
    private String preImg;
    /**
     * 时长
     */
    @TableField("duration")
    private Integer duration;
    /**
     * 时长单位
     */
    @TableField("time_unit")
    private TimeUnit timeUnit;
    /**
     * 文件地址
     */
    @TableField(value = "info", typeHandler = BaseStringListTypeHandler.class)
    private List<String> info;
    /**
     * 简介
     */
    @TableField("introduction")
    private String introduction;
    /**
     * 分类
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     *
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;
    /**
     * 原来的价格
     */
    @TableField("ori_price")
    private BigDecimal oriPrice;

    @TableField("main_img")
    private String mainImg;

    @TableField("status")
    private ProductStatus status;
}
