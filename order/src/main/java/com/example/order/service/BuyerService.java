package com.example.order.service;


import com.example.order.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String openId, String orderId);

    OrderDTO cancelOrder(String openId, String orderId);
}
