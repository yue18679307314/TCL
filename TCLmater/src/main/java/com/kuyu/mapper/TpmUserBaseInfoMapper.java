package com.kuyu.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.vo.ProvinceVo;
import com.kuyu.vo.VerifyBankAccountVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author tang_zhen
 * @Date 2017/9/25
 * @Description
 */

@Mapper
public interface TpmUserBaseInfoMapper extends BaseMapper<TpmUserBaseInfoModel> {
      void insertUserBaseInfo( TpmUserBaseInfoModel tpmUserBaseInfoModel);

      void updateUserBaseInfo(TpmUserBaseInfoModel tpmUserBaseInfoModel);

      int selectCountByOpenid(String openid);
      
      public String getOpenName(String openid);
      
      TpmUserBaseInfoModel getUserBaseInfo(String openid);

      String getHeadImage(String openid);

      List<ProvinceVo> getProvinceList();

      List<TpmUserBaseInfoModel> getUserBaseInfoList(TpmUserBaseInfoModel tpmUserBaseInfoModel,Page<TpmUserBaseInfoModel> page);

      String getNickName(String openid);

      void insertBankAccountVerify(VerifyBankAccountVo verifyBankAccountVo);

      String getWxSex(String openid);

      List<TpmUserBaseInfoModel> financeGetUserBaseInfoList(TpmUserBaseInfoModel tpmUserBaseInfoModel, Page<TpmUserBaseInfoModel>page);

      List<TpmUserBaseInfoModel> activityOrProjectGetUserBaseInfoList(TpmUserBaseInfoModel tpmUserBaseInfoModel, Page<TpmUserBaseInfoModel>page);

    List<String> getCityList(String provinceId);

      List<TpmUserBaseInfoModel> companyAdminGetUserBaseInfoList(TpmUserBaseInfoModel tpmUserBaseInfoModel, Page<TpmUserBaseInfoModel> page);

      List<String> getUserDept(String openid);
}
