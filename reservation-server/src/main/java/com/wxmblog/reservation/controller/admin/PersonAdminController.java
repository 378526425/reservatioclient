package com.wxmblog.reservation.controller.admin;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.request.category.EditCategoryRequest;
import com.wxmblog.reservation.common.rest.request.person.admin.EditPersonRequest;
import com.wxmblog.reservation.common.rest.request.person.admin.PersonRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductAdminRequest;
import com.wxmblog.reservation.common.rest.response.person.PersonResponse;
import com.wxmblog.reservation.common.rest.response.product.admin.ProductResponse;
import com.wxmblog.reservation.service.PersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 人员
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-10 17:24:35
 */
@RestController
@RequestMapping("reservation/admin/person")
@Api(tags = "后台-人员管理")
public class PersonAdminController {

    @Autowired
    private PersonService personService;

    @ApiOperation("添加编辑")
    @ApiOperationSort(value = 1)
    @PostMapping("/edit")
    public R<Void> editPerson(@RequestBody EditPersonRequest request) {
        personService.editPerson(request);
        return R.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("人员列表")
    @ApiOperationSort(value = 2)
    @GetMapping("/page")
    public R<PageResult<PersonResponse>> page(PersonRequest request,
                                              @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return R.ok(personService.page(request, pageIndex, pageSize));
    }


    @ApiOperation("详情")
    @ApiOperationSort(value = 3)
    @GetMapping("/info/{id}")
    public R<PersonResponse> info(@PathVariable Integer id) {
        return R.ok(personService.info(id));
    }

    @ApiOperation("删除人员")
    @ApiOperationSort(value = 4)
    @DeleteMapping("/delete/{id}")
    public R<Void> delete(@PathVariable Integer id) {
        personService.delete(id);
        return R.ok();
    }

    @ApiOperation("查询所有人员列表")
    @ApiOperationSort(value = 5)
    @GetMapping("/all")
    public R<List<PersonResponse>> all() {
        return R.ok(personService.getAllPerson());
    }
}
