package com.wxmblog.reservation.common.rest.request.fruser;

import com.wxmblog.base.auth.common.rest.request.RegisterRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-12-07 15:48
 **/

@Data
public class AppletRegisterRequest extends RegisterRequest {

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headPortrait;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @Length(min = 1, max = 8,message = "昵称为1-8位字符")
    @NotBlank(message = "请输入昵称")
    private String nickName;

}
