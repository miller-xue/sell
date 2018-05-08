package com.xue.sell.service;

import com.xue.sell.dto.OrderDTO;

/**
 * 买家service
 * Created by miller on 2018/5/8
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消已个订单
    OrderDTO cancelOrder(String openid,String orderId);
}
