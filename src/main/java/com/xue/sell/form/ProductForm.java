package com.xue.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by miller on 2018/5/19
 */
@Data
public class ProductForm {

    /*商品id*/
    private String productId;

    /*名称*/
    private String productName;

    /*价格*/
    private BigDecimal productPrice;

    /*库存*/
    private Integer productStock;

    /*介绍*/
    private String productDescription;

    /*小图*/
    private String productIcon;


    /* 类目类型 */
    private Integer categoryType;
}
