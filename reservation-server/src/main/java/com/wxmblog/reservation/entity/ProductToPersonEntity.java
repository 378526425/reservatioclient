package com.wxmblog.reservation.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxmblog.base.common.entity.BaseEntity;
import lombok.Data;


/**
 * 产品人员关联表
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-12 11:18:35
 */
@Data
@TableName(value = "product_to_person", autoResultMap = true)
public class ProductToPersonEntity extends BaseEntity {


    /**
     * 人员id
     */
    @TableField("person_id")
    private Integer personId;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

}
