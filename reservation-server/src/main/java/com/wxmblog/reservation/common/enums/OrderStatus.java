package com.wxmblog.reservation.common.enums;

public enum OrderStatus {

    WAIT("待服务"),
    SERVEB("已服务"),
    CANCELED("已取消"),
    EXPIRED("已过期");
    private String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
