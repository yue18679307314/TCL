package com.kuyu.aspect;

import com.alibaba.fastjson.JSON;
import com.kuyu.annotation.AOP_Service_LOG;
import com.kuyu.filter.XssHttpServletRequestWrapper;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author jt_L
 * @Date 2017/9/29
 * @Description
 */
@Aspect
@Component
public class LogAspectService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final static String LOG_METHOD_SERVICE = "logService()";

    ThreadLocal<Long> startTime = new ThreadLocal<>();
    ThreadLocal<Long> endTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.kuyu.annotation.AOP_Service_LOG)")
    public void logService(){
    }

    @Around(LOG_METHOD_SERVICE)
    public Object loggingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("被执行的类:{}",className);
        log.info("被执行的方法名:{}", methodName);
        Object[] args = joinPoint.getArgs();
        Method[] methods = joinPoint.getSignature().getDeclaringType().getMethods();
        for (Method m : methods){
            if (m.getName().equals(methodName)){
                AOP_Service_LOG aopServiceLog = m.getAnnotation(AOP_Service_LOG.class);
                if (null != aopServiceLog){
                    log.info("被执行方法的功能:{}",aopServiceLog.value());
                }
                break;
            }
        }
        for (Object arg : args) {
            if (null == arg ){
                continue;
            }
            Class<?> T = arg.getClass();
            log.info("\n方法的参数类型:{}\n参数值:\n{}",T.getTypeName(),JSON.toJSONString(arg));
        }
        Object result = null;
        log.info("!!!!!!方法正在开始执行中!!!!!!");
        startTime.set(System.currentTimeMillis());
        try{
            result = joinPoint.proceed();
        }finally{
        }
        endTime.set(System.currentTimeMillis());
        log.info("!!!!!!方法{}正常执行结束!!!!!!",methodName);
        log.info("当前类{}的执行方法{}所耗费的时间:{}毫秒",className,methodName,(endTime.get()-startTime.get()));
        log.info("执行方法{}之后的返回JSON数据:{}",methodName,JSON.toJSONString(result));

        return result;
    }

}
