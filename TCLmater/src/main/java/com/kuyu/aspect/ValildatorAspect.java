package com.kuyu.aspect;

import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.util.ResultVoUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author jt_L
 * @Date 2017/10/27
 * @Description
 */
@Aspect
@Component
public class ValildatorAspect{
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator methodValidator = factory.getValidator().forExecutables();
    private final Validator beanValidator = factory.getValidator();

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object [] params){
        return methodValidator.validateParameters(obj, method, params);
    }

    private <T> Set<ConstraintViolation<T>> validBeanParams(T bean) {
        return beanValidator.validate(bean);
    }

    @Pointcut("execution(* com.kuyu.controller.*.*(..))")
    public void validatorControllerBefore(){}

    /* * 通过连接点切入 */
    @Before("validatorControllerBefore()")
    public void validateBefore(JoinPoint point) throws Exception{
        //  获得切入目标对象
        Object target = point.getThis();
        // 获得切入方法参数
        Object [] args = point.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature)point.getSignature()).getMethod();

        // 校验以基本数据类型 为方法参数的
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);

        Iterator<ConstraintViolation<Object>> violationIterator = validResult.iterator();
        while (violationIterator.hasNext()) {
            String message = violationIterator.next().getMessage();
            throw new ParamException(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE,message));
        }

        // 校验以java bean对象 为方法参数的
        for (Object bean : args) {
            if (null != bean) {
                validResult = validBeanParams(bean);
                violationIterator = validResult.iterator();
                while (violationIterator.hasNext()) {
                    String message = violationIterator.next().getMessage();
                    throw new ParamException(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE,message));
                }
            }
        }
    }
}
