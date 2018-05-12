package com.xue.sell.exception;

import com.xue.sell.enums.ResultEnum;

/**
 * Created by miller on 2018/5/12
 */
public class PayException extends SellException {
    public PayException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public PayException(Integer code, String message) {
        super(code, message);
    }
}
