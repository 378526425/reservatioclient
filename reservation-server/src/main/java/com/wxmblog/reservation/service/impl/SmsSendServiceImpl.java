package com.wxmblog.reservation.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.wxmblog.base.auth.authority.service.WxAppletService;
import com.wxmblog.base.auth.common.rest.request.wx.WxTemplateData;
import com.wxmblog.base.auth.common.rest.request.wx.WxTemplateMsgRequest;
import com.wxmblog.base.common.rest.request.sms.SmsData;
import com.wxmblog.base.common.service.ISendSmsService;
import com.wxmblog.base.common.utils.SpringUtils;
import com.wxmblog.base.common.utils.TokenUtils;
import com.wxmblog.reservation.common.constant.PropertiesConstants;
import com.wxmblog.reservation.entity.FrUserEntity;
import com.wxmblog.reservation.entity.PersonEntity;
import com.wxmblog.reservation.entity.ProductOrderEntity;
import com.wxmblog.reservation.service.FrUserService;
import com.wxmblog.reservation.service.PersonService;
import com.wxmblog.reservation.service.ProductOrderService;
import com.wxmblog.reservation.service.SmsSendService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-08-21 14:55
 **/

@Service
public class SmsSendServiceImpl implements SmsSendService {

    @Autowired
    FrUserService frUserService;

    @Autowired
    WxAppletService wxAppletService;

    @Autowired
    PersonService personService;

    @Autowired
    ISendSmsService smsService;

    @Override
    public void sendMsg(Integer id) {

        ProductOrderService productOrderService = SpringUtils.getBean(ProductOrderService.class);

        ProductOrderEntity productOrderEntity = productOrderService.getById(id);

        if (productOrderEntity != null) {

            WxTemplateMsgRequest wxTemplateMsgRequest = new WxTemplateMsgRequest();
            wxTemplateMsgRequest.setTemplateId(PropertiesConstants.Wx_Template());
            FrUserEntity frUserEntity = frUserService.getById(productOrderEntity.getUserId());
            if (frUserEntity != null) {
                wxTemplateMsgRequest.setTouser(frUserEntity.getOpenId());
                wxTemplateMsgRequest.setData(Lists.newArrayList(
                        new WxTemplateData("time2", DateFormatUtils.format(productOrderEntity.getStartTime(), "yyyy-MM-dd HH:mm:ss")),
                        new WxTemplateData("thing9", "您的预约订单已服务完成"),
                        new WxTemplateData("thing7", productOrderEntity.getTitle()),
                        new WxTemplateData("thing8", PropertiesConstants.businessName()),
                        new WxTemplateData("phone_number12", PropertiesConstants.businessPhone())
                ));
                wxTemplateMsgRequest.setPage("pages/Home/Home");
                try {
                    wxAppletService.sendWxTemplateMessage(wxTemplateMsgRequest);
                } catch (Exception e) {

                }

            }

            //短信完成通知
            if (StringUtils.isNotBlank(productOrderEntity.getPhone())) {

                try {
                    smsService.sendSms(productOrderEntity.getPhone(), Lists.newArrayList(
                            new SmsData("name", PropertiesConstants.businessName()),
                            new SmsData("title", productOrderEntity.getTitle()),
                            new SmsData("phone", PropertiesConstants.businessPhone())
                    ), PropertiesConstants.orderComplete());
                } catch (Exception e) {

                }

            }
        }


    }

    @Override
    public void sendMsgToPerson(ProductOrderEntity productOrderEntity) {

        if (productOrderEntity.getPersonId() != null) {
            PersonEntity personEntity = personService.getById(productOrderEntity.getPersonId());

            if (StringUtils.isNotBlank(personEntity.getPhone())) {
                List<String> phones = ListUtil.of(personEntity.getPhone().split(" "));
                for (String phone : phones) {

                    try {
                        smsService.sendSms(phone, Lists.newArrayList(
                                new SmsData("time", DateUtil.format(productOrderEntity.getStartTime(), "yyyy-MM-dd HH:mm:ss")),
                                new SmsData("name", productOrderEntity.getTitle()),
                                new SmsData("phone", StringUtils.isNotBlank(productOrderEntity.getPhone()) ? productOrderEntity.getPhone() : "无")
                        ), PropertiesConstants.orderApply());
                    } catch (Exception e) {

                    }
                }
            }
        }
    }


