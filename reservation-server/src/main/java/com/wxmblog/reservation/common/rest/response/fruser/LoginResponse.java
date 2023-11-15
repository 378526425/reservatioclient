package com.wxmblog.reservation.common.rest.response.fruser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxmblog.base.common.enums.GenderEnum;
import lombok.Data;

import java.util.Date;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-09-29 16:46
 **/

@Data
public class LoginResponse {

    private Integer id;

    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 昵称
     */
    private String nickName;

}
