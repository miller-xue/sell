package com.xue.sell.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by miller on 2018/5/5
 */
@Entity
@Data   //自动生成getter setter toString
@DynamicUpdate
public class ProductCategory {
    /* 类目Id*/
    @Id
    @GeneratedValue
    private Integer categoryId;
    /* 类目名 */
    private String categoryName;

    /* 类目编号 */
    private Integer categoryType;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
    /* 创建时间 */
    private Date createTime;

    /* 更新时间 */
    private Date updateTime;

    public ProductCategory() {
    }
}
