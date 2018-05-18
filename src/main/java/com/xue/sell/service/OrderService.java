package com.xue.sell.service;

import com.xue.sell.dto.OrderDTO;
import com.xue.sell.exception.OrderException;
import com.xue.sell.exception.ProductException;
import com.xue.sell.exception.SellException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by miller on 2018/5/6
 */
public interface OrderService {

    /* 创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /* 查询单个订单 */
    OrderDTO findOne(String orderId)throws OrderException;

    /* 查询订单列表 */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /* 取消订单 */
    OrderDTO cancel(OrderDTO orderDTO)throws OrderException,ProductException;

    /* 完结订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /* 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);

    /* 查询订单列表 */
    Page<OrderDTO> findList( Pageable pageable);
}
