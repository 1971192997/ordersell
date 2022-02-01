package com.example.order.controller;

import com.example.order.VO.ResultVO;
import com.example.order.converter.OrderForm2OrderDTOConverter;
import com.example.order.dto.OrderDTO;
import com.example.order.enums.ResultEnum;
import com.example.order.exception.SellException;
import com.example.order.form.OrderForm;
import com.example.order.service.BuyerService;
import com.example.order.service.OrderService;
import com.example.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    @RequestMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("create order param err, orderFrom={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("create order detail empty orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        OrderDTO res = orderService.createOne(orderDTO);
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", res.getOrderId());

        return ResultVOUtil.success(map);
    }

    @RequestMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if (StringUtils.isEmpty(openId)) {
            log.error("list openid null");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openId, pageRequest);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    @RequestMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openId") String openId, @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openId, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    @RequestMapping("/cancel")
    public ResultVO cancel(@RequestParam("openId") String openId, @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = buyerService.cancelOrder(openId, orderId);

        return ResultVOUtil.success();
    }

}
