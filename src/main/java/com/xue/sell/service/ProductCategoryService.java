package com.xue.sell.service;

import com.xue.sell.pojo.ProductCategory;

import java.util.List;

/**
 * Created by miller on 2018/5/5
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
