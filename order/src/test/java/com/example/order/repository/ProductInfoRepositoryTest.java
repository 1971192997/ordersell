package com.example.order.repository;

import com.example.order.daoobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("xxxxx");
        productInfo.setProductPrice(new BigDecimal(9.5));
        productInfo.setProductName("rice");
        productInfo.setProductStock(100);
        productInfo.setCategoryType(3);
        productInfo.setProductDescription("well cooked");
        ProductInfo res = repository.save(productInfo);
        Assert.assertNotNull(res);

    }

    @Test
    public void findByStatusTest() {
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
    }


}