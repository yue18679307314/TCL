package com.kuyu.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.vo.BankInfoVo;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.VerifyBankAccountVo;

/**
 * Created by tang_zhen on 2017/9/21.
 */

public interface TpmUserAccountInfoService extends IService<TpmUserBaseInfoModel> {
    /**
     * 插入或者更新银行卡信息
     * @param tpmUserBaseInfoModel
     * @return ResultVo
     * @throws Exception
     */
    ResultVo updateAccountData(Integer personType,TpmUserBaseInfoModel tpmUserBaseInfoModel) throws Exception;
    /**
     * 返回银行列表
     * @param
     * @return
     */
    public List<String>  getBankList();
    /**
     * 财务共享审批银行账号后返回信息
     * @param
     * @return
     */
    FinancialResultVo insertBankAccountVerify(VerifyBankAccountVo verifyBankAccountVo) throws Exception;

    ResultVo getTheBankAccountVerify(String openid);
    
    ResultVo outputBankInfo(BankInfoVo bankInfoVo,LoginUserInfo userInfo) throws Exception;
    
    String getBankInfoUrl(List<String> list,String pdfOrxls,LoginUserInfo userInfo) throws Exception;
}
