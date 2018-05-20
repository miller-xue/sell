package com.xue.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信支付配置
 * Created by miller on 2018/5/12
 */
@Component
public class WechatPayConfig {

    /* 注入微信帐户配置 */
    @Autowired
    private WechatAccountConfig accountConfig;


    /* 生成配置对象 */
    public WxPayH5Config wechatPayConfig(){
        WxPayH5Config config = new WxPayH5Config();
        config.setAppId(accountConfig.getMpAppId());
        config.setAppSecret(accountConfig.getMpAppSecret());
        config.setKeyPath(accountConfig.getKeyPath());
        config.setMchId(accountConfig.getMchId());
        config.setMchKey(accountConfig.getMchKey());
        config.setNotifyUrl(accountConfig.getNotifyUrl());
        return config;
    }

    /**
     * 把支付服务交给spring管理
     * @return
     */
    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wechatPayConfig());
        return bestPayService;
    }
}
