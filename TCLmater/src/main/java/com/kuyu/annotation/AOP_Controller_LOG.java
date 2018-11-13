package com.kuyu.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @Author jt_L
 * @Date 2017/10/15
 * @Description
 */
@Target(ElementType.TYPE)//定义在类接口枚举或者方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface AOP_Controller_LOG{
}
