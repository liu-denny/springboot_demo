package com.example.demo.exception;

import com.example.demo.enums.ResultEnum;

/**
 * @author yudong
 * @create 2019-08-05 18:11
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
