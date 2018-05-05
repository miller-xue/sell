package com.xue.sell.utils;

import com.xue.sell.VO.ResultVO;

/**
 * Created by miller on 2018/5/5
 */
public class ResultVOUtil {
    public static ResultVO success(Object obj){
        return new ResultVO(0,"成功",obj);
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        return new ResultVO(code,msg);
    }
}
