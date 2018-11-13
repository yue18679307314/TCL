package com.kuyu.exception;


import com.kuyu.common.CommonConstants;
import com.kuyu.vo.ResultVo;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description 参数异常
 */
public class ParamException extends RuntimeException {

    private ResultVo vo;

    public ParamException(ResultVo vo) {
        super(vo.getMsg());
        this.vo = vo;
    }

    public ParamException(String msg) {
        super(msg);
        vo = new ResultVo();
        vo.setCode(CommonConstants.ERROR_DEFAULT_CODE);
        vo.setMsg(msg);
    }

    public ResultVo getVo() {
        return vo;
    }

    public void setVo(ResultVo vo) {
        this.vo = vo;
    }


}
