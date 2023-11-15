package com.wxmblog.reservation.dao;

import com.wxmblog.reservation.common.rest.request.order.admin.OrderAdminRequest;
import com.wxmblog.reservation.common.rest.response.order.admin.OrderPageResponse;
import com.wxmblog.reservation.entity.ProductOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品订单
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-13 11:14:35
 */
@Mapper
public interface ProductOrderDao extends BaseMapper<ProductOrderEntity> {

    List<OrderPageResponse> getPage(OrderAdminRequest request);

    Long countImg(String url);
}
