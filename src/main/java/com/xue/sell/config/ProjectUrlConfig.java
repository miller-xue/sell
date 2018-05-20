package com.xue.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by miller on 2018/5/20
 */
@Data
@Component
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

    /**
     * 微信公共平台授权url
     */
    private String wechatMpAuthorize;


    /**
     * 微信开放平台授权url
     */
    private String wechatOpenAuthorize;


    /**
     * 点餐系统
     */
    private String sell;
}
