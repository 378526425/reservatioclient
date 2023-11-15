package com.wxmblog.reservation.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxmblog.base.common.entity.BaseEntity;
import lombok.Data;


/**
 * 人员
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-10 17:24:35
 */
@Data
@TableName(value = "person", autoResultMap = true)
public class PersonEntity extends BaseEntity {

    /**
     * 姓名
     */
    @TableField("name")
    private String name;
    /**
     * 预览图片
     */
    @TableField("pre_img")
    private String preImg;
    /**
     * 简介
     */
    @TableField("introduction")
    private String introduction;

    @TableField("phone")
    private String phone;

}
