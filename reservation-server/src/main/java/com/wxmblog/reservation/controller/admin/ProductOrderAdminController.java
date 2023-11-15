package com.wxmblog.reservation.controller.admin;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.request.order.admin.OrderAdminRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductPersonRequest;
import com.wxmblog.reservation.common.rest.response.order.admin.OrderPageResponse;
import com.wxmblog.reservation.service.ProductOrderService;
import io.swagger.annotations.*;
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
@RequestMapping("reservation/admin/productorder")
@Api(tags = "后台-订单管理")
public class ProductOrderAdminController {

    @Autowired
    private ProductOrderService productOrderService;


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("订单列表")
    @ApiOperationSort(value = 1)
    @GetMapping("/page")
    public R<PageResult<OrderPageResponse>> page(OrderAdminRequest request,
                                                 @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return R.ok(productOrderService.page(request, pageIndex, pageSize));
    }

    @ApiOperation("修改订单为服务状态")
    @ApiOperationSort(value = 2)
    @PostMapping("/service/order/{id}")
    public R<Void> serviceOrder(@PathVariable Integer id) {
        productOrderService.serviceOrder(id);
        return R.ok();
    }
}
