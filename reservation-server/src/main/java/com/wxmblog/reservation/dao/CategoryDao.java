package com.wxmblog.reservation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxmblog.reservation.common.rest.response.category.CategoryResponse;
import com.wxmblog.reservation.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 备注
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-06-20 15:24:40
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

    List<CategoryResponse> getPage();
}
