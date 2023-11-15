package com.wxmblog.reservation.common.rest.response.fruser.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-08-17 17:27
 **/

@Data
public class FrUserPageResponse {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headPortrait;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "关联人员姓名")
    private String personName;

    @ApiModelProperty(value = "为true 则已经绑定了 需要展示取消绑定按钮")
    private Boolean isBind;
}
