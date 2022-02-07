package com.example.order.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

//https://cdmana.com/2021/10/20211022020947538G.html

//official wiki https://github.com/apache/rocketmq-spring/wiki
@Service
@RocketMQMessageListener(topic = "topic-a", consumerGroup = "string-consumer")
@Slf4j
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("mq consumer msg={}", s);
    }
}
