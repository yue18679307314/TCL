package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.TpmUserBaseInfoMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.util.*;
import com.kuyu.vo.ProvinceVo;
import com.kuyu.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import sun.security.util.Length;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author tang_zhen
 * @Date 2017/9/25
 * @Description
 */
@Service
public class TpmUserBaseInfoServiceImpl extends ServiceImpl<TpmUserBaseInfoMapper, TpmUserBaseInfoModel>implements TpmUserBaseInfoService {

    @Autowired
    private TpmUserBaseInfoMapper tpmUserBaseInfoMapper;
    @Autowired
    private TpmOptLogsService tpmOptLogsService;

    /**
     * 插入或更新用户基本信息
     * @param tpmUserBaseInfoModel
     * @return ResultVo
     * @throws Exception
     */
    @Override
    public ResultVo insertData(TpmUserBaseInfoModel tpmUserBaseInfoModel)  throws Exception
    {

        //解密手机号和身份证号
        String mobile =tpmUserBaseInfoModel.getMobile();
        String idcard =tpmUserBaseInfoModel.getId_card();
        mobile = URLDecoder.decode(mobile, "UTF-8");
        idcard = URLDecoder.decode(idcard, "UTF-8");
        byte[] decodedPwd1 = RSAUtils.decryptByPrivateKey(mobile,RSAUtils.PRIVATE_KEY);
        byte[] decodedPwds2 = RSAUtils.decryptByPrivateKey(idcard,RSAUtils.PRIVATE_KEY);
        mobile = new String(decodedPwd1);
        idcard = new String(decodedPwds2);
        //解密身份证号
        tpmUserBaseInfoModel.setId_card(idcard);
        tpmUserBaseInfoModel.setMobile(mobile);

        //WeixinUserInfo
        if(StringUtil.isEmpty(tpmUserBaseInfoModel.getOpenid())
                ||tpmUserBaseInfoModel.getStatus()==null
                ||tpmUserBaseInfoModel.getStatus()==""
                ||Integer.valueOf(tpmUserBaseInfoModel.getStatus())>1
                ||Integer.valueOf(tpmUserBaseInfoModel.getStatus())<0
                ||StringUtil.isEmpty(tpmUserBaseInfoModel.getMobile())
                ||StringUtil.isEmpty(tpmUserBaseInfoModel.getId_card())
                ||StringUtil.isEmpty(tpmUserBaseInfoModel.getProvince())
                ||StringUtil.isEmpty(tpmUserBaseInfoModel.getCity())
                ||StringUtil.isEmpty(tpmUserBaseInfoModel.getDistrict())
                ||StringUtil.isEmpty(tpmUserBaseInfoModel.getAddress()))
        {
            return ResultVo.getByEnumCode(CommonConstants.PARAM_IS_ERROR_CODE);
           /* throw  new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));*/
        }
        if (tpmUserBaseInfoModel.getAddress()!=null)
        {
            if(tpmUserBaseInfoModel.getAddress().length()>100)
            {
                return ResultVo.getByEnumCode(CommonConstants.ADDRESS_TOO_LONG_CODE);
            }
        }
        if(tpmUserBaseInfoModel.getMobile().length()!=11)
        {
            return ResultVo.getByEnumCode(CommonConstants.MOBILE_TOO_SHORT_CODE);
        }
        if(tpmUserBaseInfoModel.getMobile().contains("*")||tpmUserBaseInfoModel.getId_card().contains("*"))
        {
            return ResultVo.getByEnumCode(CommonConstants.PARAM_IS_ERROR_CODE);
           /* throw  new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));*/
        }
        if(tpmUserBaseInfoModel.getName().length()>30)
        {
            return ResultVo.getByEnumCode(CommonConstants.NAME_TOO_LONG_CODE);
        }
        tpmUserBaseInfoModel = baseInfoCleanXss(tpmUserBaseInfoModel);
        //以openid为条件查询数据库中有没有数据，为0条，表明为新增用户，执行插入操作，设置修改时间为创建时间
        if(tpmUserBaseInfoMapper.selectCountByOpenid(tpmUserBaseInfoModel.getOpenid())==0)
        {
            tpmUserBaseInfoModel.setModify_time(tpmUserBaseInfoModel.getCreate_time());
            tpmUserBaseInfoMapper.insertUserBaseInfo(tpmUserBaseInfoModel);
            return  ResultVo.getDataWithSuccess(null);
        }
        else {
            TpmUserBaseInfoModel preTpmUserBaseInfoModel = tpmUserBaseInfoMapper.getUserBaseInfo(tpmUserBaseInfoModel.getOpenid());
            userBaseInfologs(tpmUserBaseInfoModel,preTpmUserBaseInfoModel);
            tpmUserBaseInfoModel.setModify_time(DateUtils.getSysDateTimeString());
             tpmUserBaseInfoMapper.updateUserBaseInfo(tpmUserBaseInfoModel);
            return   ResultVo.getDataWithSuccess(null);
        }

    }
    
