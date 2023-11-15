package com.wxmblog.reservation.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxmblog.base.auth.authority.service.WxAppletService;
import com.wxmblog.base.common.exception.JrsfException;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.utils.ThreadUtil;
import com.wxmblog.base.common.utils.TokenUtils;
import com.wxmblog.base.file.service.MsfFileService;
import com.wxmblog.reservation.common.constant.Constants;
import com.wxmblog.reservation.common.enums.OrderStatus;
import com.wxmblog.reservation.common.exception.ExceptionEnum;
import com.wxmblog.reservation.common.rest.request.order.admin.OrderAdminRequest;
import com.wxmblog.reservation.common.rest.request.order.front.OrderSubmitRequest;
import com.wxmblog.reservation.common.rest.response.order.admin.OrderPageResponse;
import com.wxmblog.reservation.common.rest.response.order.front.HistoryOrderResponse;
import com.wxmblog.reservation.common.rest.response.order.front.OrderDetailResponse;
import com.wxmblog.reservation.dao.ProductOrderDao;
import com.wxmblog.reservation.entity.PersonEntity;
import com.wxmblog.reservation.entity.ProductEntity;
import com.wxmblog.reservation.entity.ProductOrderEntity;
import com.wxmblog.reservation.service.*;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service("productOrderService")
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderDao, ProductOrderEntity> implements ProductOrderService {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    ProductService productService;

    @Autowired
    MsfFileService msfFileService;

    @Autowired
    PersonService personService;

    @Autowired
    WxAppletService wxAppletService;

    @Autowired
    FrUserService frUserService;

    @Autowired
    SmsSendService smsSendService;

    @Transactional
    @Override
    public void addOrder(OrderSubmitRequest request) {

        RLock lock = redissonClient.getLock(Constants.ORDER_ADD + TokenUtils.getOwnerId());
        try {
            lock.lock();
            Wrapper<ProductOrderEntity> wrapper = new QueryWrapper<ProductOrderEntity>().lambda()
                    .eq(ProductOrderEntity::getProductId, request.getProductId())
                    .eq(ProductOrderEntity::getUserId, TokenUtils.getOwnerId())
                    .eq(ProductOrderEntity::getStartTime, request.getStartTime())
                    .eq(ProductOrderEntity::getStatus, OrderStatus.WAIT);
            Long count = this.baseMapper.selectCount(wrapper);

            if (count > 0) {
                throw new JrsfException(ExceptionEnum.ORDER_EXIST_EXCEPTION);
            }

            Wrapper<ProductOrderEntity> wrapperSame = new QueryWrapper<ProductOrderEntity>().lambda()
                    .eq(ProductOrderEntity::getProductId, request.getProductId())
                    .eq(ProductOrderEntity::getStartTime, request.getStartTime())
                    .eq(ProductOrderEntity::getStatus, OrderStatus.WAIT);
            Long countSame = this.baseMapper.selectCount(wrapperSame);
            if (countSame > 0) {
                throw new JrsfException(ExceptionEnum.ORDER_FULL_EXCEPTION);
            }

            ProductOrderEntity productOrderEntity = new ProductOrderEntity();

            BeanUtils.copyProperties(request, productOrderEntity);

            ProductEntity productEntity = productService.getById(request.getProductId());
            if (productEntity != null) {
                BeanUtils.copyProperties(productEntity, productOrderEntity, "id");
                productOrderEntity.setPreImg(productEntity.getPreImg());
            }

            if (request.getPersonId() != null) {

                PersonEntity personEntity = personService.getById(request.getPersonId());
                if (personEntity != null) {
                    productOrderEntity.setPersonId(request.getPersonId());
                    productOrderEntity.setPersonName(personEntity.getName());
                    productOrderEntity.setPersonPreImg(personEntity.getPreImg());
                }
            }

            productOrderEntity.setStatus(OrderStatus.WAIT);
            productOrderEntity.setUserId(TokenUtils.getOwnerId());
            this.save(productOrderEntity);

            Long seconds = DateUtil.between(new Date(), request.getStartTime(), DateUnit.SECOND);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(request.getStartTime());
            calendar.add(Calendar.DATE, 3);
            Long secondsComplete = DateUtil.between(new Date(), calendar.getTime(), DateUnit.SECOND);


            //三天没有服务 自动为已过期
            ThreadUtil.getInstance().scheduledThreadPool.schedule(() -> {

                ProductOrderEntity oldOrder = this.baseMapper.selectById(productOrderEntity.getId());
                if (oldOrder != null && OrderStatus.WAIT.equals(oldOrder.getStatus())) {
                    oldOrder.setStatus(OrderStatus.EXPIRED);
                    this.baseMapper.updateById(oldOrder);
                }

            }, secondsComplete, TimeUnit.SECONDS);

            //如果预约时间大于半小时 提醒用户即将开始
            if (seconds > 1800) {
                //服务开始前提醒
                ThreadUtil.getInstance().scheduledThreadPool.schedule(() -> {

                    smsSendService.serviceStart(productOrderEntity.getId());

                }, seconds - 1800, TimeUnit.SECONDS);
            }

            //用户下单提醒服务人员
            smsSendService.sendMsgToPerson(productOrderEntity);

        } finally {
            lock.unlock();
        }
    }

    @Override
    public PageResult<OrderPageResponse> page(OrderAdminRequest request, Integer pageIndex, Integer pageSize) {

        if (StringUtils.isNotBlank(request.getTitle())) {
            request.setTitle("%" + request.getTitle() + "%");
        }

        if (StringUtils.isNotBlank(request.getContacts())) {
            request.setContacts("%" + request.getContacts() + "%");
        }

        if (StringUtils.isNotBlank(request.getPhone())) {
            request.setPhone("%" + request.getPhone() + "%");
        }


        Page<OrderPageResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.baseMapper.getPage(request);
        PageResult<OrderPageResponse> result = new PageResult<>(page);
        return result;

    }

    @Transactional
    @Override
    public void serviceOrder(Integer id) {

        ProductOrderEntity productOrderEntity = this.baseMapper.selectById(id);

        if (productOrderEntity != null) {
            if (!OrderStatus.WAIT.equals(productOrderEntity.getStatus())) {
                throw new JrsfException(ExceptionEnum.ORDER_COMPLETE_EXCEPTION);
            }

            productOrderEntity.setStatus(OrderStatus.SERVEB);
            this.baseMapper.updateById(productOrderEntity);
        }

        //通知用户已服务
        smsSendService.sendMsg(id);
    }

    @Override
    public HistoryOrderResponse historyOrder() {

        HistoryOrderResponse historyOrderResponse = new HistoryOrderResponse();
        Wrapper<ProductOrderEntity> wrapper = new QueryWrapper<ProductOrderEntity>().lambda()
                .eq(ProductOrderEntity::getUserId, TokenUtils.getOwnerId())
                .last(" order by id desc limit 1");
        List<ProductOrderEntity> productOrderEntityList = this.baseMapper.selectList(wrapper);
        if (CollectionUtil.isNotEmpty(productOrderEntityList)) {
            BeanUtils.copyProperties(productOrderEntityList.get(0), historyOrderResponse);
        }
        return historyOrderResponse;
    }

    @Override
    public OrderDetailResponse detail(Integer id) {

        OrderDetailResponse response = new OrderDetailResponse();
        ProductOrderEntity productOrderEntity = this.baseMapper.selectById(id);
        if (productOrderEntity != null) {
            BeanUtils.copyProperties(productOrderEntity, response);
        }

        return response;
    }

    @Transactional
    @Override
    public void cancel(Integer id) {

        ProductOrderEntity productOrderEntity = this.baseMapper.selectById(id);

        if (productOrderEntity != null) {

            if (!OrderStatus.WAIT.equals(productOrderEntity.getStatus())) {
                throw new JrsfException(ExceptionEnum.ORDER_COMPLETE_EXCEPTION);
            }
            productOrderEntity.setStatus(OrderStatus.CANCELED);
            this.baseMapper.updateById(productOrderEntity);

            //用户取消提醒服务人员
            smsSendService.sendMsgToPersonCancel(productOrderEntity);
        }

    }

    @Override
    public Long imgCountByUrl(String url) {
        return this.baseMapper.countImg(url);
    }

}
