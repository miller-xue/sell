package com.xue.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信帐户配置
 * Created by miller on 2018/5/9
 */
@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WechatAccountConfig {

    /**
     * 公众平台appId
     */
    private String mpAppId;

    /**
     * 公众平台appSecret
     */
    private String mpAppSecret;

    /**
     * 开放平台appId
     */
    private String openAppId;

    /**
     * 开放平台appSecret
     */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通地址
     */
    private String notifyUrl;

    /**
     * 微信模板Ids
     */
    private Map<String,String> templateId;
}
