package com.wxmblog.reservation.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxmblog.reservation.dao.UserToPersonDao;
import com.wxmblog.reservation.entity.UserToPersonEntity;
import com.wxmblog.reservation.service.UserToPersonService;


@Service("userToPersonService")
public class UserToPersonServiceImpl extends ServiceImpl<UserToPersonDao, UserToPersonEntity> implements UserToPersonService {

}
