package com.xue.sell.utils;

/**
 * Created by miller on 2018/5/12
 */
public class MathUtil {

    private static final double Money_Range = 0.01;
    /**
     * 比较2个double是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(Double d1 , Double d2){
        double result = Math.abs(d1-d2);
        if(result < Money_Range){
            return true;
        }else {
            return false;
        }
    }
}
