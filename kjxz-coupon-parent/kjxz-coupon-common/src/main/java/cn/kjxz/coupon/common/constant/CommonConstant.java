package cn.kjxz.coupon.common.constant;

public interface CommonConstant {
    String REDIS_SEPARATOR="_";
    interface CouponConstant{
        //根据商户id+优惠券等级 redis key
        String REDIS_COUPON_MERCHANTID_LEVEL="redis_coupon_merchantid_level_";
        //存放当前模板id所有的优惠券码的redis 后面跟模板id
        String REDIS_COUPON_FOR_CODE="redis_coupon_for_code_";
        //商家对应优惠券模板存入redis key 后面加当前商家的id
        String REDIS_MERCHANTID_FOR_COUPON="redis_merchantid_for_coupon_";
        //模板id 对应模板信息  后面加模板id
        String REDSI_COUPON_INFO="redsi_coupon_info_";
        //模板信息中的库存id
        String REDSI_COUPON_COUNT="redsi_coupon_count_";
    }

    interface KafkaKey{
        /**
         * 模板微服务发送消息   分发微服务是消费者  消息是根据模板id与商家id完成优惠券码的传递
         */
        String COUPONID_MERCHANTID_TO_CODE="couponid_merchantid_to_code_";
    }
}
