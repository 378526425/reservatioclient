package com.wxmblog.reservation.dao;

import com.wxmblog.reservation.common.rest.request.person.admin.PersonRequest;
import com.wxmblog.reservation.common.rest.response.person.PersonResponse;
import com.wxmblog.reservation.entity.PersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 人员
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2023-07-10 17:24:35
 */
@Mapper
public interface PersonDao extends BaseMapper<PersonEntity> {


    List<PersonResponse> personPage(PersonRequest request);

    List<PersonResponse> getAllPerson();

    List<PersonResponse> getPersonByProductId(Integer id);

    List<PersonResponse> personPageByProduct(Integer id);
}
