package com.xue.sell.pojo;

import com.xue.sell.enums.OrderStatusEnum;
import com.xue.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by miller on 2018/5/5
 */
@Entity
@Data
@DynamicUpdate //不更新updateTime
public class OrderMaster {
    /* 订单Id */
    @Id
    private String orderId;

    /* 买家名字 */
    private String buyerName;

    /*买家电话*/
    private String buyerPhone;

    /* 买家地址 */
    private String buyerAddress;

    /* 买家微信openid */
    private String openid;

    /* 订单总金额 */
    private BigDecimal orderAmount;

    /*订单状态 默认为新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /* 支付状态, 默认0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /* 创建时间 */
    private Data createTime;

    /* 更新时间 */
    private Data updateTime;
}
