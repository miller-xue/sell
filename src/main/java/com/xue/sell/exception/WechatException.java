package com.xue.sell.exception;

import com.xue.sell.enums.ResultEnum;

/**
 * Created by miller on 2018/5/10
 */
public class WechatException extends SellException {
    public WechatException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public WechatException(Integer code, String message) {
        super(code, message);
    }
}

