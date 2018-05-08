package com.xue.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xue.sell.enums.OrderStatusEnum;
import com.xue.sell.enums.PayStatusEnum;
import com.xue.sell.pojo.OrderDetail;
import com.xue.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by miller on 2018/5/6
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;

    /* 买家名字 */
    private String buyerName;

    /*买家电话*/
    private String buyerPhone;

    /* 买家地址 */
    private String buyerAddress;

    /* 买家微信openid */
    private String buyerOpenid;

    /* 订单总金额 */
    private BigDecimal orderAmount;

    /*订单状态 默认为新下单*/
    private Integer orderStatus;

    /* 支付状态, 默认0未支付*/
    private Integer payStatus;

    /* 创建时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /* 更新时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
