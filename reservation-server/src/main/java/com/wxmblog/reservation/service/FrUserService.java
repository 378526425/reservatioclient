package com.wxmblog.reservation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.reservation.common.rest.request.fruser.admin.FrUserPageRequest;
import com.wxmblog.reservation.common.rest.request.fruser.admin.UserToPersonRequest;
import com.wxmblog.reservation.common.rest.request.order.front.UserOrderRequest;
import com.wxmblog.reservation.common.rest.response.fruser.EditUserRequest;
import com.wxmblog.reservation.common.rest.response.fruser.PersonalResponse;
import com.wxmblog.reservation.common.rest.response.fruser.admin.FrUserPageResponse;
import com.wxmblog.reservation.common.rest.response.order.front.UserOrderPageResponse;
import com.wxmblog.reservation.entity.FrUserEntity;


/**
 * 前台用户
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-04 11:27:04
 */
public interface FrUserService extends IService<FrUserEntity> {

    FrUserEntity getFrUserByOpenId(String openId);

    Long countByOpenId(String openId);

    PersonalResponse personal();

    PageResult<UserOrderPageResponse> orderPage(UserOrderRequest request, Integer pageIndex, Integer pageSize);

    PageResult<FrUserPageResponse> page(FrUserPageRequest request, Integer pageIndex, Integer pageSize);

    void bindPerson(UserToPersonRequest request);

    void unbind(UserToPersonRequest request);

    void editInfo(EditUserRequest request);


}

