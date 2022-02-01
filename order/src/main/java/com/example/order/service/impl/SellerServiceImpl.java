package com.example.order.service.impl;

import com.example.order.daoobject.SellerInfo;
import com.example.order.repository.SellerInfoRepository;
import com.example.order.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerInfoRepository repository;

    @Override
    public SellerInfo findByOpenId(String openId) {
        return repository.findByOpenId(openId);
    }
}
