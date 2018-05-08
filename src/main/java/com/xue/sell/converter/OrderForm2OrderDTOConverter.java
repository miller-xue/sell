package com.xue.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xue.sell.dto.OrderDTO;
import com.xue.sell.enums.ResultEnum;
import com.xue.sell.exception.SellException;
import com.xue.sell.form.OrderForm;
import com.xue.sell.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by miller on 2018/5/8
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = null;
        try {
            orderDetailList =  gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】 错误 string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
