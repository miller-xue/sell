package com.xue.sell.service.impl;

import com.xue.sell.dto.OrderDTO;
import com.xue.sell.service.OrderService;
import com.xue.sell.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by miller on 2018/5/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayServiceImpl payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1525595355648963663");

        payService.create(orderDTO);
    }


    @Test
    public void refund(){

    }
}