package com.wxmblog.reservation.common.exception;

import com.wxmblog.base.common.interfaces.BaseExceptionEnumInterface;

public enum ExceptionEnum implements BaseExceptionEnumInterface {

    PRODUCT_EXIST_EXCEPTION(12001, "存在产品"),
    ORDER_EXIST_EXCEPTION(12002, "存在相似订单"),
    ORDER_COMPLETE_EXCEPTION(12003, "订单已完成"),
    ORDER_FULL_EXCEPTION(12004, "预约已满，请更换时间");

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
