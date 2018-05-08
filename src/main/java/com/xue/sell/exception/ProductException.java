package com.xue.sell.exception;

import com.xue.sell.enums.ResultEnum;

/**
 * Created by miller on 2018/5/8
 */
public class ProductException extends SellException {

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public ProductException(Integer code, String message) {
        super(code, message);
    }
}
