package com.example.order.service.impl;

import com.example.order.config.VchatAccountConfig;
import com.example.order.dto.OrderDTO;
import com.example.order.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private VchatAccountConfig vchatAccountConfig;


    @Override
    public void orderStatus(OrderDTO orderDTO) {//vchat mp test
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(vchatAccountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());//openId of mp
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("sec", "oh easy lenny"),
                new WxMpTemplateData("key1", orderDTO.getOrderStatusEnum().getMessage())

        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("push msg err e={}", e);
        }

    }
}