    /**
     * 插入或更新用户基本信息
     * @param tpmUserBaseInfoModel
     * @return ResultVo
     * @throws Exception
     */
    @Override
    public void bundingInsertData(TpmUserBaseInfoModel tpmUserBaseInfoModel)
    {
         tpmUserBaseInfoModel.setCreate_time(DateUtils.getLongDateStr());
         tpmUserBaseInfoMapper.insertUserBaseInfo(tpmUserBaseInfoModel);
    }

    private TpmUserBaseInfoModel baseInfoCleanXss(TpmUserBaseInfoModel tpmUserBaseInfoModel) {
        tpmUserBaseInfoModel.setName(StringUtil.cleanXSS(tpmUserBaseInfoModel.getName()));
        tpmUserBaseInfoModel.setSex(StringUtil.cleanXSS(tpmUserBaseInfoModel.getSex()));
        tpmUserBaseInfoModel.setMobile(StringUtil.cleanXSS(tpmUserBaseInfoModel.getMobile()));
        tpmUserBaseInfoModel.setId_card(StringUtil.cleanXSS(tpmUserBaseInfoModel.getId_card()));
        tpmUserBaseInfoModel.setProvince(StringUtil.cleanXSS(tpmUserBaseInfoModel.getProvince()));
        tpmUserBaseInfoModel.setCity(StringUtil.cleanXSS(tpmUserBaseInfoModel.getCity()));
        tpmUserBaseInfoModel.setAddress(StringUtil.cleanXSS(tpmUserBaseInfoModel.getAddress()));
        return  tpmUserBaseInfoModel;
    }

    
    @Override
    public void update(TpmUserBaseInfoModel tpmUserBaseInfoModel){
    	tpmUserBaseInfoModel.updateById();
    }

    /**
     * 根据openid查询姓名name
     */
	@Override
	public String getOpenName(String openid) {
		return baseMapper.getOpenName(openid);
	}

	@Override
	public TpmUserBaseInfoModel getUserBaseInfoOriginal(String openid) {
        TpmUserBaseInfoModel tpmUserBaseInfoModel = baseMapper.getUserBaseInfo(openid);
        return tpmUserBaseInfoModel;
	}
	
