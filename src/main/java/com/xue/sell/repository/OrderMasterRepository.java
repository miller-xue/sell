package com.xue.sell.repository;

import com.xue.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miller on 2018/5/5
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{

    /**
     * 按照买家openid查询订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
}
