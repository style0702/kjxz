package com.kjxz.rabbitmq.constant;

public interface RabbitmqConstant  {
    //rabbit信息唯一id
    String RABBIT = "rabbit_";

    //rabbitmq
    interface rabbit{
        //rabbit信息
        String REDIS_MESSAGE="redis_message";
        //rabbit信息发送状态
        String REDIS_SEND_STATUS="redis_send_status";
        //rabbit信息发送次数
        String REDIS_SEND_COUNT="redis_send_count";
        //rabbit信息发送时间
        String REDIS_SEND_TIMEOUT="redis_send_timeout";
        //rabbit信息消费状态
        String REDIS_CONSUMER_STATUS="redis_consumer_status";
        //rabbit信息消费次数
        String REDIS_CONSUMER_COUNT="redis_consumer_count";
    }
}
