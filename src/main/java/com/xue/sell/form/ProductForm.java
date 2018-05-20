package com.xue.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

/**
 * Created by miller on 2018/5/19
 */
@Data
public class ProductForm {

    /*商品id*/
    private String productId;

    /*名称*/
    @NotEmpty(message = "名称必填")
    private String productName;

    /*价格*/
//    @NotEmpty(message = "价格必填")
    private BigDecimal productPrice;

    /*库存*/
//    @NotEmpty(message = "库存必填")
    private Integer productStock;

    /*介绍*/
    @NotEmpty(message = "介绍必填")
    private String productDescription;

    /*小图*/
    private String productIcon;


    /* 类目类型 */
    private Integer categoryType;
}
