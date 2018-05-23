package com.xue.sell.exception;

import com.xue.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by miller on 2018/5/6
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
