package com.xue.sell.exception;

import com.xue.sell.enums.ResultEnum;

/**
 * Created by miller on 2018/5/6
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
