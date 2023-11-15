package com.wxmblog.reservation.dao;

import com.wxmblog.reservation.entity.ProductToPersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品人员关联表
 * 
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-12 11:18:35
 */
@Mapper
public interface ProductToPersonDao extends BaseMapper<ProductToPersonEntity> {
	
}
