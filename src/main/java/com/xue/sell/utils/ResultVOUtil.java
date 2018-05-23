package com.xue.sell.utils;

import com.xue.sell.VO.ResultVO;
import com.xue.sell.exception.SellException;

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

    public static ResultVO error(SellException e){
        return new ResultVO(e.getCode(),e.getMessage());
    }
}
