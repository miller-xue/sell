package com.xue.sell.utils;

import java.util.Random;

/**
 * Created by miller on 2018/5/6
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer num = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
