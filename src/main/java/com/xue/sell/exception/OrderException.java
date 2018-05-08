package com.xue.sell.exception;

import com.xue.sell.enums.ResultEnum;

/**
 * Created by miller on 2018/5/8
 */
public class OrderException extends SellException {
    public OrderException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public OrderException(Integer code, String message) {
        super(code, message);
    }
}
