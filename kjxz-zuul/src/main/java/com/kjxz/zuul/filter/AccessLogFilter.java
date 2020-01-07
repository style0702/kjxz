package com.kjxz.zuul.filter;

import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccessLogFilter extends AbstractPostZuulFilter {
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER-1;
    }

    @Override
    public Object run() throws ZuulException {
        long startTime = (long) requestContext.get("startTime");
        log.info("当前请求:{},执行的时间为：{}",requestContext.getRequest().getRequestURL(),System.currentTimeMillis()-startTime);
        return null;
    }
}
