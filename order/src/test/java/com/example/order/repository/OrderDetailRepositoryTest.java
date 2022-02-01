package com.example.order.repository;

import com.example.order.daoobject.OrderDetail;
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
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("10000");
        orderDetail.setOrderId("123456789");
        orderDetail.setProductId("11");
        orderDetail.setProductName("shrift");
        orderDetail.setProductIcon("ccccxxxx");
        orderDetail.setProductPrice(new BigDecimal(3.3));
        orderDetail.setProductQuantity(1);

        OrderDetail res = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(res);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("1643590990529521290");
        Assert.assertNotEquals(0, orderDetailList.size());

    }
}