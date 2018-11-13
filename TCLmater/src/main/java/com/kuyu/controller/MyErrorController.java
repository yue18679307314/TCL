package com.kuyu.controller;

import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.vo.ResultVo;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jt_L
 * @Date 2017/11/3
 * @Description 404异常处理
 */
@RestController
public class MyErrorController implements ErrorController{

    @RequestMapping(value = CommonConstants.ERROR_CODE)
    public void error(){
        throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_FOUNT_404_CODE));
    }

    @Override
    public String getErrorPath(){
        return CommonConstants.ERROR_CODE;
    }
}
