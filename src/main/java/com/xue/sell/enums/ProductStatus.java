package com.xue.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by miller on 2018/5/5
 */
@Getter
public enum ProductStatus {

    UP(0,"在架"),DOWN(1,"下架")
    ;
    private Integer code;

    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
