package com.wxmblog.reservation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wxmblog.base.common.entity.BaseEntity;
import lombok.Data;


/**
 * 备注
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-06-20 15:24:40
 */
@Data
@TableName(value = "category", autoResultMap = true)
public class CategoryEntity extends BaseEntity {

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    @TableField("sort")
    private Integer sort;

}
