package com.xue.sell.service.impl;

import com.xue.sell.exception.SellException;
import com.xue.sell.service.RedisLock;
import com.xue.sell.service.SeckillService;
import com.xue.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miller on 2018/6/1
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static final int TIMEOUT = 10 * 1000;

    @Autowired
    private RedisLock redisLock;

    /**
     * 国庆活动, 皮蛋粥特价, 限量100000份
     */
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;

    static
    {
        /**
         * 模拟多个表, 商品信息表, 库存表, 秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();

        products.put("123456",100000);
        stock.put("123456",100000);
    }

    private String queryMap(String productId)
    {
        return "国庆活动, 皮蛋粥特价,限量份"
                + products.get(productId)
                +" 还剩: " + stock.get(productId)+ " 份"
                +"该商品成功下单用户数目: "
                +orders.size() + " 人";
    }

    @Override
    public String querySeckillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if(!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101,"哎呦喂,人也太多了换个姿势在试试");
        }

        //1. 查询该商品的库存, 为0则活动结束
        int stockNum = stock.get(productId);
        if(stockNum <= 0){
            throw new SellException(100,"活动结束");
        }else {
            //2.下单
            orders.put(KeyUtil.getUniqueKey(),productId);
            //3.减库存
            stockNum = stockNum -1;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }

        //解锁
        redisLock.lock(productId,String.valueOf(time));
    }
}