    @Override
    public void sendMsgToPersonCancel(ProductOrderEntity productOrderEntity) {

        if (productOrderEntity.getPersonId() != null) {
            PersonEntity personEntity = personService.getById(productOrderEntity.getPersonId());

            if (StringUtils.isNotBlank(personEntity.getPhone())) {
                List<String> phones = ListUtil.of(personEntity.getPhone().split(" "));
                for (String phone : phones) {

                    try {
                        smsService.sendSms(phone, Lists.newArrayList(
                                new SmsData("time", DateUtil.format(productOrderEntity.getStartTime(), "yyyy-MM-dd HH:mm:ss")),
                                new SmsData("name", productOrderEntity.getTitle()),
                                new SmsData("phone", StringUtils.isNotBlank(productOrderEntity.getPhone()) ? productOrderEntity.getPhone() : "无")
                        ), PropertiesConstants.orderCancel());
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    @Override
    public void serviceStart(Integer id) {

        ProductOrderService productOrderService = SpringUtils.getBean(ProductOrderService.class);
        ProductOrderEntity productOrderEntity = productOrderService.getById(id);

        if (productOrderEntity != null) {
            WxTemplateMsgRequest wxTemplateMsgRequest = new WxTemplateMsgRequest();
            wxTemplateMsgRequest.setTemplateId(PropertiesConstants.Wx_Template());
            FrUserEntity frUserEntity = frUserService.getById(productOrderEntity.getUserId());
            if (frUserEntity != null) {
                wxTemplateMsgRequest.setTouser(frUserEntity.getOpenId());
                wxTemplateMsgRequest.setData(Lists.newArrayList(
                        new WxTemplateData("time2", DateFormatUtils.format(productOrderEntity.getStartTime(), "yyyy-MM-dd HH:mm:ss")),
                        new WxTemplateData("thing9", "您的预约订单即将开始"),
                        new WxTemplateData("thing7", productOrderEntity.getTitle()),
                        new WxTemplateData("thing8", PropertiesConstants.businessName()),
                        new WxTemplateData("phone_number12", PropertiesConstants.businessPhone())
                ));
                wxTemplateMsgRequest.setPage("pages/Home/Home");
                try {
                    wxAppletService.sendWxTemplateMessage(wxTemplateMsgRequest);
                } catch (Exception e) {

                }
            }

            //开始时通知顾客
            if (StringUtils.isNotBlank(productOrderEntity.getPhone())) {

                try {
                    smsService.sendSms(productOrderEntity.getPhone(), Lists.newArrayList(
                            new SmsData("time", DateUtil.format(productOrderEntity.getStartTime(), "yyyy-MM-dd HH:mm:ss")),
                            new SmsData("name", productOrderEntity.getTitle()),
                            new SmsData("phone", PropertiesConstants.businessPhone())
                    ), PropertiesConstants.orderStart());
                } catch (Exception e) {

                }

            }

            //开始时通知人员
            if (productOrderEntity.getPersonId() != null) {
                PersonEntity personEntity = personService.getById(productOrderEntity.getPersonId());

                if (StringUtils.isNotBlank(personEntity.getPhone())) {
                    List<String> phones = ListUtil.of(personEntity.getPhone().split(" "));
                    for (String phone : phones) {
                        try {
                            smsService.sendSms(phone, Lists.newArrayList(
                                    new SmsData("time", DateUtil.format(productOrderEntity.getStartTime(), "yyyy-MM-dd HH:mm:ss")),
                                    new SmsData("name", productOrderEntity.getTitle()),
                                    new SmsData("phone", StringUtils.isNotBlank(productOrderEntity.getPhone()) ? productOrderEntity.getPhone() : "无")
                            ), PropertiesConstants.orderPersonStart());
                        } catch (Exception e) {

                        }
                    }

                }
            }
        }


    }

}
