package com.wxmblog.reservation.dao;

import com.wxmblog.reservation.entity.UserToPersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关联人员
 * 
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-08-18 15:28:26
 */
@Mapper
public interface UserToPersonDao extends BaseMapper<UserToPersonEntity> {
	
}
