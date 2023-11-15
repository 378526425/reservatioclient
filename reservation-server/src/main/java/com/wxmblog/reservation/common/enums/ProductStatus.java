package com.wxmblog.reservation.common.enums;

public enum ProductStatus {

    UP("上架"),
    DOWN("下架");
    private String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
