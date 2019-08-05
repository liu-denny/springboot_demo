package com.example.demo.VO;

import lombok.Data;

/**
 * @author yudong
 * @create 2019-08-05 15:42
 */
@Data
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
