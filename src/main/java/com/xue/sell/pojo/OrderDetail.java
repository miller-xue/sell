package com.xue.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by miller on 2018/5/5
 */
@Entity
@Data
public class OrderDetail {

    /*详情Id*/
    @Id
    private String detailId;

    /*订单id*/
    private String orderId;

    /*商品id*/
    private String productId;

    /*商品名称*/
    private String productName;

    /*商品单价*/
    private BigDecimal productPrice;

    /*购买数量*/
    private Integer productQuantity;

    /*商品图片*/
    private String productIcon;
}
