package com.xue.sell.service.impl;

import com.xue.sell.dto.OrderDTO;
import com.xue.sell.enums.OrderStatusEnum;
import com.xue.sell.enums.PayStatusEnum;
import com.xue.sell.pojo.OrderDetail;
import com.xue.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by miller on 2018/5/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    private final String buyerOpenid = "110110";

    private final String ORDER_ID = "1525595661239108993";

    @Test
    @Transactional
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("侯琳");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("13022996276");
        orderDTO.setBuyerOpenid(buyerOpenid);

        List<OrderDetail> orderDetailList = new ArrayList<>();
//        OrderDetail od1 = new OrderDetail();
//        od1.setProductId("1234567");
//        od1.setProductQuantity(10);
//        orderDetailList.add(od1);

        OrderDetail od2 = new OrderDetail();
        od2.setProductId("12345671");
        od2.setProductQuantity(100);
        orderDetailList.add(od2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("result : []",orderDTO);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid,pageRequest);
        log.info("【查询当前用户的订单】 result={}",orderDTOPage.getContent());
        Assert.assertNotEquals(0,orderDTOPage.getTotalPages());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}