package com.wxmblog.reservation.controller;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.request.order.front.OrderSubmitRequest;
import com.wxmblog.reservation.common.rest.response.order.front.HistoryOrderResponse;
import com.wxmblog.reservation.common.rest.response.order.front.OrderDetailResponse;
import com.wxmblog.reservation.service.ProductOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 产品订单
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-13 11:14:35
 */
@RestController
@RequestMapping("reservation/productorder")
@Api(tags = "前台-订单")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;


    @ApiOperation("提交预约订单")
    @ApiOperationSort(value = 1)
    @PostMapping("/add")
    public R<Void> addOrder(@RequestBody OrderSubmitRequest request) {

        productOrderService.addOrder(request);
        return R.ok();

    }

    @ApiOperation("用户信息快速补充")
    @ApiOperationSort(value = 2)
    @GetMapping("/historyOrder")
    public R<HistoryOrderResponse> historyOrder() {

        return R.ok(productOrderService.historyOrder());

    }

    @ApiOperation("用户订单详情")
    @ApiOperationSort(value = 3)
    @GetMapping("/detail/{id}")
    public R<OrderDetailResponse> detail(@PathVariable Integer id) {

        return R.ok(productOrderService.detail(id));

    }

    @ApiOperation("用户取消订单")
    @ApiOperationSort(value = 4)
    @GetMapping("/cancel/{id}")
    public R<Void> cancel(@PathVariable Integer id) {

        productOrderService.cancel(id);
        return R.ok();

    }
}
