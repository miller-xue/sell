package com.xue.sell.repository;

import com.xue.sell.pojo.OrderMaster;
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
public class OrderMasterRepositoryTest  {
    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "1213";

    @Test
//    @Transactional
    public  void saveTest(){
        OrderMaster order = new OrderMaster();
        order.setOrderId("123467");
        order.setBuyerName("师兄");
        order.setBuyerPhone("13021295656");
        order.setBuyerAddress("mukewang");
        order.setBuyerOpenid(OPENID);
        order.setOrderAmount(new BigDecimal(2.5));
        OrderMaster orderMaster = repository.save(order);
        Assert.assertNotNull(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderMaster> orderMasterList = repository.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertNotEquals(0,orderMasterList.getContent().size());
//        System.out.println(orderMasterList.getTotalPages());
//        System.out.println(orderMasterList.getTotalElements());
    }
}