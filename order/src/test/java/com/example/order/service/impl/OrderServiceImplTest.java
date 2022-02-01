package com.example.order.service.impl;

import com.example.order.dto.OrderDTO;
import com.example.order.service.OrderService;
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
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    void findOne() {
    }

    @Test
    void createOne() {
    }

    @Test
    void cancel() {
    }

    @Test
    void finish() {
        OrderDTO orderDTO = orderService.findOne("1643593214844192620");
        orderDTO = orderService.finish(orderDTO);
        Assert.assertEquals(new Integer(1), orderDTO.getOrderStatus());
    }

    @Test
    void paid() {
        OrderDTO orderDTO = orderService.findOne("1643593214844192620");
        orderDTO = orderService.paid(orderDTO);
        Assert.assertEquals(new Integer(1), orderDTO.getPayStatus());
    }

    @Test
    void findList() {

    }

    @Test
    void testFindList() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
//        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
        Assert.assertTrue("findtest order page", orderDTOPage.getTotalElements() > 0);
    }
}