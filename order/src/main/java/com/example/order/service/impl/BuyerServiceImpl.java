package com.example.order.service.impl;

import com.example.order.dto.OrderDTO;
import com.example.order.enums.ResultEnum;
import com.example.order.exception.SellException;
import com.example.order.service.BuyerService;
import com.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return checkOrder(openId, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO = checkOrder(openId, orderId);
        if (null == orderDTO) {
            log.error("check order not exist orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrder(String openId, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (null == orderDTO) {
            log.error("check order not exist orderId={}", orderId);
            return null;
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
//        if (!orderDTO.getBuyerOpenid().equals(openId)) {
//        if (!openId.equalsIgnoreCase(orderDTO.getBuyerOpenid())) {
        if (!openId.equalsIgnoreCase(orderDTO.getBuyerOpenid())) {
            log.error("===={}", openId.equalsIgnoreCase(orderDTO.getBuyerOpenid()));
            log.error("===={}", openId.equalsIgnoreCase("leverwwz"));
            log.error("===={}", orderDTO.getBuyerOpenid().equalsIgnoreCase("leverwwz"));
            log.error("===={}", "leverwwz".equalsIgnoreCase(orderDTO.getBuyerOpenid()));
            log.error("order owner diff, openId={}, orderDTO{}", openId, orderDTO.getBuyerOpenid());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        return orderDTO;

    }

}
