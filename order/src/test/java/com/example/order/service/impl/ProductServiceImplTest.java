package com.example.order.service.impl;

import com.example.order.daoobject.ProductInfo;
import com.example.order.service.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    void findUpAll() {
    }

    @Test
    void save() {
    }

    @Test
    void onSale() {
    }

    @Test
    void offSale() {
    }

    @Test
    void increaseStock() {
    }

    @Test
    void decreaseStock() {
    }
}