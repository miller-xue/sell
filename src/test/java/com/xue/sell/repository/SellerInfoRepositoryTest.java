package com.xue.sell.repository;

import com.xue.sell.pojo.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by miller on 2018/5/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    public static final String OPENID = "l221312";

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setUsername("123456");
        sellerInfo.setPassword("password");
        sellerInfo.setSellerId("123456");
        sellerInfo.setOpenid(OPENID);
        SellerInfo result = repository.save(sellerInfo);
        Assert.assertTrue("保存成功",result != null);
    }

    @Test
    public void findByOpenid() {
        SellerInfo result = repository.findByOpenid(OPENID);
        log.info("result={}",result);
        Assert.assertTrue("根据OPENID查询成功",result != null);
    }
}