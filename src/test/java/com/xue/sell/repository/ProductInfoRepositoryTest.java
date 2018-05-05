package com.xue.sell.repository;

import com.xue.sell.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by miller on 2018/5/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    ProductInfoRepository repository;

    @Test
    @Transactional
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1231231");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的皮蛋粥");
        productInfo.setProductIcon("http://dwad.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);

       ProductInfo result =  repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> results = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,results.size());
    }
}