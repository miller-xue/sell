package com.xue.sell.service.impl;

import com.xue.sell.pojo.ProductCategory;
import com.xue.sell.service.ProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by miller on 2018/5/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryServiceImpl service;

    @Test
    public void findOne() {
       ProductCategory category = service.findOne(1);
        Assert.assertEquals(new Integer(1),category.getCategoryId());
    }

    @Test
    public void findAll() {
       List<ProductCategory> results = service.findAll();
       Assert.assertNotEquals(0,results.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> results = service.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0,results.size());
    }

    @Test
    public void save() {
        ProductCategory category = new ProductCategory("我最爱",4);
        ProductCategory result = service.save(category);
        Assert.assertNotNull(result.getCategoryId());
    }
}