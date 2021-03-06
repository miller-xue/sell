package com.xue.sell.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回给最外层对象
 * Created by miller on 2018/5/5
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -7030237385589807166L;

    /* 错误码 */
    private Integer code;
    /* 提示信息 */
    private String msg;
    /* 具体内容 */
    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
