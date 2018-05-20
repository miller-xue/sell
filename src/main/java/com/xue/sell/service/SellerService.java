package com.xue.sell.service;

import com.xue.sell.pojo.SellerInfo;

/**
 * Created by miller on 2018/5/20
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
