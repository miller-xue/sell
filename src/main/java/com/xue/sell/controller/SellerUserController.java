package com.xue.sell.controller;

import com.xue.sell.constant.CookieConstant;
import com.xue.sell.constant.RedisConstant;
import com.xue.sell.config.ProjectUrlConfig;
import com.xue.sell.enums.ResultEnum;
import com.xue.sell.pojo.SellerInfo;
import com.xue.sell.service.SellerService;
import com.xue.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 * Created by miller on 2018/5/21
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig urlConfig;

    @GetMapping("/login")
    public String login(HttpServletResponse response,
                        @RequestParam("openid") String openid,
                        Model model) {
        // 1. openid和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            model.addAttribute("msg", ResultEnum.LOGIN_FAIL.getMessage());
            model.addAttribute("url","/sell/seller/order/list");
            return "common/error";
        }
        // 2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE; //过期时间

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);

        // 3. 设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return "redirect:" + urlConfig.getSell() + "/seller/product/list"; //绝对地址
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) {
        // 从cookie查询session
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie != null){
            // 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            // 清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0); //把cookie设置0值为null
        }
        model.addAttribute("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        model.addAttribute("url","/sell/seller/order/list"); //相对地址
        return "common/success";

    }
}
