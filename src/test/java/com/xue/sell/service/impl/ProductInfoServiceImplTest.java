package com.xue.sell.service.impl;

import com.xue.sell.enums.ProductStatusEnum;
import com.xue.sell.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by miller on 2018/5/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void findOne() {
        ProductInfo result = service.findOne("1231231");
        Assert.assertEquals("1231231",result.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> results = service.findUpAll();
        Assert.assertNotEquals(0,results.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> results = service.findAll(request);

//        System.out.println(results.getTotalElements());
        Assert.assertNotEquals(0,results.getTotalElements());
    }

    @Test
    @Transactional
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://dwad.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(1);

        ProductInfo result = service.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void offSale(){
        ProductInfo productInfo = service.offSale("123456");
        Assert.assertTrue("下架成功",productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN);
    }

    @Test
    @Transactional
    public void onSale(){
        ProductInfo productInfo = service.onSale("123456");
        Assert.assertTrue("上架成功",productInfo.getProductStatusEnum() == ProductStatusEnum.UP);
    }


}