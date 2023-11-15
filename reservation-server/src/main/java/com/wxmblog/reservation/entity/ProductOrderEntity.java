package com.wxmblog.reservation.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxmblog.base.common.entity.BaseEntity;
import com.wxmblog.reservation.common.enums.OrderStatus;
import com.wxmblog.reservation.common.enums.TimeUnit;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品订单
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-13 11:14:35
 */
@Data
@TableName(value = "product_order", autoResultMap = true)
public class ProductOrderEntity extends BaseEntity {


    /**
     * 标题
     */
    @TableField("title")
    private String title;
    /**
     * 预览图片base64
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
     * 简介
     */
    @TableField("introduction")
    private String introduction;
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

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 状态
     */
    @TableField("status")
    private OrderStatus status;
    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 预约人姓名
     */
    @TableField("contacts")
    private String contacts;
    /**
     * 联系手机号
     */
    @TableField("phone")
    private String phone;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 人员id
     */
    @TableField("person_id")
    private Integer personId;
    /**
     * 人员姓名
     */
    @TableField("person_name")
    private String personName;
    /**
     * 人员预览图片 base64
     */
    @TableField("person_pre_img")
    private String personPreImg;

    @TableField("main_img")
    private String mainImg;
}
