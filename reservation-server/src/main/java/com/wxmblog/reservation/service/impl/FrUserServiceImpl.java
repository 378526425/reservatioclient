package com.wxmblog.reservation.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.utils.TokenUtils;
import com.wxmblog.reservation.common.rest.request.fruser.admin.FrUserPageRequest;
import com.wxmblog.reservation.common.rest.request.fruser.admin.UserToPersonRequest;
import com.wxmblog.reservation.common.rest.request.order.front.UserOrderRequest;
import com.wxmblog.reservation.common.rest.response.fruser.EditUserRequest;
import com.wxmblog.reservation.common.rest.response.fruser.PersonalResponse;
import com.wxmblog.reservation.common.rest.response.fruser.admin.FrUserPageResponse;
import com.wxmblog.reservation.common.rest.response.order.front.UserOrderPageResponse;
import com.wxmblog.reservation.dao.FrUserDao;
import com.wxmblog.reservation.entity.FrUserEntity;
import com.wxmblog.reservation.entity.UserToPersonEntity;
import com.wxmblog.reservation.service.FrUserService;
import com.wxmblog.reservation.service.UserToPersonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("frUserService")
public class FrUserServiceImpl extends ServiceImpl<FrUserDao, FrUserEntity> implements FrUserService {

    @Autowired
    UserToPersonService userToPersonService;


    @Override
    public FrUserEntity getFrUserByOpenId(String openId) {
        Wrapper<FrUserEntity> frUserEntityWrapper = new QueryWrapper<FrUserEntity>().lambda().eq(FrUserEntity::getOpenId, openId);
        return getOne(frUserEntityWrapper);
    }

    @Override
    public Long countByOpenId(String openId) {
        Wrapper<FrUserEntity> frUserEntityWrapper = new QueryWrapper<FrUserEntity>().lambda().eq(FrUserEntity::getOpenId, openId);
        return count(frUserEntityWrapper);
    }

    @Override
    public PersonalResponse personal() {

        PersonalResponse personalResponse = this.baseMapper.getPersonalResponse(TokenUtils.getOwnerId());

        FrUserEntity frUserEntity = this.baseMapper.selectById(TokenUtils.getOwnerId());
        if (frUserEntity != null) {
            BeanUtils.copyProperties(frUserEntity, personalResponse);
        }

        return personalResponse;
    }

    @Override
    public PageResult<UserOrderPageResponse> orderPage(UserOrderRequest request, Integer pageIndex, Integer pageSize) {

        request.setUserId(TokenUtils.getOwnerId());

        Page<UserOrderPageResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.baseMapper.orderPage(request);
        PageResult<UserOrderPageResponse> result = new PageResult<>(page);
        return result;

    }

    @Override
    public PageResult<FrUserPageResponse> page(FrUserPageRequest request, Integer pageIndex, Integer pageSize) {
        if (StringUtils.isNotBlank(request.getNickName())) {
            request.setNickName("%" + request.getNickName() + "%");
        }

        if (StringUtils.isNotBlank(request.getPersonName())) {
            request.setPersonName("%" + request.getPersonName() + "%");
        }

        Page<FrUserPageResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.baseMapper.frUserPage(request);
        PageResult<FrUserPageResponse> result = new PageResult<>(page);
        return result;
    }

    @Transactional
    @Override
    public void bindPerson(UserToPersonRequest request) {

        Wrapper<UserToPersonEntity> wrapper = new QueryWrapper<UserToPersonEntity>().lambda()
                .eq(UserToPersonEntity::getUserId, request.getUserId())
                .eq(UserToPersonEntity::getPersonId, request.getPersonId());
        Long count = userToPersonService.getBaseMapper().selectCount(wrapper);
        if (count == 0) {

            Wrapper<UserToPersonEntity> wrapperDelete = new QueryWrapper<UserToPersonEntity>().lambda()
                    .eq(UserToPersonEntity::getPersonId, request.getPersonId());
            userToPersonService.getBaseMapper().delete(wrapperDelete);
            UserToPersonEntity userToPersonEntity = new UserToPersonEntity();
            userToPersonEntity.setUserId(request.getUserId());
            userToPersonEntity.setPersonId(request.getPersonId());
            userToPersonService.getBaseMapper().insert(userToPersonEntity);
        }

    }

    @Override
    @Transactional
    public void unbind(UserToPersonRequest request) {
        Wrapper<UserToPersonEntity> wrapper = new QueryWrapper<UserToPersonEntity>().lambda()
                .eq(UserToPersonEntity::getUserId, request.getUserId())
                .eq(UserToPersonEntity::getPersonId, request.getPersonId());
        userToPersonService.getBaseMapper().delete(wrapper);
    }

    @Transactional
    @Override
    public void editInfo(EditUserRequest request) {

        FrUserEntity frUserEntity = this.baseMapper.selectById(TokenUtils.getOwnerId());
        if (frUserEntity != null) {
            BeanUtils.copyProperties(request, frUserEntity);
            this.baseMapper.updateById(frUserEntity);
        }

    }



}
