package com.wxmblog.reservation.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxmblog.reservation.dao.ProductToPersonDao;
import com.wxmblog.reservation.entity.ProductToPersonEntity;
import com.wxmblog.reservation.service.ProductToPersonService;


@Service("productToPersonService")
public class ProductToPersonServiceImpl extends ServiceImpl<ProductToPersonDao, ProductToPersonEntity> implements ProductToPersonService {

}
