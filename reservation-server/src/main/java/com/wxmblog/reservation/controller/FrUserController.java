package com.wxmblog.reservation.controller;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.request.order.front.UserOrderRequest;
import com.wxmblog.reservation.common.rest.request.person.admin.EditPersonRequest;
import com.wxmblog.reservation.common.rest.response.fruser.EditUserRequest;
import com.wxmblog.reservation.common.rest.response.fruser.PersonalResponse;
import com.wxmblog.reservation.common.rest.response.order.front.UserOrderPageResponse;
import com.wxmblog.reservation.service.FrUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 前台用户
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-04 11:27:04
 */
@RestController
@RequestMapping("reservation/fruser")
@Api(tags = "前台-个人中心")
public class FrUserController {

    @Autowired
    private FrUserService frUserService;


    @ApiOperation("个人中心信息")
    @ApiOperationSort(value = 1)
    @GetMapping("/personal")
    public R<PersonalResponse> personal() {
        return R.ok(frUserService.personal());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("用户订单列表")
    @ApiOperationSort(value = 2)
    @GetMapping("/order/page")
    public R<PageResult<UserOrderPageResponse>> page(UserOrderRequest request,
                                                     @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return R.ok(frUserService.orderPage(request, pageIndex, pageSize));
    }

    @ApiOperation("编辑个人信息")
    @ApiOperationSort(value = 3)
    @PostMapping("/edit")
    public R<Void> editInfo(@RequestBody EditUserRequest request) {
        frUserService.editInfo(request);
        return R.ok();
    }

}
