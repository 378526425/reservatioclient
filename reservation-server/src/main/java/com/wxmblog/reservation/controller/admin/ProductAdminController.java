package com.wxmblog.reservation.controller.admin;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.request.product.admin.EditProductRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductAdminRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductPersonRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductStatusRequest;
import com.wxmblog.reservation.common.rest.response.product.admin.ProductResponse;
import com.wxmblog.reservation.service.ProductService;
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
@RequestMapping("reservation/admin/product")
@Api(tags = "后台-产品管理")
public class ProductAdminController {
    @Autowired
    private ProductService productService;

    @ApiOperation("添加编辑")
    @ApiOperationSort(value = 1)
    @PostMapping("/edit")
    public R<Void> editProduct(@RequestBody EditProductRequest request) {
        productService.editProduct(request);
        return R.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("产品列表")
    @ApiOperationSort(value = 2)
    @GetMapping("/page")
    public R<PageResult<ProductResponse>> page(ProductAdminRequest request,
                                               @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return R.ok(productService.page(request, pageIndex, pageSize));
    }

    @ApiOperation("详情")
    @ApiOperationSort(value = 3)
    @GetMapping("/info/{id}")
    public R<ProductResponse> info(@PathVariable Integer id) {
        return R.ok(productService.info(id));
    }

    @ApiOperation("删除产品")
    @ApiOperationSort(value = 4)
    @DeleteMapping("/delete/{id}")
    public R<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return R.ok();
    }

    @ApiOperation("添加人员")
    @ApiOperationSort(value = 5)
    @PostMapping("/addPerson")
    public R<Void> addPerson(@RequestBody ProductPersonRequest request) {
        productService.addPerson(request);
        return R.ok();
    }

    @ApiOperation("修改产品状态")
    @ApiOperationSort(value = 6)
    @PutMapping("/status")
    public R<Void> updateStatus(@RequestBody ProductStatusRequest request) {
        productService.updateStatus(request);
        return R.ok();
    }

}
