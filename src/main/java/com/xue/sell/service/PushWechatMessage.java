package com.xue.sell.service;

import com.xue.sell.dto.OrderDTO;

/**
 * 推送微信消息
 * Created by miller on 2018/5/22
 */
public interface PushWechatMessage {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
