package com.xue.sell.pojo.mapper;

import com.xue.sell.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by miller on 2018/5/31
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","我最爱111");
        map.put("category_type",111);
        int result =  mapper.insertByMap(map);
        Assert.assertTrue("保存成功", result > 0);
    }


    @Test
    public void insertByObject() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("我爱");
        category.setCategoryType(123);
        int result =  mapper.insertByObject(category);
        Assert.assertTrue("保存成功", result > 0);
    }


    @Test
    public void findByCategoryType(){
        int categoryType = 123;
        ProductCategory category = mapper.findByCategoryType(categoryType);

        Assert.assertTrue("查询成功", category != null && categoryType == category.getCategoryType());
    }

    @Test
    public void findByCategoryName(){
        List<ProductCategory> list = mapper.findByCategoryName("我最爱");
        Assert.assertTrue("查询结果不能为空", !CollectionUtils.isEmpty(list));
    }

    @Test
    public void updateByCategoryType(){
        int result = mapper.updateByCategoryType(123,"我不爱你了");
        Assert.assertTrue("更新失败",result > 0);
    }

    @Test
    public void updateByObject(){

        ProductCategory category = new ProductCategory();
        category.setCategoryType(123);
        category.setCategoryName("123");
        int result = mapper.updateByObject(category);
        Assert.assertTrue("更新失败",result > 0);
    }
    @Test
    public void deleteByCategoryType(){
        int result = mapper.deleteByCategoryType(123);
        Assert.assertTrue("更新失败",result > 0);
    }

    @Test
    public void selectByCategoryType(){
        int categoryType = 110;
        ProductCategory category = mapper.selectByCategoryType(categoryType);

        Assert.assertTrue("查询失败", category != null && categoryType == category.getCategoryType());
    }
}