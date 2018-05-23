package com.xue.sell.handler;

import com.xue.sell.VO.ResultVO;
import com.xue.sell.config.ProjectUrlConfig;
import com.xue.sell.exception.SellException;
import com.xue.sell.exception.SellerAuthorizeException;
import com.xue.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by miller on 2018/5/21
 */
@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig urlConfig;

    //拦截登陆异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public String handlerAuthorizedException(){
        return "redirect:"
                .concat(urlConfig.getWechatOpenAuthorize()) //TODO 跳转到授权页面
                .concat("/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(urlConfig.getSell())
                .concat("/seller/order/list");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e) {
        return ResultVOUtil.error(e);
    }

}
