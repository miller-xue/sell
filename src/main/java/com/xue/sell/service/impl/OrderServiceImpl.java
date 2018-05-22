package com.xue.sell.service.impl;

import com.xue.sell.converter.OrderMaster2OrderDTOConverter;
import com.xue.sell.dto.CartDTO;
import com.xue.sell.dto.OrderDTO;
import com.xue.sell.enums.OrderStatusEnum;
import com.xue.sell.enums.PayStatusEnum;
import com.xue.sell.enums.ResultEnum;
import com.xue.sell.exception.OrderException;
import com.xue.sell.exception.ProductException;
import com.xue.sell.pojo.OrderDetail;
import com.xue.sell.pojo.OrderMaster;
import com.xue.sell.pojo.ProductInfo;
import com.xue.sell.repository.OrderDetailRepository;
import com.xue.sell.repository.OrderMasterRepository;
import com.xue.sell.service.OrderService;
import com.xue.sell.service.PayService;
import com.xue.sell.service.ProductInfoService;
import com.xue.sell.service.PushWechatMessage;
import com.xue.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by miller on 2018/5/6
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductInfoService productService;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    PayService payService;

    @Autowired
    private PushWechatMessage pushWechatMessage;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        //1. 查询商品 (数量 价格)
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){//商品不存在
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.订单总价
            orderAmount = productInfo.getProductPrice()
                        .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                        .add(orderAmount);
            //3.保存订单详情
            BeanUtils.copyProperties(productInfo,orderDetail);
            //设置订单详情主键
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            //设置订单id
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }

        //3. 写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        //4.扣库存
        List<CartDTO> cartDTOList =  orderDTO.getOrderDetailList().stream()
                                    .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                                    .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);


        //TODO 返回值不对。需要等需求在去操作返回值
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId)throws OrderException {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw  new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList =  orderDetailRepository.findByOrOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw  new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //前端查询的结果不需要列表详情
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalPages());
    }


    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO)throws OrderException,ProductException {

        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);

        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【取消订单】 更新失败 orderMaster={}",orderMaster);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3.返回库存
        // 创建订单时,订单内必须有商品。Controller层会判断校验  OrderDTO是根据OrderId查询出来的。

        /*3.1判断订单是否有商品
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】 订单中无商品详情 orderDTO={}",orderDTO);
            throw new OrderException(ResultEnum.ORDER_DETAIL_EMPTY);
        }*/
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map( e ->
                        new CartDTO(e.getProductId(),e.getProductQuantity()))
                        .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //4.如果已支付给用户退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
//            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】 订单状态不正确, orderId={} ,orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【完结订单】 更新失败 orderMaster={}",orderMaster);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //TODO 推送微信模板消息
//        pushWechatMessage.orderStatus(orderDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付完成】 订单状态不正确, orderId={} ,orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】 订单支付状态不正确,orderDTO={}",orderDTO);
            throw new OrderException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //3.修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【订单支付完成】 更新失败 orderMaster={}",orderMaster);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //2.修改订单状态
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalPages());
    }
}
