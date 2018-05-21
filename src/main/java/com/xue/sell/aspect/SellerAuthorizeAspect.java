package com.xue.sell.aspect;

import com.xue.sell.Constant.CookieConstant;
import com.xue.sell.Constant.RedisConstant;
import com.xue.sell.exception.SellerAuthorizeException;
import com.xue.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by miller on 2018/5/21
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 拦截所有seller的操作 不包括SellerUserController的操作
     */
    @Pointcut("execution(public * com.xue.sell.controller.Seller*.*(..))" +
    "&& !execution(public * com.xue.sell.controller.SellerUserController.*(..))")
    public void verify(){}


    @Before("verify()")
    public void doVerify(){
        //TODO 不进行拦截
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //查询Cookie
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if(cookie == null){
//            log.info("【登陆校验】 cookie中查不到Token");
//            throw new SellerAuthorizeException();
//        }
//
//
//        //去Redis里查
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        if(StringUtils.isEmpty(tokenValue)){
//            log.info("【登陆校验】 redis中查不到Token");
//            throw new SellerAuthorizeException();
//        }
    }
}
