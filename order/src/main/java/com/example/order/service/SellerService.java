package com.example.order.service;

import com.example.order.daoobject.SellerInfo;

public interface SellerService {

    /**
     *
     * @param openId
     * @return
     */
    SellerInfo findByOpenId(String openId);
}
