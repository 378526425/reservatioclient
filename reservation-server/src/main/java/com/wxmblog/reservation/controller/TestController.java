package com.wxmblog.reservation.controller;

import com.google.common.collect.Lists;
import com.wxmblog.base.auth.authority.service.WxAppletService;
import com.wxmblog.base.auth.common.rest.request.wx.WxTemplateData;
import com.wxmblog.base.auth.common.rest.request.wx.WxTemplateMsgRequest;
import com.wxmblog.base.common.annotation.AuthIgnore;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.reservation.common.rest.response.order.front.HistoryOrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSort;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: reservation
 * @description:
 * @author: Mr.Wang
 * @create: 2023-08-17 16:27
 **/

@RestController
@RequestMapping("reservation/test")
@Api(tags = "测试")
public class TestController {

    @Autowired
    WxAppletService wxAppletService;

    @ApiOperation("发送模板消息")
    @ApiOperationSort(value = 2)
    @GetMapping("/sendTemplate")
    @AuthIgnore
    public R<Void> historyOrder() {

        WxTemplateMsgRequest wxTemplateMsgRequest = new WxTemplateMsgRequest();

        wxTemplateMsgRequest.setTemplateId("YGOE7mi2DuVsyY2oW_VG3Q8S11cntICr6eSXIUxvd8g");
        wxTemplateMsgRequest.setTouser("ofKw_4ylF-leswYTqtf3JD6VExHs");
        wxTemplateMsgRequest.setData(Lists.newArrayList(
                new WxTemplateData("time1", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")),
                new WxTemplateData("time10", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")),
                new WxTemplateData("name15", "名字"),
                new WxTemplateData("thing2", "内容"),
                new WxTemplateData("phone_number18", "手机号")
        ));
        wxTemplateMsgRequest.setMiniprogramState("trial");
        wxAppletService.sendWxTemplateMessage(wxTemplateMsgRequest);
        return R.ok();

    }
}
