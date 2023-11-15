package com.wxmblog.reservation.controller.admin;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.request.fruser.admin.FrUserPageRequest;
import com.wxmblog.reservation.common.rest.request.fruser.admin.UserToPersonRequest;
import com.wxmblog.reservation.common.rest.request.person.admin.EditPersonRequest;
import com.wxmblog.reservation.common.rest.response.fruser.admin.FrUserPageResponse;
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
@RequestMapping("reservation/admin/fruser")
@Api(tags = "后台-用户管理")
public class FrUserAdminController {

    @Autowired
    private FrUserService frUserService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("用户列表")
    @ApiOperationSort(value = 1)
    @GetMapping("/page")
    public R<PageResult<FrUserPageResponse>> page(FrUserPageRequest request,
                                                  @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return R.ok(frUserService.page(request, pageIndex, pageSize));
    }

    @ApiOperation("绑定人员")
    @ApiOperationSort(value = 2)
    @PostMapping("/bind")
    public R<Void> bindPerson(@RequestBody UserToPersonRequest request) {
        frUserService.bindPerson(request);
        return R.ok();
    }

    @ApiOperation("解除绑定人员")
    @ApiOperationSort(value = 3)
    @PostMapping("/unbind")
    public R<Void> unbind(@RequestBody UserToPersonRequest request) {
        frUserService.unbind(request);
        return R.ok();
    }


}
