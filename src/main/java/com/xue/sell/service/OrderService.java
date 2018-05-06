package com.xue.sell.service;

import com.xue.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by miller on 2018/5/6
 */
public interface OrderService {

    /* 创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /* 查询单个订单 */
    OrderDTO findOne(String orderId);

    /* 查询订单列表 */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /* 根据id取消订单 */
    public OrderDTO cancel(String orderId);

    /* 取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);

    /* 完结订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /* 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);
}
