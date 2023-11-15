package com.wxmblog.reservation.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxmblog.base.common.entity.BaseEntity;
import com.wxmblog.base.common.enums.FrUserStatusEnum;
import lombok.Data;

import java.util.Date;


/**
 * 前台用户
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-04 11:27:04
 */
@Data
@TableName(value = "fr_user", autoResultMap = true)
public class FrUserEntity extends BaseEntity {

    /**
     * 登陆凭证
     */
    @TableField("open_id")
    private String openId;
    /**
     * 用户状态 启用/停用/注销
     */
    @TableField("status")
    private FrUserStatusEnum status;
    /**
     * 头像
     */
    @TableField("head_portrait")
    private String headPortrait;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 会话密钥
     */
    @TableField("session_key")
    private String sessionKey;
    /**
     * 用户在开放平台的唯一标识符
     */
    @TableField("union_id")
    private String unionId;
    /**
     * 最近时间
     */
    @TableField("lately_time")
    private Date latelyTime;

}
