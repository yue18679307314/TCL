package com.kuyu.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kuyu.exception.ParamException;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.ProvinceVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.VerifyBankAccountVo;

import java.util.List;

/**
 * @Author tang_zhen
 * @Date 2017/9/25
 * @Description
 */
public interface TpmUserBaseInfoService extends IService<TpmUserBaseInfoModel>{
    /**
     * 将基本信息插入数据库
     * @param tpmUserBaseInfoModel
     * @return ResultVo
     * @throws Exception
     */
    ResultVo insertData(TpmUserBaseInfoModel tpmUserBaseInfoModel) throws Exception;
    
    public void update(TpmUserBaseInfoModel tpmUserBaseInfoModel);
    
    public void bundingInsertData(TpmUserBaseInfoModel tpmUserBaseInfoModel);


    /**
     * 根据openid查询姓名name
     * @param openid
     * @return String
     */
    public String getOpenName(String openid)throws Exception;
    
    public TpmUserBaseInfoModel getUserBaseInfoOriginal(String openid);
    
    /**
     * 根据openid查询
     * @param openid
     * @return
     */
    public TpmUserBaseInfoModel getUserBaseInfo(String openid)throws Exception;

    /**
     * 查询省列表
     * @return
     */
    List<ProvinceVo> getProvinceList()throws Exception;

    /**
     * 查询所有临促人员信息
     * @return
     */
    Page<TpmUserBaseInfoModel> getUserBaseInfoList(LoginUserInfo loginUserInfo, TpmUserBaseInfoModel tpmUserBaseInfoModel)throws Exception;

    List<String> getCityList(String provinceId);
}
