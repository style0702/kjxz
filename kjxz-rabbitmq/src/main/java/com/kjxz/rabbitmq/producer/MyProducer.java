package com.kjxz.rabbitmq.producer;

import cn.kjxz.global.util.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjxz.rabbitmq.constant.RabbitmqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MyProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    ObjectMapper objectMapper;


    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            log.info("发送的broker的结果：{}",b);
            log.info("消息的id：{}",correlationData.getId());
            String rabbitId= RabbitmqConstant.RABBIT+correlationData.getId();
            try {
                String message = objectMapper.writeValueAsString(correlationData);
                redisUtils.hset(rabbitId,RabbitmqConstant.rabbit.REDIS_MESSAGE,message);
                redisUtils.hset(rabbitId,RabbitmqConstant.rabbit.REDIS_SEND_STATUS,String.valueOf(0));
                redisUtils.hset(rabbitId,RabbitmqConstant.rabbit.REDIS_SEND_COUNT,String.valueOf(1));
                redisUtils.hset(rabbitId,RabbitmqConstant.rabbit.REDIS_SEND_TIMEOUT,String.valueOf(System.currentTimeMillis()*5*1000));
                redisUtils.hset(rabbitId,RabbitmqConstant.rabbit.REDIS_CONSUMER_STATUS,String.valueOf(0));
                redisUtils.hset(rabbitId,RabbitmqConstant.rabbit.REDIS_CONSUMER_COUNT,String.valueOf(0));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            if(!b){
                log.info("消息发送失败");
            }

        }
    };
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        /**
         * @param message   发送的消息
         * @param i 错误码
         * @param s 错误文本信息
         * @param s1  发送的交换机
         * @param s2 发送的路由键
         */
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {

        }
    };

    public void send(String message, Map<String,Object> properties){
        MessageHeaders messageHeaders = new MessageHeaders(properties);
        org.springframework.messaging.Message<String> message1 = MessageBuilder.createMessage(message, messageHeaders);

        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        CorrelationData correlationData = new CorrelationData("123456");
        rabbitTemplate.convertAndSend("springboot.exchange","springboot.rout",message1,correlationData);

    }

}
