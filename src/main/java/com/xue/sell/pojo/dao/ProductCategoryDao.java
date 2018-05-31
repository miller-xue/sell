package com.xue.sell.pojo.dao;

import com.xue.sell.pojo.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by miller on 2018/5/31
 */
@Repository
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;
}
