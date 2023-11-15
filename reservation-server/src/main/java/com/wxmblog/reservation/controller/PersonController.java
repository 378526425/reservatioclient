package com.wxmblog.reservation.controller;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.annotation.AuthIgnore;
import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.response.person.PersonResponse;
import com.wxmblog.reservation.service.PersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 人员
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-10 17:24:35
 */
@RestController
@RequestMapping("reservation/person")
@Api(tags = "前台-人员管理")
public class PersonController {

    @Autowired
    private PersonService personService;


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("根据产品查询人员列表")
    @ApiOperationSort(value = 1)
    @GetMapping("/page/{productId}")
    @AuthIgnore
    public R<PageResult<PersonResponse>> page(@PathVariable Integer productId,
                                              @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return R.ok(personService.page(productId, pageIndex, pageSize));
    }

}
