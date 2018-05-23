package com.xue.sell.service.impl;

import com.xue.sell.pojo.SellerInfo;
import com.xue.sell.repository.SellerInfoRepository;
import com.xue.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 卖家用户Service
 * Created by miller on 2018/5/20
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
