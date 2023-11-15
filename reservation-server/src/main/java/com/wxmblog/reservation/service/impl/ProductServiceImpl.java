package com.wxmblog.reservation.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxmblog.base.auth.service.MsfConfigService;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.utils.SpringUtils;
import com.wxmblog.base.file.service.MsfFileService;
import com.wxmblog.reservation.common.enums.ConfigEnums;
import com.wxmblog.reservation.common.enums.ProductStatus;
import com.wxmblog.reservation.common.rest.request.product.admin.EditProductRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductAdminRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductPersonRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductStatusRequest;
import com.wxmblog.reservation.common.rest.request.product.front.ProductRequest;
import com.wxmblog.reservation.common.rest.response.product.admin.ProductResponse;
import com.wxmblog.reservation.common.rest.response.product.front.SummaryResponse;
import com.wxmblog.reservation.dao.ProductDao;
import com.wxmblog.reservation.entity.PersonEntity;
import com.wxmblog.reservation.entity.ProductEntity;
import com.wxmblog.reservation.entity.ProductOrderEntity;
import com.wxmblog.reservation.entity.ProductToPersonEntity;
import com.wxmblog.reservation.service.PersonService;
import com.wxmblog.reservation.service.ProductOrderService;
import com.wxmblog.reservation.service.ProductService;
import com.wxmblog.reservation.service.ProductToPersonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Autowired
    MsfFileService msfFileService;

    @Autowired
    MsfConfigService msfConfigService;

    @Autowired
    ProductToPersonService productToPersonService;

    @Autowired
    PersonService personService;

    @Transactional
    @Override
    public void editProduct(EditProductRequest request) {

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(request, productEntity);
        if (request.getId() == null) {
            productEntity.setStatus(ProductStatus.DOWN);
            this.baseMapper.insert(productEntity);

            //默认设置全部人员
            Wrapper<PersonEntity> wrapper = new QueryWrapper<PersonEntity>().lambda()
                    .last("order by id asc");
            List<PersonEntity> personEntityList = personService.getBaseMapper().selectList(wrapper);
            AtomicInteger sort = new AtomicInteger(1);
            personEntityList.forEach(model -> {
                ProductToPersonEntity productToPersonEntity = new ProductToPersonEntity();
                productToPersonEntity.setProductId(productEntity.getId());
                productToPersonEntity.setPersonId(model.getId());
                productToPersonEntity.setSort("到店安排".equals(model.getName()) ? 0 : sort.getAndIncrement());
                productToPersonService.save(productToPersonEntity);
            });

        } else {
            Wrapper<ProductEntity> wrapper = new QueryWrapper<ProductEntity>().lambda()
                    .eq(ProductEntity::getId, request.getId());
            ProductEntity oldProduct = this.getById(request.getId());
            deleteOldImg(oldProduct.getMainImg(), request.getMainImg());
            deleteOldImg(oldProduct.getPreImg(), request.getPreImg());
            if (CollectionUtil.isNotEmpty(oldProduct.getInfo())) {
                oldProduct.getInfo().forEach(model -> {
                    if (CollectionUtil.isEmpty(request.getInfo())) {
                        deleteOldImg(model, null);
                    } else {
                        if (!request.getInfo().contains(model)) {
                            deleteOldImg(model, null);
                        }
                    }
                });
            }
            this.baseMapper.update(productEntity, wrapper);
        }

    }

    private void deleteOldImg(String oldImg, String newImg) {

        if (StringUtils.isNotBlank(oldImg) && !oldImg.equals(newImg)) {
            ProductOrderService productOrderService = SpringUtils.getBean(ProductOrderService.class);
            Long mainCount = productOrderService.imgCountByUrl(oldImg);
            if (mainCount == 0) {
                msfFileService.deleteFileByUrl(oldImg);
            }
        }
    }

    @Override
    public Long countByCategoryId(Integer categoryId) {
        Wrapper<ProductEntity> productEntityWrapper = new QueryWrapper<ProductEntity>().lambda()
                .eq(ProductEntity::getCategoryId, categoryId);
        return this.baseMapper.selectCount(productEntityWrapper);
    }

    @Override
    public PageResult<ProductResponse> page(ProductAdminRequest request, Integer pageIndex, Integer pageSize) {

        if (StringUtils.isNotBlank(request.getTitle())) {
            request.setTitle("%" + request.getTitle() + "%");
        }

        Page<ProductResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.baseMapper.getPage(request);
        PageResult<ProductResponse> result = new PageResult<>(page);
        return result;
    }

    @Override
    public ProductResponse info(Integer id) {
        ProductResponse productResponse = this.baseMapper.info(id);

        if (productResponse != null) {
            productResponse.setPersonList(personService.getPersonByProductId(id));
        }
        return productResponse;
    }

    @Transactional
    @Override
    public void delete(Integer id) {

        ProductEntity productEntity = this.getById(id);
        if (productEntity != null) {
            this.removeById(id);
            //查询该产品是否有关联的订单

            ProductOrderService productOrderService = SpringUtils.getBean(ProductOrderService.class);
            Long preCount = productOrderService.imgCountByUrl(productEntity.getPreImg());
            if (preCount == 0) {
                msfFileService.deleteFileByUrl(productEntity.getPreImg());
            }

            Long mainCount = productOrderService.imgCountByUrl(productEntity.getMainImg());
            if (mainCount == 0) {
                msfFileService.deleteFileByUrl(productEntity.getMainImg());
            }

            if (CollectionUtil.isNotEmpty(productEntity.getInfo())) {
                productEntity.getInfo().forEach(model -> {

                    Long infoCount = productOrderService.imgCountByUrl(model);
                    if (infoCount == 0) {
                        msfFileService.deleteFileByUrl(model);
                    }
                });
            }
        }
    }

    @Override
    public SummaryResponse summary() {

        String summary = msfConfigService.getValueByCode(ConfigEnums.HOME_INFO.name());
        if (StringUtils.isNotBlank(summary)) {
            return JSON.parseObject(summary, SummaryResponse.class);
        }
        return null;
    }

    @Override
    public PageResult<ProductResponse> homePage(ProductRequest request, Integer pageIndex, Integer pageSize) {

        Page<ProductResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.baseMapper.homePage(request);
        PageResult<ProductResponse> result = new PageResult<>(page);
        return result;
    }

    @Override
    public ProductResponse frontInfo(Integer id) {

        ProductResponse productResponse = new ProductResponse();
        ProductEntity productEntity = this.baseMapper.selectById(id);
        if (productEntity != null) {
            BeanUtils.copyProperties(productEntity, productResponse);
        }
        return productResponse;
    }

    @Transactional
    @Override
    public void addPerson(ProductPersonRequest request) {

        Wrapper<ProductToPersonEntity> wrapper = new QueryWrapper<ProductToPersonEntity>().lambda()
                .eq(ProductToPersonEntity::getProductId, request.getProductId());
        productToPersonService.remove(wrapper);

        if (CollectionUtil.isNotEmpty(request.getPersonIds())) {

            List<ProductToPersonEntity> productList = new ArrayList<>();
            for (int i = 0; i < request.getPersonIds().size(); i++) {

                ProductToPersonEntity productToPersonEntity = new ProductToPersonEntity();
                productToPersonEntity.setProductId(request.getProductId());
                productToPersonEntity.setPersonId(request.getPersonIds().get(i));
                productToPersonEntity.setSort(i);
                productList.add(productToPersonEntity);
            }
            productToPersonService.saveBatch(productList);
        }
    }

    @Transactional
    @Override
    public void updateStatus(ProductStatusRequest request) {

        Wrapper<ProductEntity> wrapper = new UpdateWrapper<ProductEntity>().lambda()
                .eq(ProductEntity::getId, request.getId())
                .set(ProductEntity::getStatus, request.getStatus());
        this.update(wrapper);
    }

}
