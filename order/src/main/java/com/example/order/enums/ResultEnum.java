package com.example.order.enums;

import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-06-11 18:56
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "success"),

    PARAM_ERROR(1, "invalid param"),

    PRODUCT_NOT_EXIST(10, "product not found"),

    PRODUCT_STOCK_ERROR(11, "stock err"),

    ORDER_NOT_EXIST(12, "order not found"),

    ORDERDETAIL_NOT_EXIST(13, "order detail not found"),

    ORDER_STATUS_ERROR(14, "invalid order status"),

    ORDER_UPDATE_FAIL(15, "order update failed"),

    ORDER_DETAIL_EMPTY(16, "order detail empty"),

    ORDER_PAY_STATUS_ERROR(17, "order pay status err"),

    CART_EMPTY(18, "cart empty"),

    ORDER_OWNER_ERROR(19, "wrong owner of order"),

    WECHAT_MP_ERROR(20, "vchat mp err"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "vpay verify err"),

    ORDER_CANCEL_SUCCESS(22, "order canceled"),

    ORDER_FINISH_SUCCESS(23, "order finished successfully"),

    PRODUCT_STATUS_ERROR(24, "invalid product status"),

    LOGIN_FAIL(25, "login failed"),

    LOGOUT_SUCCESS(26, "logout success"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
