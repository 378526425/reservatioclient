package com.wxmblog.reservation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.reservation.common.rest.request.person.admin.EditPersonRequest;
import com.wxmblog.reservation.common.rest.request.person.admin.PersonRequest;
import com.wxmblog.reservation.common.rest.request.product.admin.ProductAdminRequest;
import com.wxmblog.reservation.common.rest.response.person.PersonResponse;
import com.wxmblog.reservation.common.rest.response.product.admin.ProductResponse;
import com.wxmblog.reservation.entity.PersonEntity;

import java.util.List;


/**
 * 人员
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-10 17:24:35
 */
public interface PersonService extends IService<PersonEntity> {

    void editPerson(EditPersonRequest request);


    PageResult<PersonResponse> page(PersonRequest request, Integer pageIndex, Integer pageSize);

    PageResult<PersonResponse> page(Integer productId, Integer pageIndex, Integer pageSize);

    PersonResponse info(Integer id);

    void delete(Integer id);

    List<PersonResponse> getAllPerson();

    List<PersonResponse> getPersonByProductId(Integer id);
}

