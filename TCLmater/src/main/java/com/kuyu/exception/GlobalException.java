package com.kuyu.exception;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.common.CommonConstants;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description 全局异常处理类
 */

@RestControllerAdvice
public class GlobalException {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //共享平台调用uri
    public static Set<String> sharePlatformUris = new HashSet<>();

    static {
        sharePlatformUris.add("/tpm/tpmUserBaseInfo/receiveInfoFromFinance");
        sharePlatformUris.add("/tpm/tpmRepayment/insert");
        sharePlatformUris.add("/tpm/deptEmployee/doTpmDept");
        sharePlatformUris.add("/tpm/deptEmployee/doTpmEmployee");
    }


    @ExceptionHandler(Exception.class)
    public Object throwException(Exception e,HttpServletRequest req)throws Exception{
        ResultVo vo = new ResultVo();
        vo.setCode(CommonConstants.SYSTEM_ERROR_CODE);
        vo.setMsg(CommonConstants.SYSTEM_ERROR_MSG);
        log.info(e.getMessage(),e);
        String requestURI = req.getRequestURI();
        log.info("异常url网址为：{}", req.getRequestURL());
        boolean flag = false;
        if (requestURI.contains("/out/")) flag = true;
        if (!flag){
            for (String uri : sharePlatformUris){
                if (requestURI.startsWith(uri)) flag = true;
            }
        }

        if (e instanceof ParamException){
            ParamException paramException = (ParamException)e;
            vo = paramException.getVo();
        }else if (e instanceof MybatisPlusException){
            vo.setMsg(CommonConstants.SYSTEM_ERROR_MSG);
        }
        if (flag){
            vo.setCode(CommonConstants.SHARE_PLATFORM_ERROR_CODE);
            if (StringUtils.isEmpty(vo.getMsg())) vo.setMsg(CommonConstants.SYSTEM_ERROR_MSG);
            String jsonResultVo = StringUtil.toJsonResultVo(vo);
            log.info("共享平台异常json：{}", jsonResultVo);
            return jsonResultVo;
        }
        log.info("异常json：{}", JSON.toJSONString(vo));
        return vo;
    }



}
