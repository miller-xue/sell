package com.xue.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.xue.sell.dto.OrderDTO;
import com.xue.sell.enums.ResultEnum;
import com.xue.sell.exception.PayException;
import com.xue.sell.service.OrderService;
import com.xue.sell.service.PayService;
import com.xue.sell.utils.JsonUtil;
import com.xue.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by miller on 2018/5/12
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点单订餐";

    @Autowired
    private  BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信支付】 发起支付 request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);

        log.info("【微信支付】 发起支付 response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        // 1. 验证签名
        // 2. 支付状态
        // 3. 支付金额
        // 4. 支付入(下单人 == 支付入)

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知, payResponse={}", JsonUtil.toJson(payResponse));

        // 查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        //判断订单是否存在
        if (orderDTO == null) {
            log.error("【微信支付】 异步通知, 订单不存在, orderId={}", payResponse.getOrderId());
            throw new PayException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致(0.10 0.1)
        if(MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),payResponse.getOrderAmount()) == false){
            log.error("【微信支付】 异步通知, 订单金额不一致 orderId={}, 微信通知金额={}, 系统金额={}",
                    orderDTO.getOrderId(), payResponse.getOrderAmount().doubleValue(), orderDTO.getOrderAmount().doubleValue());
            throw new PayException(ResultEnum.WECHAT_PAY_NOTIFY_VERIFY_ERROR);
        }


        //修改订单支付状态
        orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信退款】 request={}",JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);

        log.info("【微信退款】 response={}",JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
