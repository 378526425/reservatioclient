package com.wxmblog.reservation.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.utils.SpringBeanUtils;
import com.wxmblog.base.common.utils.SpringUtils;
import com.wxmblog.base.file.service.MsfFileService;
import com.wxmblog.reservation.common.rest.request.person.admin.EditPersonRequest;
import com.wxmblog.reservation.common.rest.request.person.admin.PersonRequest;
import com.wxmblog.reservation.common.rest.response.person.PersonResponse;
import com.wxmblog.reservation.common.rest.response.product.admin.ProductResponse;
import com.wxmblog.reservation.entity.ProductEntity;
import com.wxmblog.reservation.entity.ProductOrderEntity;
import com.wxmblog.reservation.service.ProductOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxmblog.reservation.dao.PersonDao;
import com.wxmblog.reservation.entity.PersonEntity;
import com.wxmblog.reservation.service.PersonService;

import java.util.List;


@Service("personService")
public class PersonServiceImpl extends ServiceImpl<PersonDao, PersonEntity> implements PersonService {

    @Autowired
    MsfFileService msfFileService;


    @Override
    public void editPerson(EditPersonRequest request) {

        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(request, personEntity);
        if (request.getId() == null) {
            this.baseMapper.insert(personEntity);
        } else {
            Wrapper<PersonEntity> wrapper = new QueryWrapper<PersonEntity>().lambda()
                    .eq(PersonEntity::getId, request.getId());
            PersonEntity personEntityOld = this.getById(request.getId());
            if (personEntityOld != null) {
                if (StringUtils.isNotBlank(personEntityOld.getPreImg()) && !personEntityOld.getPreImg().equals(request.getPreImg())) {
                    ProductOrderService productOrderService = SpringUtils.getBean(ProductOrderService.class);
                    Long mainCount = productOrderService.imgCountByUrl(personEntityOld.getPreImg());
                    if (mainCount == 0) {
                        msfFileService.deleteFileByUrl(personEntityOld.getPreImg());
                    }
                }
            }

            this.baseMapper.update(personEntity, wrapper);
        }
    }

    @Override
    public PageResult<PersonResponse> page(PersonRequest request, Integer pageIndex, Integer pageSize) {


        if (StringUtils.isNotBlank(request.getName())) {
            request.setName("%" + request.getName() + "%");
        }

        Page<PersonResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.baseMapper.personPage(request);
        PageResult<PersonResponse> result = new PageResult<>(page);
        return result;
    }

    @Override
    public PageResult<PersonResponse> page(Integer productId, Integer pageIndex, Integer pageSize) {

        Page<PersonResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.baseMapper.personPageByProduct(productId);
        PageResult<PersonResponse> result = new PageResult<>(page);
        return result;
    }

    @Override
    public PersonResponse info(Integer id) {

        PersonResponse personResponse = new PersonResponse();
        PersonEntity personEntity = this.baseMapper.selectById(id);
        if (personEntity != null) {
            BeanUtils.copyProperties(personEntity, personResponse);
        }

        return personResponse;
    }

    @Override
    public void delete(Integer id) {

        PersonEntity personEntity = this.getById(id);
        if (personEntity != null) {
            this.removeById(id);

            ProductOrderService productOrderService = SpringUtils.getBean(ProductOrderService.class);
            Long preCount = productOrderService.imgCountByUrl(personEntity.getPreImg());
            if (preCount == 0) {
                msfFileService.deleteFileByUrl(personEntity.getPreImg());
            }

        }
    }

    @Override
    public List<PersonResponse> getAllPerson() {
        return this.baseMapper.getAllPerson();
    }

    @Override
    public List<PersonResponse> getPersonByProductId(Integer id) {
        return this.baseMapper.getPersonByProductId(id);
    }
}
