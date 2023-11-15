package com.wxmblog.reservation.common.constant;

import com.wxmblog.base.common.utils.SpringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PropertiesConstants {

    @Value("${wx.template}")
    private String Wx_Template;

    @Value("${businessName}")
    private String businessName;

    @Value("${businessPhone}")
    private String businessPhone;

    @Value("${alisms.orderApply}")
    private String orderApply;

    @Value("${alisms.orderCancel}")
    private String orderCancel;

    @Value("${alisms.orderComplete}")
    private String orderComplete;

    @Value("${alisms.orderStart}")
    private String orderStart;

    @Value("${alisms.orderPersonStart}")
    private String orderPersonStart;


    public static String Wx_Template() {
        return SpringUtils.getBean(PropertiesConstants.class).getWx_Template();
    }

    public static String businessName() {
        return SpringUtils.getBean(PropertiesConstants.class).getBusinessName();
    }

    public static String businessPhone() {
        return SpringUtils.getBean(PropertiesConstants.class).getBusinessPhone();
    }

    public static String orderApply() {
        return SpringUtils.getBean(PropertiesConstants.class).getOrderApply();
    }

    public static String orderCancel() {
        return SpringUtils.getBean(PropertiesConstants.class).getOrderCancel();
    }

    public static String orderComplete() {
        return SpringUtils.getBean(PropertiesConstants.class).getOrderComplete();
    }

    public static String orderStart() {
        return SpringUtils.getBean(PropertiesConstants.class).getOrderStart();
    }

    public static String orderPersonStart() {
        return SpringUtils.getBean(PropertiesConstants.class).getOrderPersonStart();
    }

}
