package com.kuyu.annotation;

import java.lang.annotation.*;

/**
 * @Author jt_L
 * @Date 2017/10/16
 * @Description AOP拦截日志
 */
@Target({ElementType.METHOD})//定义在类方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AOP_Service_LOG{
    /**
     * 描述
     * @return
     */
    String value() default "";
}
