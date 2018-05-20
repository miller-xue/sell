package com.xue.sell.exception;

import com.xue.sell.enums.ResultEnum;

/**
 * Created by miller on 2018/5/20
 */
public class CategoryException extends SellException {
    public CategoryException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public CategoryException(Integer code, String message) {
        super(code, message);
    }
}
