package com.wxmblog.reservation.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.reservation.common.rest.request.category.EditCategoryRequest;
import com.wxmblog.reservation.common.rest.response.category.CategoryResponse;
import com.wxmblog.reservation.entity.CategoryEntity;

import java.util.List;


/**
 * 备注
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-06-20 15:24:40
 */
public interface CategoryService extends IService<CategoryEntity> {

    void editCategory(EditCategoryRequest request);

    PageResult<CategoryResponse> page(Integer pageIndex, Integer pageSize);

    void deleteById(Integer id);

    List<CategoryResponse> listAll();
}

