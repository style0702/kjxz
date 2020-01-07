package cn.kjxz.global.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {
    //配置监听的主体，groupId和配置文件中的保持一致
    @KafkaListener(topics = {"kjxz"},groupId = "test-consumer-group")
    public void listen(ConsumerRecord<?,?> record){
        System.out.println("收到kafka生产者发送的消息"+record.value());
    }
}
