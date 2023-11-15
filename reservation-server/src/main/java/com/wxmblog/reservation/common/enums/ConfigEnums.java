package com.wxmblog.reservation.common.enums;

public enum ConfigEnums {

    HOME_INFO("首页预览信息");

    private String desc;

    ConfigEnums(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
