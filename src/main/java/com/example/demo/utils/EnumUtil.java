package com.example.demo.utils;

import com.example.demo.enums.CodeEnum;

/**
 * @author yudong
 * @create 2019-08-08 11:09
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
