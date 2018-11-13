package com.kuyu.util;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.common.CommonConstants;
import com.kuyu.vo.ResultVo;

import java.util.function.Predicate;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description 返回resultVo对象工具类
 */
public class ResultVoUtils {

    public static ResultVo paramException(String msg, String... fieldNames) {
        ResultVo vo = new ResultVo();
        vo.setCode(CommonConstants.ERROR_DEFAULT_CODE);

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < fieldNames.length; i++) {
            if (i==0){
                stringBuffer.append(fieldNames[i]);
                continue;
            }
            stringBuffer.append(","+fieldNames[i]);

        }
        vo.setMsg(stringBuffer+CommonConstants.PARAM_IS_ERROR+msg);
        return vo;
    }

    public static ResultVo toSharePlatform(String code,String msg) {
        ResultVo vo = new ResultVo();
        vo.setCode(code);
        if (StringUtils.isEmpty(msg)){
            if (code.equals(CommonConstants.SHARE_PLATFORM_FINISH_CODE)){
                vo.setMsg(CommonConstants.SHARE_PLATFORM_FINISH_MSG);
            }else {
                vo.setMsg(CommonConstants.SHARE_PLATFORM_ERROR_MSG);
            }
        }else {
            vo.setMsg(msg);
        }
        return vo;
    }


    public static ResultVo defaultException(String msg) {
        ResultVo vo = new ResultVo();
        vo.setCode(CommonConstants.SYSTEM_ERROR_CODE);
        vo.setMsg(msg);
        return vo;
    }

    /**
     * 返回操作成功的信息
     * @param msg
     * @return
     */
    public static ResultVo success(String msg) {
        ResultVo vo = ResultVo.get(ResultVo.SUCCESS);
        if (StringUtils.isEmpty(msg)){
            msg = vo.getMsg();
        }
        vo.setMsg(msg);
        return vo;
    }

    /**
     * 返回操作失败的信息
     * @param msg
     * @return
     */
    public static ResultVo fail(String msg){
        ResultVo vo = ResultVo.get(ResultVo.FAIL);
        if (StringUtils.isEmpty(msg)){
            msg = vo.getMsg();
        }
        vo.setMsg(msg);
        return vo;
    }


}
