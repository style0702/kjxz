package com.kjxz.zuul.filter;

import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginFilter extends AbstractPreZuulFilter {

    @Value("${name}")
    public String name;
    //定义执行循序
    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("登录的filter"+name);
        return fail(601,"登录失败");
    }
}
