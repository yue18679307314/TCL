package com.kuyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.vo.BankInfoVo;
import com.kuyu.vo.VerifyBankAccountVo;

/**
 * Created by tang_zhen on 2017/9/21.
 */
@Mapper
public interface TpmUserAccountInfoMapper extends BaseMapper<TpmUserBaseInfoModel>{
     void updateAccountInfo(TpmUserBaseInfoModel tpmUserBaseInfoModel);

     String selectAccountInfo(String openid);

     TpmUserBaseInfoModel selectAllAccountInfo(String openid);

     String selectCreateTime(String openid);

     String getUserCode();

     String getDepartCode();

     String getTitle();

     List<String> getBankList();

     int updateBankAccountVerify(VerifyBankAccountVo verifyBankAccountVo);

     TpmUserBaseInfoModel getUserBaseInfoByReqfsscbill(VerifyBankAccountVo verifyBankAccountVo);

     String getBankAccountVerifyInfo(String openid);

     void updateUserStatementPaystate(String openid);

    String getNameByOpenid(String openid);
     
     List<TpmUserBaseInfoModel> outputBankInfo(BankInfoVo bankInfoVo,Page<TpmUserBaseInfoModel> page);
     
     List<TpmUserBaseInfoModel> queryBankInfoByOpenids(List<String> list);
}