    /**
     * 根据openid查询所有信息
     */
	@Override
	public TpmUserBaseInfoModel getUserBaseInfo(String openid) throws Exception {
        TpmUserBaseInfoModel tpmUserBaseInfoModel = baseMapper.getUserBaseInfo(openid);
        if(tpmUserBaseInfoModel==null)//表明进来系统的是新用户
        {
            TpmUserBaseInfoModel tpmUserBaseInfo = new TpmUserBaseInfoModel();
            tpmUserBaseInfo.setHeadImage(tpmUserBaseInfoMapper.getHeadImage(openid));
            tpmUserBaseInfo.setOpenid(openid);
            tpmUserBaseInfo.setWxName(baseMapper.getNickName(openid));
            String wxSex = tpmUserBaseInfoMapper.getWxSex(openid);
            if ("1".equals(wxSex)) {
                wxSex = "男";
            } else if("2".equals(wxSex)){
                wxSex = "女";
            }else
            {
                wxSex="";
            }
            tpmUserBaseInfo.setWxSex(wxSex);
            return tpmUserBaseInfo;
        }

        tpmUserBaseInfoModel.setHeadImage(baseMapper.getHeadImage(tpmUserBaseInfoModel.getOpenid()));
        tpmUserBaseInfoModel.setWxName(baseMapper.getNickName(tpmUserBaseInfoModel.getOpenid()));
        String wxSex = tpmUserBaseInfoMapper.getWxSex(tpmUserBaseInfoModel.getOpenid());
        if (wxSex.equals("1")) {
            wxSex = "男";
        } else if(wxSex.equals("2")){
            wxSex = "女";
        }else
        {
            wxSex="";
        }
        tpmUserBaseInfoModel.setWxSex(wxSex);
       /* if(tpmUserBaseInfoModel.getAccount_value().equals("暂未填写"))
        {
            tpmUserBaseInfoModel.setAccount_value("");
            tpmUserBaseInfoModel.setAccount_bank_name("");
            tpmUserBaseInfoModel.setAccount_name("");
            tpmUserBaseInfoModel.setOpen_branch("");
            tpmUserBaseInfoModel.setOpen_province("");
            tpmUserBaseInfoModel.setOpen_city("");
        }*/
       if(tpmUserBaseInfoModel.getAccount_value().length()<=4&&!("暂未填写".equals(tpmUserBaseInfoModel.getAccount_value())))
       {
           tpmUserBaseInfoModel.setAccount_value("*****");
       }
       if(tpmUserBaseInfoModel.getId_card().length()<18)
       {
           String idCard = tpmUserBaseInfoModel.getId_card();
           tpmUserBaseInfoModel.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
       }
		return bankAccountAndInfoUtil(tpmUserBaseInfoModel);
	}
    /**
     * 银行卡信息处理：只返回后四位；去掉“暂未填写”
     * 敏感信息处理：身份证号和电话号码处理
     */
    public  TpmUserBaseInfoModel  bankAccountAndInfoUtil(TpmUserBaseInfoModel tpmUserBaseInfoModel) throws Exception
    {

        if(tpmUserBaseInfoModel.getAccount_value().equals("暂未填写"))
        {
            tpmUserBaseInfoModel.setAccount_value("");
            tpmUserBaseInfoModel.setAccount_bank_name("");
//            tpmUserBaseInfoModel.setAccount_name("");
            tpmUserBaseInfoModel.setOpen_branch("");
            tpmUserBaseInfoModel.setOpen_province("");
            tpmUserBaseInfoModel.setOpen_city("");

            String mobilePhone = tpmUserBaseInfoModel.getMobile();
            byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobilePhone,RSAUtils.PUBLIC_KEY);
            BASE64Encoder enc=new BASE64Encoder();
            mobilePhone=enc.encode(decodedPwds2);
            tpmUserBaseInfoModel.setMobile(mobilePhone);

            tpmUserBaseInfoModel.setId_card(tpmUserBaseInfoModel.getId_card().substring(0,6)+"********"+tpmUserBaseInfoModel.getId_card().substring(14,18));
            return tpmUserBaseInfoModel;
        }else
        {
            String account = tpmUserBaseInfoModel.getAccount_value();
            String lastFour = account.substring(account.length()-4);
            StringBuffer stringBuffer = new StringBuffer();
            for(int i =0;i<account.length()-4;i++)
            {
                stringBuffer.append("*");
            }
            //System.out.println(stringBuffer.toString()+lastFour);
            tpmUserBaseInfoModel.setAccount_value(stringBuffer.toString()+lastFour);

            String mobilePhone = tpmUserBaseInfoModel.getMobile();
            byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobilePhone,RSAUtils.PUBLIC_KEY);
            BASE64Encoder enc=new BASE64Encoder();
            mobilePhone=enc.encode(decodedPwds2);
            tpmUserBaseInfoModel.setMobile(mobilePhone);

