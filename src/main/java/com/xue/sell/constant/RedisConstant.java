package com.xue.sell.constant;

/**
 * redis常量
 * Created by miller on 2018/5/21
 */
public interface RedisConstant {

    /*token前缀*/
    String TOKEN_PREFIX = "token_%s";

    /*token在redis中过期时间*/
    Integer EXPIRE = 7200; //2小时

}
