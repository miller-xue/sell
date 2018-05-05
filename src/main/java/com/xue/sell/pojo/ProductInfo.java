package com.xue.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品
 * Created by miller on 2018/5/5
 */
@Entity
@Data
public class ProductInfo {

    /*商品id*/
    @Id
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

    /*状态 0 正常 1下架*/
    private Integer productStatus;

    /* 类目类型 */
    private Integer categoryType;
}
