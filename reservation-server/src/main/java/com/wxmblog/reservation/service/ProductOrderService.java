package com.wxmblog.reservation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.reservation.common.rest.request.order.admin.OrderAdminRequest;
import com.wxmblog.reservation.common.rest.request.order.front.OrderSubmitRequest;
import com.wxmblog.reservation.common.rest.response.order.admin.OrderPageResponse;
import com.wxmblog.reservation.common.rest.response.order.front.HistoryOrderResponse;
import com.wxmblog.reservation.common.rest.response.order.front.OrderDetailResponse;
import com.wxmblog.reservation.entity.ProductOrderEntity;


/**
 * 产品订单
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-13 11:14:35
 */
public interface ProductOrderService extends IService<ProductOrderEntity> {

    void addOrder(OrderSubmitRequest request);

    PageResult<OrderPageResponse> page(OrderAdminRequest request, Integer pageIndex, Integer pageSize);

    void serviceOrder(Integer id);

    HistoryOrderResponse historyOrder();

    OrderDetailResponse detail(Integer id);

    void cancel(Integer id);

    Long imgCountByUrl(String url);
}

