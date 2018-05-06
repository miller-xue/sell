package com.xue.sell.converter;

import com.xue.sell.dto.OrderDTO;
import com.xue.sell.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by miller on 2018/5/6
 */
public class OrderMasterToOrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e ->
            convert(e)
        ).collect(Collectors.toList());
    }
}