            tpmUserBaseInfoModel.setId_card(tpmUserBaseInfoModel.getId_card().substring(0,6)+"********"+tpmUserBaseInfoModel.getId_card().substring(14,18));
            return  tpmUserBaseInfoModel;
        }


    }

    /**
     * 返回省列表
     * @param
     */
    public List<ProvinceVo> getProvinceList()
    {
        List<ProvinceVo> provinceList =tpmUserBaseInfoMapper.getProvinceList();
        return  provinceList;
    }

    @Override
    public Page<TpmUserBaseInfoModel> getUserBaseInfoList(LoginUserInfo loginUserInfo,TpmUserBaseInfoModel tpmUserBaseInfoModel) throws Exception {

        Integer personType = CheckParamUtils.getPersonType(loginUserInfo);
        String personCode = loginUserInfo.getEmployeeModel().getPerson_code();
        tpmUserBaseInfoModel.setPersonCode(personCode);
        return getPage(personType,tpmUserBaseInfoModel);
}

    @Override
    public List<String> getCityList(String provinceId) {
        List<String> cityList = tpmUserBaseInfoMapper.getCityList(provinceId);
        return cityList;
    }

    public Page<TpmUserBaseInfoModel> getPage(Integer personType ,TpmUserBaseInfoModel tpmUserBaseInfoModel)throws Exception {
        tpmUserBaseInfoModel = (TpmUserBaseInfoModel) CheckParamUtils.trimWithObjectField(tpmUserBaseInfoModel);
        int number = 1;
        Page<TpmUserBaseInfoModel> page = new Page<>(tpmUserBaseInfoModel.getCurrent(), tpmUserBaseInfoModel.getSize());
        //去除空格，加上百分号
        String name = tpmUserBaseInfoModel.getName();
        if (StringUtils.isNotEmpty(name)) {
            tpmUserBaseInfoModel.setName(StringUtil.escapeStrWithLike(name));
        }
        String province = tpmUserBaseInfoModel.getProvince();
        if (StringUtil.isNotEmpty(province)) {
            tpmUserBaseInfoModel.setProvince(StringUtil.escapeStrWithLike(province));
        }
        String city = tpmUserBaseInfoModel.getCity();
        if (StringUtil.isNotEmpty(city)) {
            tpmUserBaseInfoModel.setCity(StringUtil.escapeStrWithLike(city));
        }

        if (personType==1) {//
            List<TpmUserBaseInfoModel> userBaseInfoModelList = tpmUserBaseInfoMapper.getUserBaseInfoList(tpmUserBaseInfoModel, page);
            for (TpmUserBaseInfoModel tpmUserBaseInfo : userBaseInfoModelList) {
                tpmUserBaseInfo.setNumber("" + number);
                tpmUserBaseInfo.setWxName(tpmUserBaseInfoMapper.getNickName(tpmUserBaseInfo.getOpenid()));
                String wxSex = tpmUserBaseInfoMapper.getWxSex(tpmUserBaseInfo.getOpenid());
                if (wxSex.equals("1")) {
                    wxSex = "男";
                } else if(wxSex.equals("2")){
                    wxSex = "女";
                }else
                {
                    wxSex="";
                }
                tpmUserBaseInfo.setWxSex(wxSex);
               /* if(tpmUserBaseInfo.getMobile().length()<11||tpmUserBaseInfo.getId_card().length()<18)
                {
                    String mobile = tpmUserBaseInfo.getMobile();
                    String idCard = tpmUserBaseInfo.getId_card();
                    tpmUserBaseInfo.setMobile(mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4));
                    tpmUserBaseInfo.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
                }
                tpmUserBaseInfo.setMobile(tpmUserBaseInfo.getMobile().substring(0,3)+"****"+tpmUserBaseInfo.getMobile().substring(7,11));
                tpmUserBaseInfo.setId_card(tpmUserBaseInfo.getId_card().substring(0,6)+"********"+tpmUserBaseInfo.getId_card().substring(14,18));*/
                if(tpmUserBaseInfo.getId_card().length()<18)
                {
                    String idCard = tpmUserBaseInfo.getId_card();
                    tpmUserBaseInfo.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
                }
                tpmUserBaseInfo.setId_card(tpmUserBaseInfo.getId_card().substring(0,6)+"********"+tpmUserBaseInfo.getId_card().substring(14,18));

               String mobilePhone = tpmUserBaseInfo.getMobile();
                byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobilePhone,RSAUtils.PUBLIC_KEY);
                BASE64Encoder enc=new BASE64Encoder();
                mobilePhone=enc.encode(decodedPwds2);
                tpmUserBaseInfo.setMobile(mobilePhone);
                number++;
                //银行卡审核的字段处理
                if (tpmUserBaseInfo.getBank_account_verify()==null||"".equals(tpmUserBaseInfo.getBank_account_verify()))
                {
                    tpmUserBaseInfo.setBank_account_verify("3");
                }
            }
            page.setRecords(userBaseInfoModelList);
            return page;
        }
        else if(personType==2)
        {
            tpmUserBaseInfoModel.setPersonType(personType);
            List<TpmUserBaseInfoModel> userBaseInfoModelList = tpmUserBaseInfoMapper.financeGetUserBaseInfoList(tpmUserBaseInfoModel, page);
            for (TpmUserBaseInfoModel tpmUserBaseInfo : userBaseInfoModelList) {
                tpmUserBaseInfo.setNumber("" + number);
                tpmUserBaseInfo.setWxName(tpmUserBaseInfoMapper.getNickName(tpmUserBaseInfo.getOpenid()));
                String wxSex = tpmUserBaseInfoMapper.getWxSex(tpmUserBaseInfo.getOpenid());
                if (wxSex.equals("1")) {
                    wxSex = "男";
                } else if(wxSex.equals("2")){
                    wxSex = "女";
                }else
                {
                    wxSex="";
                }
                tpmUserBaseInfo.setWxSex(wxSex);
               /* if(tpmUserBaseInfo.getMobile().length()<11||tpmUserBaseInfo.getId_card().length()<18)
                {
                    String mobile = tpmUserBaseInfo.getMobile();
                    String idCard = tpmUserBaseInfo.getId_card();
                    tpmUserBaseInfo.setMobile(mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4));
                    tpmUserBaseInfo.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
                }
                tpmUserBaseInfo.setMobile(tpmUserBaseInfo.getMobile().substring(0,3)+"****"+tpmUserBaseInfo.getMobile().substring(7,11));
                tpmUserBaseInfo.setId_card(tpmUserBaseInfo.getId_card().substring(0,6)+"********"+tpmUserBaseInfo.getId_card().substring(14,18));*/
                if(tpmUserBaseInfo.getId_card().length()<18)
                {
                    String idCard = tpmUserBaseInfo.getId_card();
                    tpmUserBaseInfo.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
                }
                tpmUserBaseInfo.setId_card(tpmUserBaseInfo.getId_card().substring(0,6)+"********"+tpmUserBaseInfo.getId_card().substring(14,18));

                String mobilePhone = tpmUserBaseInfo.getMobile();
                byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobilePhone,RSAUtils.PUBLIC_KEY);
                BASE64Encoder enc=new BASE64Encoder();
                mobilePhone=enc.encode(decodedPwds2);
                tpmUserBaseInfo.setMobile(mobilePhone);

                number++;
                //银行卡审核的字段处理
                if (tpmUserBaseInfo.getBank_account_verify()==null||"".equals(tpmUserBaseInfo.getBank_account_verify()))
                {
                    tpmUserBaseInfo.setBank_account_verify("3");
                }
            }
            page.setRecords(userBaseInfoModelList);
            return page;

        }else if(personType==3) {//3微信端登录的活动负责人
            page.setRecords(new ArrayList<TpmUserBaseInfoModel>());
            return page;

        }else if(personType==4){//4立项负责人或者活动负责人
            tpmUserBaseInfoModel.setPersonType(personType);
            List<TpmUserBaseInfoModel> userBaseInfoModelList = tpmUserBaseInfoMapper.activityOrProjectGetUserBaseInfoList(tpmUserBaseInfoModel, page);
            for (TpmUserBaseInfoModel tpmUserBaseInfo : userBaseInfoModelList) {
                tpmUserBaseInfo.setNumber("" + number);
                tpmUserBaseInfo.setWxName(tpmUserBaseInfoMapper.getNickName(tpmUserBaseInfo.getOpenid()));
                String wxSex = tpmUserBaseInfoMapper.getWxSex(tpmUserBaseInfo.getOpenid());
                if (wxSex.equals("1")) {
                    wxSex = "男";
                } else if(wxSex.equals("2")){
                    wxSex = "女";
                }else
                {
                    wxSex="";
                }
                tpmUserBaseInfo.setWxSex(wxSex);
                /*if(tpmUserBaseInfo.getMobile().length()<11||tpmUserBaseInfo.getId_card().length()<18)
                {
                    String mobile = tpmUserBaseInfo.getMobile();
                    String idCard = tpmUserBaseInfo.getId_card();
                    tpmUserBaseInfo.setMobile(mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4));
                    tpmUserBaseInfo.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
                }
                tpmUserBaseInfo.setMobile(tpmUserBaseInfo.getMobile().substring(0,3)+"****"+tpmUserBaseInfo.getMobile().substring(7,11));
                tpmUserBaseInfo.setId_card(tpmUserBaseInfo.getId_card().substring(0,6)+"********"+tpmUserBaseInfo.getId_card().substring(14,18));*/
                if(tpmUserBaseInfo.getId_card().length()<18)
                {
                    String idCard = tpmUserBaseInfo.getId_card();
                    tpmUserBaseInfo.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
                }
                tpmUserBaseInfo.setId_card(tpmUserBaseInfo.getId_card().substring(0,6)+"********"+tpmUserBaseInfo.getId_card().substring(14,18));

                String mobilePhone = tpmUserBaseInfo.getMobile();
                byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobilePhone,RSAUtils.PUBLIC_KEY);
                BASE64Encoder enc=new BASE64Encoder();
                mobilePhone=enc.encode(decodedPwds2);
                tpmUserBaseInfo.setMobile(mobilePhone);
                number++;
                //银行卡审核的字段处理
                if (tpmUserBaseInfo.getBank_account_verify()==null||"".equals(tpmUserBaseInfo.getBank_account_verify()))
                {
                    tpmUserBaseInfo.setBank_account_verify("3");
                }
            }
            page.setRecords(userBaseInfoModelList);
            return page;
    }else if (personType==0||personType==6) {//分公司的管理员
            tpmUserBaseInfoModel.setPersonType(personType);
            List<TpmUserBaseInfoModel> userBaseInfoModelList = tpmUserBaseInfoMapper.companyAdminGetUserBaseInfoList(tpmUserBaseInfoModel, page);
            for (TpmUserBaseInfoModel tpmUserBaseInfo : userBaseInfoModelList) {
                tpmUserBaseInfo.setNumber("" + number);
                tpmUserBaseInfo.setWxName(tpmUserBaseInfoMapper.getNickName(tpmUserBaseInfo.getOpenid()));
                String wxSex = tpmUserBaseInfoMapper.getWxSex(tpmUserBaseInfo.getOpenid());
                if (wxSex.equals("1")) {
                    wxSex = "男";
                } else if(wxSex.equals("2")){
                    wxSex = "女";
                }else
                {
                    wxSex="";
                }
                tpmUserBaseInfo.setWxSex(wxSex);
                if(tpmUserBaseInfo.getId_card().length()<18)
                {
                    String idCard = tpmUserBaseInfo.getId_card();
                    tpmUserBaseInfo.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
                }
                tpmUserBaseInfo.setId_card(tpmUserBaseInfo.getId_card().substring(0,6)+"********"+tpmUserBaseInfo.getId_card().substring(14,18));

                String mobilePhone = tpmUserBaseInfo.getMobile();
                byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobilePhone,RSAUtils.PUBLIC_KEY);
                BASE64Encoder enc=new BASE64Encoder();
                mobilePhone=enc.encode(decodedPwds2);
                tpmUserBaseInfo.setMobile(mobilePhone);

                number++;
                //银行卡审核的字段处理
                if (tpmUserBaseInfo.getBank_account_verify()==null||"".equals(tpmUserBaseInfo.getBank_account_verify()))
                {
                    tpmUserBaseInfo.setBank_account_verify("3");
                }
            }
            page.setRecords(userBaseInfoModelList);
            return page;
        }

    page.setRecords(new ArrayList<TpmUserBaseInfoModel>());
    return  page;
    }


    /**
     * 个人信息更改调用日志接口
     *
     * @param tpmUserBaseInfoModel
     */
    public int userBaseInfologs(TpmUserBaseInfoModel tpmUserBaseInfoModel, TpmUserBaseInfoModel preTpmUserBaseInfoModel) throws Exception {
        TpmOptLogsModel tpmOptLogsModel = new TpmOptLogsModel();

        String content2 = "";
        int flag = 0;
        if (!tpmUserBaseInfoModel.getSex().equals(preTpmUserBaseInfoModel.getSex())) {
            content2 = content2 + "原性别：" + preTpmUserBaseInfoModel.getSex() + "性别改为：" + tpmUserBaseInfoModel.getSex() + "；";
            flag = 1;
        }
        if (!tpmUserBaseInfoModel.getMobile().equals(preTpmUserBaseInfoModel.getMobile())) {
            content2 = content2 + "原手机号为：" + preTpmUserBaseInfoModel.getMobile() + ",手机号改为" + tpmUserBaseInfoModel.getMobile() + "；";
            flag = 1;
        }
        if (!tpmUserBaseInfoModel.getId_card().equals(preTpmUserBaseInfoModel.getId_card())) {
            content2 = content2 + "原身份证账号为：" + preTpmUserBaseInfoModel.getId_card() + ",身份证账号改为：" + tpmUserBaseInfoModel.getId_card() + "；";
            flag = 1;
        }
        if (!tpmUserBaseInfoModel.getProvince().equals(preTpmUserBaseInfoModel.getProvince())) {
            content2 = content2 + "原省份为：" + preTpmUserBaseInfoModel.getProvince() + ",省份改为：" + tpmUserBaseInfoModel.getProvince() + "；";
            flag = 1;
        }
        if (!tpmUserBaseInfoModel.getCity().equals(preTpmUserBaseInfoModel.getCity())) {
            content2 = content2 + "原市为：" + preTpmUserBaseInfoModel.getCity() + ",市改为：" + tpmUserBaseInfoModel.getCity() + "；";
            flag = 1;
        }
        if (!tpmUserBaseInfoModel.getAddress().equals(preTpmUserBaseInfoModel.getAddress())) {
            content2 = content2 + "原详细地址为：" + preTpmUserBaseInfoModel.getAddress() + ",详细地址改为：" + tpmUserBaseInfoModel.getAddress() + "；";
            flag = 1;
        }

        if ((tpmUserBaseInfoModel.getIdimgurl_front() != null &&tpmUserBaseInfoModel.getIdimgurl_front()!="")&& !tpmUserBaseInfoModel.getIdimgurl_front().equals(preTpmUserBaseInfoModel.getIdimgurl_front())) {
            content2 = content2 + "更改了身份证正面照片；";
            flag = 1;
        }
        if((tpmUserBaseInfoModel.getIdimgurl_back()!=null&&tpmUserBaseInfoModel.getIdimgurl_back()!="")&&!tpmUserBaseInfoModel.getIdimgurl_back().equals(preTpmUserBaseInfoModel.getIdimgurl_back()))
        {
            content2 = content2 + "更改了身份证背面照片；";
            flag = 1;
        }
        if (flag == 0) {
            tpmOptLogsModel.setContent("未修改任何信息");
            return flag;
        } else {
            tpmOptLogsModel.setContent(content2);
            tpmOptLogsModel.setType(8);
            tpmOptLogsModel.setOptUser(tpmUserBaseInfoModel.getName());
            tpmOptLogsModel.setOptUserDept(getUserDept(tpmUserBaseInfoModel.getOpenid()));
            tpmOptLogsService.insertOptLogs(tpmOptLogsModel);
            return flag;
        }

    }

    private String getUserDept(String openid) {
        List<String> depts = tpmUserBaseInfoMapper.getUserDept(openid);
        if (depts.size()!=0)
        {
            return depts.get(0);
        }else {
            return "";
        }
    }


}