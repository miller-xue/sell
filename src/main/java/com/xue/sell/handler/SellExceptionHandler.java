package com.xue.sell.handler;

import com.xue.sell.config.ProjectUrlConfig;
import com.xue.sell.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by miller on 2018/5/21
 */
@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig urlConfig;

    //拦截登陆异常
    /*@ExceptionHandler(value = SellerAuthorizeException.class)
    public String handlerAuthorizedException(){
        return "redirect:"
                .concat(urlConfig.getSell())
                .concat("/");//TODO 跳转到授权页面
    }*/

}
