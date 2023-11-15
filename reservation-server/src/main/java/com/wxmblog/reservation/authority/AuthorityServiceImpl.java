package com.wxmblog.reservation.authority;

import com.wxmblog.base.auth.authority.service.IAuthorityServiceImpl;
import com.wxmblog.base.auth.authority.service.WxAppletService;
import com.wxmblog.base.auth.common.rest.request.LoginRequest;
import com.wxmblog.base.common.entity.LoginUser;
import com.wxmblog.base.common.enums.BaseUserExceptionEnum;
import com.wxmblog.base.common.enums.FrUserStatusEnum;
import com.wxmblog.base.common.exception.JrsfException;
import com.wxmblog.base.common.service.BaseCommonService;
import com.wxmblog.base.common.utils.DateUtils;
import com.wxmblog.base.file.service.MsfFileService;
import com.wxmblog.reservation.common.rest.request.fruser.AppletRegisterRequest;
import com.wxmblog.reservation.common.rest.response.fruser.LoginResponse;
import com.wxmblog.reservation.entity.FrUserEntity;
import com.wxmblog.reservation.service.FrUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-06-16 18:05
 **/
@Service
public class AuthorityServiceImpl extends IAuthorityServiceImpl<LoginRequest, AppletRegisterRequest> {

    @Autowired
    MsfFileService fileService;

    @Autowired
    FrUserService frUserService;

    @Autowired
    WxAppletService wxAppletService;

    @Autowired
    BaseCommonService baseCommonService;

    /*
     * @Author wanglei
     * @Description  微信小程序登录
     * @Date 16:49 2022/12/5
     * @Param
     * @return
     **/
    @Override
    public LoginUser login(LoginRequest loginRequest) {

        LoginUser loginUser = new LoginUser();
        FrUserEntity frUserEntity = this.frUserService.getFrUserByOpenId(loginRequest.getOpenId());
        if (frUserEntity == null) {
            throw new JrsfException(BaseUserExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }

        if (FrUserStatusEnum.DISABLE.equals(frUserEntity.getStatus())) {
            throw new JrsfException(BaseUserExceptionEnum.USER_STATUS_ERROR_EXCEPTION);
        }

        loginUser.setId(frUserEntity.getId());
        LoginResponse loginResponse = new LoginResponse();
        BeanUtils.copyProperties(frUserEntity, loginResponse);
        loginUser.setInfo(loginResponse);

        return loginUser;
    }

    @Override
    @Transactional
    public void wxAppletRegister(AppletRegisterRequest request) {

        FrUserEntity frUserEntity = new FrUserEntity();
        BeanUtils.copyProperties(request, frUserEntity);

        if (this.frUserService.countByOpenId(request.getOpenId()) > 0l) {
            throw new JrsfException(BaseUserExceptionEnum.USER_EXIST_EXCEPTION);
        }

        frUserEntity.setStatus(FrUserStatusEnum.ENABLE);

        this.frUserService.save(frUserEntity);

    }
}
