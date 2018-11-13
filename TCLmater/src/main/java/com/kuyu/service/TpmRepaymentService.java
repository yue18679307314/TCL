package com.kuyu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmRepaymentModel;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.RepaymentManagementReturnVo;
import com.kuyu.vo.RepaymentManagementVo;
import com.kuyu.vo.RepaymentVo;
import com.kuyu.vo.ResultVo;

/**
 * @Author tang_zhen
 * @Date 2017/9/28
 * @Description
 */
public interface TpmRepaymentService extends IService<TpmRepaymentModel> {
    /**
     *插入报销单信息
     * @param tpmRepaymentModel
     * @return
     * @throws Exception
     */
    FinancialResultVo insertRepayment(TpmRepaymentModel tpmRepaymentModel) throws Exception;

    /**
     * 查询报销单的信息
     * @param repaymentVo
     * @param personType
     * @return
     */
    Page<TpmRepaymentModel> queryRepayment(RepaymentVo repaymentVo, LoginUserInfo personType);
    
    Page<RepaymentManagementReturnVo> queryRepaymentManagementList(RepaymentManagementVo rmv, LoginUserInfo userInfo) throws Exception;
    
    RepaymentManagementReturnVo queryRepaymentManagementDetails(String uuid) throws Exception;
    
    ResultVo importAttendence(RepaymentManagementVo rmv,LoginUserInfo userInfo) throws Exception;
    
    ResultVo queryAttendences(RepaymentManagementVo rmv,LoginUserInfo userInfo) throws Exception;
    
    String getDownloadUrl(String uuid,LoginUserInfo userInfo) throws Exception;
}
