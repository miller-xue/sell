package com.xue.sell.repository;

import com.xue.sell.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miller on 2018/5/20
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
    
}
