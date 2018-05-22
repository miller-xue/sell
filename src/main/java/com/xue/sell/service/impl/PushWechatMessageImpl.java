package com.xue.sell.service.impl;

import com.xue.sell.config.WechatAccountConfig;
import com.xue.sell.dto.OrderDTO;
import com.xue.sell.service.PushWechatMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by miller on 2018/5/22
 */
@Service
@Slf4j
public class PushWechatMessageImpl implements PushWechatMessage
{
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser("o1wAo0puJqioIHOUKXTo43XkXmEg"); //TODO orderDTO获取openid

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","亲请记得收货"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","13022992263"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "$" +orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临")
        );

        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error("【微信模板消息】 发送失败  {}",e);
        }
    }
}
