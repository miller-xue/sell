package com.xue.sell.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xue.sell.enums.ProductStatusEnum;
import com.xue.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * Created by miller on 2018/5/5
 */
@Entity
@Data
@DynamicUpdate
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

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
