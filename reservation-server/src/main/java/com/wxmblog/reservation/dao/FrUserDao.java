package com.wxmblog.reservation.dao;

import com.wxmblog.reservation.common.rest.request.fruser.admin.FrUserPageRequest;
import com.wxmblog.reservation.common.rest.request.order.front.UserOrderRequest;
import com.wxmblog.reservation.common.rest.response.fruser.PersonalResponse;
import com.wxmblog.reservation.common.rest.response.fruser.admin.FrUserPageResponse;
import com.wxmblog.reservation.common.rest.response.order.front.UserOrderPageResponse;
import com.wxmblog.reservation.entity.FrUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 前台用户
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-04 11:27:04
 */
@Mapper
public interface FrUserDao extends BaseMapper<FrUserEntity> {

    PersonalResponse getPersonalResponse(Integer ownerId);

    List<UserOrderPageResponse> orderPage(UserOrderRequest request);

    List<FrUserPageResponse> frUserPage(FrUserPageRequest request);
}
