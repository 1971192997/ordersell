package com.example.order.utils;

import com.example.order.enums.CodeEnum;

public class EnumUtil {

    public static <T extends CodeEnum> T getCode(Integer code, Class<T> enumClass) {

        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

}
