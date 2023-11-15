package com.wxmblog.reservation.common.enums;

public enum TimeUnit {

    DAY("天"),
    HOUR("小时"),
    MINUTE("分钟");
    private String desc;

    TimeUnit(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
