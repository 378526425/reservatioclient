package com.wxmblog.reservation.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxmblog.base.common.entity.BaseEntity;
import lombok.Data;


/**
 * 用户关联人员
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-08-18 15:28:26
 */
@Data
@TableName(value = "user_to_person", autoResultMap = true)
public class UserToPersonEntity extends BaseEntity {


                                                                                                            /**
             * 用户id
             */
            @TableField("user_id")
            private Integer userId;
                                /**
             * 人员id
             */
            @TableField("person_id")
            private Integer personId;
            
}
