package com.example.order.service;

import com.example.order.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderDTO findOne(String orderId);

    OrderDTO createOne(OrderDTO orderDTO);

    OrderDTO cancel(OrderDTO orderDTO);

    OrderDTO finish(OrderDTO orderDTO);

    OrderDTO paid(OrderDTO orderDTO);

    Page<OrderDTO> findList(String buyerOpendId, Pageable pageable);

    Page<OrderDTO> findList(Pageable pageable);


}
