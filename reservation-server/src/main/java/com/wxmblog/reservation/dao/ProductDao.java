package com.wxmblog.reservation.dao;

import com.wxmblog.reservation.common.rest.request.product.admin.ProductAdminRequest;
import com.wxmblog.reservation.common.rest.request.product.front.ProductRequest;
import com.wxmblog.reservation.common.rest.response.product.admin.ProductResponse;
import com.wxmblog.reservation.entity.ProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-06 10:59:54
 */
@Mapper
public interface ProductDao extends BaseMapper<ProductEntity> {

    List<ProductResponse> getPage(ProductAdminRequest request);

    ProductResponse info(Integer id);

    List<ProductResponse> homePage(ProductRequest request);

}
