package com.wxmblog.reservation.common.rest.response.fruser;

import com.wxmblog.base.file.annotation.FileSave;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-08-18 15:59
 **/

@Data
public class EditUserRequest {

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @FileSave(table = "fr_user", field = "head_portrait", tokenId = true)
    private String headPortrait;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "请输入昵称")
    private String nickName;
}
