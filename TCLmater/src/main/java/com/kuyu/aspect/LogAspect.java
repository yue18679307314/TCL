package com.kuyu.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.ResponseFacade;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.kuyu.filter.XssHttpServletRequestWrapper;

import io.swagger.annotations.ApiOperation;

/**
 * @Author jt_L
 * @Date 2017/9/29
 * @Description
 */
@Aspect
@Component
public class LogAspect {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final static String LOG_METHOD = "log()";

    ThreadLocal<Long> startTime = new ThreadLocal<>();
    ThreadLocal<Long> endTime = new ThreadLocal<>();

    @Pointcut("@within(com.kuyu.annotation.AOP_Controller_LOG)")
    public void log(){
    }

    @Around(LOG_METHOD)
    public Object loggingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();//获取attribute
        HttpServletRequest req = attributes.getRequest();
        log.info("请求的URL:{}",req.getRequestURL());
        log.info("请求的IP:{}",req.getRemoteAddr());
        String method = joinPoint.getSignature().getName();
        log.info("请求的方式:{}",method);
        log.info("被执行的类:{}",joinPoint.getSignature().getDeclaringTypeName());
        log.info("被执行的方法名:{}",joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (null == arg || arg instanceof MultipartHttpServletRequest || arg instanceof XssHttpServletRequestWrapper
            		|| arg instanceof HttpRequest ||arg instanceof HttpResponse || arg instanceof ResponseFacade){
                continue;
            }
            Class<?> T = arg.getClass();
            log.info("\n方法的参数类型:{}\n参数值:\n{}",T.getTypeName(),JSON.toJSONString(arg));
        }
        Class joinPointClass = joinPoint.getSignature().getDeclaringType();
        Method[] methods = joinPointClass.getMethods();
        for (Method m : methods){
            if (m.getName().equals(method)){
                ApiOperation apiOperation = m.getAnnotation(ApiOperation.class);
                if (null != apiOperation){
                    log.info("被执行方法的功能:{}",apiOperation.value());
                }
                break;
            }
        }
        Object result = null;
        log.info("!!!!!!方法正在开始执行中!!!!!!");
        startTime.set(System.currentTimeMillis());
        try{
            result = joinPoint.proceed();
        }finally{
        }
        endTime.set(System.currentTimeMillis());
        log.info("!!!!!!方法正常执行结束!!!!!!");
        log.info("当前执行方法所耗费的时间:{}毫秒",(endTime.get()-startTime.get()));
        log.info("执行方法之后的返回JSON数据:{}",JSON.toJSONString(result));

        return result;
    }

}
