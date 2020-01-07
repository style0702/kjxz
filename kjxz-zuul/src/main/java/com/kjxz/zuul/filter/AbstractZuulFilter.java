package com.kjxz.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public abstract class AbstractZuulFilter extends ZuulFilter {
    //所有的参数都可以通过这个类获得 底层是ThreadLocal +ConcurrentHashMap
    RequestContext requestContext;
    //定义一个key
    static final String NEXT="next";
    //所有自定义的filter都用这个判断
    @Override
    public boolean shouldFilter() {
        //初始化
         requestContext = RequestContext.getCurrentContext();
        //第一个filter 取不到这个值 默认值为true
        return requestContext.getBoolean(NEXT,true);
    }

    //定义失败的编号 失败信息
    public Object fail(int code,String message){
        requestContext.set(NEXT,false);
        //获得request
        HttpServletRequest request = requestContext.getRequest();
        log.info("当前请求失败：{}，url:{}",this.getClass().getName(),request.getRequestURI());
        //前面请求已经凉了,告诉zuul不需要去走了
        requestContext.setSendZuulResponse(false);
        //zuul不去请求微服务了那么久拿不到本来应该的返回值 ，zuul应该自己定义返回值
        requestContext.setResponseStatusCode(code);
        requestContext.getResponse().setContentType("application/json;charset=utf-8");
        requestContext.setResponseBody(message);
        return null;
    }

    public Object success(){
        requestContext.set(NEXT,true);
        return null;
    }
}
