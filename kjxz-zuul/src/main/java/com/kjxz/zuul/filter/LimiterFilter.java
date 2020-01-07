package com.kjxz.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;
@Component
@Slf4j
public class LimiterFilter extends AbstractPreZuulFilter {
    //令牌桶 封装的Semaphore(信号量) 限制线程数量
    RateLimiter rateLimiter = RateLimiter.create(10);
    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public Object run() throws ZuulException {
        requestContext.set("startTime",System.currentTimeMillis());
        if (rateLimiter.tryAcquire()){
            log.info("通过令牌桶获得资格");
            //说明获得访问资格
            return  success();
        }
        return fail(701,"网络正忙");
    }
}
