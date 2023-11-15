package com.wxmblog.reservation.controller.admin;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.request.category.EditCategoryRequest;
import com.wxmblog.reservation.common.rest.response.category.CategoryResponse;
import com.wxmblog.reservation.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 备注
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-06-20 15:24:40
 */
@RestController
@RequestMapping("reservation/admin/category")
@Api(tags = "后台-分类管理")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;


    @ApiOperation("添加编辑")
    @ApiOperationSort(value = 1)
    @PostMapping("/edit")
    public R<Void> editCategory(@RequestBody EditCategoryRequest request) {
        categoryService.editCategory(request);
        return R.ok();
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("分类列表")
    @ApiOperationSort(value = 2)
    @GetMapping("/page")
    public R<PageResult<CategoryResponse>> page(
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return R.ok(categoryService.page(pageIndex, pageSize));
    }

    @ApiOperation("删除分类")
    @ApiOperationSort(value = 3)
    @DeleteMapping("/delete/{id}")
    public R<Void> editCategory(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return R.ok();
    }
}
