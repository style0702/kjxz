package com.kjxz.rabbitmq.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyProducerTest {

    @Autowired
    private MyProducer myProducer;

    @Test
    public void send(){
        Map<String, Object> map = new HashMap<>();
        map.put("sendTime",new Date());
        String message="hell springboot and rabbit";
        myProducer.send(message,map);
    }
}