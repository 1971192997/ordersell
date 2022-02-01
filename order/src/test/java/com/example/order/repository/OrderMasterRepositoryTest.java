package com.example.order.repository;

import com.example.order.daoobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository orderMasterRepository;
    private final String OPENID = "110110";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456789");
        orderMaster.setBuyerName("wukong");
        orderMaster.setBuyerPhone("136");
        orderMaster.setBuyerAddress("bj");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);


    }

    @Test
    public void findTest() {

        PageRequest pageRequest = PageRequest.of(0, 1);//page from 0 1 2 3...
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(OPENID, pageRequest);
        System.out.println(orderMasterPage.getContent());
        System.out.println("---------" + orderMasterPage.getTotalElements());
        Assert.assertNotEquals(0, orderMasterPage.getTotalElements());

    }


    @Test
    public void getByIdTest() {
        OrderMaster orderMaster = orderMasterRepository.getById("1643536265647474464");

        System.out.println("order master :" + orderMaster.getOrderId());
        System.out.println("order master :" + orderMaster.getBuyerOpenid());
        System.out.println("order master :" + orderMaster.getBuyerAddress());
        System.out.println("order master :" + orderMaster.getBuyerPhone());
        Assert.assertNotNull(orderMaster);
    }

    @Test
    public void getOneTest() {
        OrderMaster orderMaster = orderMasterRepository.getOne("1643536265647474464");


        System.out.println("order master :" + orderMaster.getOrderId());
        System.out.println("order master :" + orderMaster.getBuyerOpenid());
        System.out.println("order master :" + orderMaster.getBuyerAddress());
        System.out.println("order master :" + orderMaster.getBuyerPhone());
        Assert.assertNotNull(orderMaster);
    }

    @Test
    public void findOneTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456789");
        Example<OrderMaster> example = Example.of(orderMaster);
        orderMaster = orderMasterRepository.findOne(example).get();

        System.out.println("order master :" + orderMaster.getOrderId());
        System.out.println("order master :" + orderMaster.getBuyerOpenid());
        System.out.println("order master :" + orderMaster.getBuyerAddress());
        System.out.println("order master :" + orderMaster.getBuyerPhone());
        Assert.assertNotNull(orderMaster);
    }

    @Test
    public void findByIdTest() {

        Optional<OrderMaster> o = orderMasterRepository.findById("1643593214844192620111");
        OrderMaster orderMaster = null;
        if (o.isPresent()) {
            orderMaster = o.get();
            System.out.println("order master :" + orderMaster.getOrderId());
            System.out.println("order master :" + orderMaster.getBuyerOpenid());
            System.out.println("order master :" + orderMaster.getBuyerAddress());
            System.out.println("order master :" + orderMaster.getBuyerPhone());

        }
        if (o.isEmpty()) {
            System.out.println("========================");
        }
        if (null==orderMaster){
            System.out.println("========================null");
        }

//        Assert.assertNotNull(orderMaster);

    }
}