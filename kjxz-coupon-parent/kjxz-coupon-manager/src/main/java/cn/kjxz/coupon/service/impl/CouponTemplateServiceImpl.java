package cn.kjxz.coupon.service.impl;

import cn.kjxz.coupon.common.constant.CommonConstant;
import cn.kjxz.coupon.common.util.FutureUtils;
import cn.kjxz.coupon.common.util.IdUtils;
import cn.kjxz.coupon.dao.CouponTemplateMapper;
import cn.kjxz.coupon.entity.CouponTemplate;
import cn.kjxz.coupon.entity.param.CouponTemplateParam;
import cn.kjxz.coupon.service.CouponTemplateService;
import cn.kjxz.coupon.util.CouponCodeUtil;

import cn.kjxz.global.kafka.KafkaSender;
import cn.kjxz.global.util.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {
    @Autowired
    //@SuppressWarnings("all")
    private CouponTemplateMapper couponTemplateMapper;
    @Autowired
    private IdUtils idUtils;
    @Autowired
    private CouponCodeUtil couponCodeUtil;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public void add(CouponTemplateParam couponTemplateParam) {

        CouponTemplate build = CouponTemplate.builder().cAmount(new BigDecimal(String.valueOf(couponTemplateParam.getcAmount())))
                .cAreaId(couponTemplateParam.getcMerchantId())
                .cAmountLimit(new BigDecimal(String.valueOf(couponTemplateParam.getcAmountLimit())))
                .cCategory(couponTemplateParam.getcCategory())
                .cConsumeStatus(couponTemplateParam.getcConsumeStatus())
                .cCount(couponTemplateParam.getcCount())
                .cDesc(couponTemplateParam.getcDesc())
                .cExpireDay(couponTemplateParam.getcExpireDay())
                .cExpireType(couponTemplateParam.getcExpireType())
                .cLimiter(couponTemplateParam.getcLimiter())
                //暂时
                .cLinkUrl(couponTemplateParam.getcMerchantId())
                .cMerchantId(couponTemplateParam.getcMerchantId())
                .cMerchantName(couponTemplateParam.getcMerchantName())
                .cProductLine(couponTemplateParam.getcProductLine())
                .build();
        //如果要分库分表 根据ID
        String couponId = FutureUtils.get(idUtils.getId(couponTemplateParam.getcMerchantId()));
        build.setCId(couponId);
        //todo logo linkUrl
        //todo redis
        //优惠券码
        List<String> list = couponCodeUtil.generateCode(couponTemplateParam.getcCount(), couponTemplateParam.getcMerchantId());
        //发送kafka到其它微服务 进行数据库的插入 list+商家id+模板id  <商家id，<模板id，优惠券码>>
        Map<String, String> objectObjectHashMap = new HashMap<>();
        HashMap<String, String> objectObjectHashMap1 = Maps.<String, String>newHashMap();
        try {
            objectObjectHashMap1.put(couponId,objectMapper.writeValueAsString(list));
            String s = objectMapper.writeValueAsString(objectObjectHashMap1);
            objectObjectHashMap.put(couponTemplateParam.getcMerchantId(),s);
            kafkaSender.sendMessage(CommonConstant.KafkaKey.COUPONID_MERCHANTID_TO_CODE,objectMapper.writeValueAsString(objectObjectHashMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //todo 调用c_coupon_code_user
        //todo :logo linkUrl  FastDfs
        //todo redis
        couponTemplateMapper.insertSelective(build);
        //放入redis  商家id 模板id
        //根据价格等级缓存到redis
       double amount = couponTemplateParam.getcAmount();
       if (amount>=1&&amount<=50){
           //根据优惠等级存入redis
            redisUtils.sset(CommonConstant.CouponConstant.REDIS_COUPON_MERCHANTID_LEVEL+couponTemplateParam.getcMerchantId()+CommonConstant.REDIS_SEPARATOR+1);
       }
       //当前商家增加一个优惠券模板 放入redis
        redisUtils.sset(CommonConstant.CouponConstant.REDIS_MERCHANTID_FOR_COUPON+couponTemplateParam.getcMerchantId(),couponId);
       //当前模板的信息放入redis
        //数量
        redisUtils.hset(CommonConstant.CouponConstant.REDSI_COUPON_INFO+couponId,CommonConstant.CouponConstant.REDSI_COUPON_COUNT,String.valueOf(couponTemplateParam.getcCount()));
        //详细信息
        try {
            redisUtils.hset(CommonConstant.CouponConstant.REDSI_COUPON_INFO+couponId,CommonConstant.CouponConstant.REDSI_COUPON_INFO,objectMapper.writeValueAsString(build));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
