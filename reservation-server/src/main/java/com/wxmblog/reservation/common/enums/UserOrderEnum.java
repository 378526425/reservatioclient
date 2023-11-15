package com.wxmblog.reservation.common.enums;

public enum UserOrderEnum {

    WAIT("待服务"),
    SERVEB("已服务"),
    INVALID("无效");
    private String desc;

    UserOrderEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
