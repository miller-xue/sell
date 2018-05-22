package com.xue.sell.service.impl;

import com.xue.sell.dto.OrderDTO;
import com.xue.sell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by miller on 2018/5/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushWechatMessageImplTest {

    @Autowired
    private PushWechatMessageImpl wechatMessage;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {
        OrderDTO  orderDTO = orderService.findOne("1525595661239108993");
        wechatMessage.orderStatus(orderDTO);

    }
}