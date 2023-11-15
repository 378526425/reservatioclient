package com.wxmblog.reservation.controller;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.annotation.AuthIgnore;
import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.request.product.front.ProductRequest;
import com.wxmblog.reservation.common.rest.response.category.CategoryResponse;
import com.wxmblog.reservation.common.rest.response.product.admin.ProductResponse;
import com.wxmblog.reservation.common.rest.response.product.front.SummaryResponse;
import com.wxmblog.reservation.service.CategoryService;
import com.wxmblog.reservation.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 产品
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-06 10:59:54
 */
@RestController
@RequestMapping("reservation/product")
@Api(tags = "前台-产品管理")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("预览信息")
    @ApiOperationSort(value = 1)
    @GetMapping("/summary")
    @AuthIgnore
    public R<SummaryResponse> summary() {

        return R.ok(productService.summary());
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("优惠项目")
    @ApiOperationSort(value = 2)
    @GetMapping("/page")
    @AuthIgnore
    public R<PageResult<ProductResponse>> page(ProductRequest request,@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return R.ok(productService.homePage(request,pageIndex, pageSize));
    }

    @ApiOperation("前台详情")
    @ApiOperationSort(value = 3)
    @GetMapping("/info/{id}")
    @AuthIgnore
    public R<ProductResponse> info(@PathVariable Integer id) {
        return R.ok(productService.frontInfo(id));
    }

    @ApiOperation("查询所有类别列表")
    @ApiOperationSort(value = 4)
    @GetMapping("/allCategory")
    @AuthIgnore
    public R<List<CategoryResponse>> allCategory() {
           return R.ok(categoryService.listAll());
    }

}
