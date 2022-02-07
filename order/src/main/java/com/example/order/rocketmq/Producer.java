package com.example.order.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class Producer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage() throws Exception {
        sendMessageAsync();
    }

    public void sendMessageAsync() throws Exception {
        rocketMQTemplate.asyncSend("topic-a", "testing message", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("mq send succ sendResult={}", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("mq send failed exception={}", throwable.getMessage());
            }
        });

    }
}
