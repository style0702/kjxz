package cn.kjxz.global.kafka;

import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSender {
    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendMessage(String topic,String message){
        kafkaTemplate.send(topic,message);
    }
}
