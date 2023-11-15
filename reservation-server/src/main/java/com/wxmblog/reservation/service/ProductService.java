package com.wxmblog.reservation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.reservation.common.rest.request.product.admin.EditProductRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductAdminRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductPersonRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductStatusRequest;
import com.wxmblog.reservation.common.rest.request.product.front.ProductRequest;
import com.wxmblog.reservation.common.rest.response.product.admin.ProductResponse;
import com.wxmblog.reservation.common.rest.response.product.front.SummaryResponse;
import com.wxmblog.reservation.entity.ProductEntity;
import org.springframework.scheduling.annotation.Async;


/**
 * 产品
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-06 10:59:54
 */
public interface ProductService extends IService<ProductEntity> {

    void editProduct(EditProductRequest request);

    Long countByCategoryId(Integer categoryId);

    PageResult<ProductResponse> page(ProductAdminRequest request, Integer pageIndex, Integer pageSize);

    ProductResponse info(Integer id);

    void delete(Integer id);

    SummaryResponse summary();

    PageResult<ProductResponse> homePage(ProductRequest request, Integer pageIndex, Integer pageSize);

    ProductResponse frontInfo(Integer id);

    void addPerson(ProductPersonRequest request);

    void updateStatus(ProductStatusRequest request);
}

