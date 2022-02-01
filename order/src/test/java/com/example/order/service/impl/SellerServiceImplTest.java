package com.example.order.service.impl;

import com.example.order.daoobject.SellerInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SellerServiceImplTest {

    @Autowired
    SellerServiceImpl service;

    @Test
    void findByOpenIdTest() {
        SellerInfo info = service.findByOpenId("1643688224030656335");
        Assert.assertNotNull(info);
    }
}