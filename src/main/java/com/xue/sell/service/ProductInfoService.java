package com.xue.sell.service;

import com.xue.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by miller on 2018/5/5
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存

    //减库存
}