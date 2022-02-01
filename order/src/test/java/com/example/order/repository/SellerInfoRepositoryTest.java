package com.example.order.repository;

import com.example.order.daoobject.SellerInfo;
import com.example.order.utils.KeyUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SellerInfoRepositoryTest {
    @Autowired
    SellerInfoRepository repository;

    @Test
    void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenId(KeyUtil.genUniqueKey());
        sellerInfo.setPassword("123456");
        sellerInfo.setUserId("2");
        sellerInfo.setUserName("rose");
        SellerInfo res = repository.save(sellerInfo);
        Assert.assertNotNull(res);
    }

    @Test
    void findByOpenId() {
        SellerInfo sellerInfo = repository.findByOpenId("1643688224030656335");
        Assert.assertNotNull(sellerInfo);
    }
}