package com.xue.sell.utils;

import com.xue.sell.enums.CodeEnum;

/**
 * Created by miller on 2018/5/12
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each: enumClass.getEnumConstants()){
            if(each.getCode().equals(code)){
                return each;
            }
        }
        return null;
    }
}
