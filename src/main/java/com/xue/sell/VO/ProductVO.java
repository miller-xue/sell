package com.xue.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品包含类目
 * Created by miller on 2018/5/5
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String catagoryName;

    @JsonProperty("type")
    private Integer catagoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVoList;

    public ProductVO(String catagoryName, Integer catagoryType, List<ProductInfoVO> productInfoVoList) {
        this.catagoryName = catagoryName;
        this.catagoryType = catagoryType;
        this.productInfoVoList = productInfoVoList;
    }
}
