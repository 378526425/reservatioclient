package com.wxmblog.reservation.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxmblog.base.common.exception.JrsfException;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.reservation.common.exception.ExceptionEnum;
import com.wxmblog.reservation.common.rest.request.category.EditCategoryRequest;
import com.wxmblog.reservation.common.rest.response.category.CategoryResponse;
import com.wxmblog.reservation.dao.CategoryDao;
import com.wxmblog.reservation.entity.CategoryEntity;
import com.wxmblog.reservation.service.CategoryService;
import com.wxmblog.reservation.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    ProductService productService;

    @Transactional
    @Override
    public void editCategory(EditCategoryRequest request) {
        if (request.getId() == null) {
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(request, categoryEntity);
            this.baseMapper.insert(categoryEntity);
        } else {
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(request, categoryEntity);
            Wrapper<CategoryEntity> updateWrapper = new QueryWrapper<CategoryEntity>().lambda()
                    .eq(CategoryEntity::getId, request.getId());
            this.baseMapper.update(categoryEntity, updateWrapper);
        }
    }

    @Override
    public PageResult<CategoryResponse> page(Integer pageIndex, Integer pageSize) {
        Page<CategoryResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.baseMapper.getPage();
        PageResult<CategoryResponse> result = new PageResult<>(page);
        return result;
    }

    @Override
    public void deleteById(Integer id) {

        Long productCount = this.productService.countByCategoryId(id);
        if (productCount > 0) {
            throw new JrsfException(ExceptionEnum.PRODUCT_EXIST_EXCEPTION);
        }
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<CategoryResponse> listAll() {
        return this.baseMapper.getPage();
    }
}













