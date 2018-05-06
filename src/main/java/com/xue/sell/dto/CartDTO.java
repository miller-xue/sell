package com.xue.sell.dto;

import lombok.Data;

/**
 * 购物车DTO
 * Created by miller on 2018/5/6
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
