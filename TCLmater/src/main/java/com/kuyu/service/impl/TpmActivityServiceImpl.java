package com.kuyu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuyu.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.TpmActivityMapper;
import com.kuyu.mapper.TpmDeptMapper;
import com.kuyu.mapper.TpmUserAccountInfoMapper;
import com.kuyu.mapper.TpmUserActivityMapper;
import com.kuyu.mapper.UserRoleInfoMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.TpmProjectModel;
import com.kuyu.model.TpmUserActivityModel;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.service.TpmActivityService;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmUserStatementService;
import com.kuyu.task.WeiXinSendMessageService;
import com.kuyu.util.ActivityUtils;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.RSAUtils;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.query.ActivityJoinQuery;
import com.kuyu.vo.query.ActivityQuery;

/**
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 活动列表 Service实现类
 */
@Service
@Transactional
public class TpmActivityServiceImpl extends ServiceImpl<TpmActivityMapper, TpmActivityModel> implements TpmActivityService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TpmUserActivityMapper tpmUserActivityMapper;

    @Autowired
    private TpmUserAccountInfoMapper tpmUserAccountInfoMapper;

    @Autowired
    private TpmDeptMapper tpmDeptMapper;

    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;

    @Autowired
    private WeiXinSendMessageService weiXinSendMessageServiceImpl;
    
    @Autowired
    private TpmUserStatementService tpmUserStatementService;

    @Autowired
    private TpmActivityMapper tpmActivityMapper;
    
    @Autowired
    private TpmBranchAdminService tpmBranchAdminService;
    @Override
    public ResultVo findActivityListByCity(LoginUserInfo userInfo, Integer type, String city) throws Exception {
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType != -1){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String openid = userInfo.getWeixinUserInfo().getOpenId();
        List<ActivityVo> activityList;
        if (type != 1) {
            //查询临促参加的活动
            activityList = baseMapper.findActivityListByUserHomePage(openid,DateUtils.today());
            for (ActivityVo activityVo : activityList) {
            	TpmUserStatementModel tusm = new TpmUserStatementModel();
            	tusm.setActivity_uuid(activityVo.getActivityUuid());
            	tusm.setOpenid(openid);
            	tusm.setActivity_time(DateUtils.today());
            	TpmUserStatementModel t = tpmUserStatementService.queryUserStatement(tusm);
            	if(t != null) {
            		activityVo.setHour_state(t.getHours_state());
            	}
			}
        } else {
            //处理城市,去掉最后的市并拼接%
            city = CheckParamUtils.trimWithString(city);
            if (city.endsWith(CommonConstants.CITY)){
                int index = city.lastIndexOf(CommonConstants.CITY);
                city = city.substring(0, index);
            }
            //查询所有的活动
            city = StringUtil.escapeStrWithRightLike(city);
            activityList = baseMapper.findActivityListByCity(city);

        }
        if (activityList.isEmpty()) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        String time = DateUtils.currentTime();
        for (ActivityVo activityVo : activityList) {
            if (type == 1) {
                //ActivityUtils.setStateByTime(true,openid,activityVo,time);
                getActivityStateUtils(activityVo);

            }
            ActivityUtils.changeDateTimeStr(activityVo);
        }
        if(type==1)
        {
            activityList = changeTheOrderOFActivity(activityList);
        }
//        activityList = changeTheOrderOFActivity(activityList);
        return ResultVo.getDataWithSuccess(activityList);
    }

    private List<ActivityVo> changeTheOrderOFActivity(List<ActivityVo> activityList) {
        List<ActivityVo> activityNotStartList = new ArrayList<>();
        List<ActivityVo> activityProceedingList =new ArrayList<>();
        List<ActivityVo> activityEndList = new ArrayList<>();
        List<ActivityVo> newActivityList = new ArrayList<>();
        for(ActivityVo activityVo: activityList)
        {
            if(activityVo.getActivityState()==0)
            {
                activityNotStartList.add(activityVo);
            }else if (activityVo.getActivityState()==1)
            {
                activityProceedingList.add(activityVo);
            }else {
                activityEndList.add(activityVo);
            }
        }
        newActivityList.addAll(activityProceedingList);
        newActivityList.addAll(activityNotStartList);
        newActivityList.addAll(activityEndList);
        return  newActivityList;
    }

    public  void getActivityStateUtils(ActivityVo activityVo)
    {
        String startDay = activityVo.getStartTime();
        String endDay = activityVo.getEndTime();
//        String activityUuid = activityVo.getActivityUuid();
        String currentDay = DateUtils.currentTime().substring(0,10);
        if(currentDay.compareTo(endDay)>0)//已结束
        {
            activityVo.setActivityState(new Integer(2));
        }else if(currentDay.compareTo(startDay)<0)//未开始
        {
            activityVo.setActivityState(new Integer(0));
        }else {//进行中
            activityVo.setActivityState(new Integer(1));
        }
    }

    /**
     * 对活动的每一天针对某个临促添加活动状态和申请状态
     * @param activityVo
     * @param openid
     * @throws Exception
     */
    public  void getActivityStateListUtils(ActivityVo activityVo, String openid) throws Exception
    {
        List<ActivityStateVo> activityStateVoList  = new ArrayList<ActivityStateVo>();
        List<String> dateList = new ArrayList<String>();
        int totalCount = activityVo.getUserNo();
        String startDay = activityVo.getStartTime();
        String endDay = activityVo.getEndTime();
        String activityUuid = activityVo.getActivityUuid();
        String currentDay = DateUtils.currentTime().substring(0,10);
        int countFlag =0;
        //状态列表list
        for(String activityDay = startDay;activityDay.compareTo(endDay)<=0;activityDay = getNextDay(activityDay))
        {
            ActivityStateVo activityStateVo = new ActivityStateVo();
            activityStateVo.setDate(activityDay.replaceAll("-","."));
            int joinCount = tpmActivityMapper.selectCountByDay(activityUuid,activityDay);
            if (currentDay.compareTo(activityDay)<0){//当前时间小于开始时间

                if (joinCount < totalCount){
                    activityStateVo.setActivityDaySate(0);//招募中(未开始)
                }else {
                    activityStateVo.setActivityDaySate(1);//招募完成(未开始)

                }
            }else if (currentDay.compareTo(activityDay)==0){//当前时间在开始与结束时间之间

                if (joinCount < totalCount){
                    activityStateVo.setActivityDaySate(2);//招募中(进行中)
                }else {
                    activityStateVo.setActivityDaySate(4);//招募完成(进行中)
                }
            }else{
                activityStateVo.setActivityDaySate(3);//已结束
            }
            //申请列表list
            TpmUserActivityModel userActivityModel = new TpmUserActivityModel().selectOne(Condition.create().where("openid={0} and activity_uuid={1} and request_activity_day={2}",openid,activityVo.getActivityUuid(),activityDay));
            if (null == userActivityModel) {
                activityStateVo.setRequestState(-1);//未申请
            }else {
                Integer requestState = userActivityModel.getRequestState();

                if (requestState > 2){
                    if (currentDay.compareTo(activityDay)<0){//当前时间小于开始时间
                        activityStateVo.setRequestState(1);//未开始,已申请
                    }else if (currentDay.compareTo(activityDay)==0){//当前时间在开始与结束时间之间
                        activityStateVo.setRequestState(3);//进行中,,已参加
                    }else{
                        activityStateVo.setRequestState(4);//已结束
                    }
                }else {
                    activityStateVo.setRequestState(requestState);
                }
                if(userActivityModel.getRejectFlag()==1)
                {
                    activityStateVo.setRequestState(5);
                }
            }
           String reson =  tpmActivityMapper.getApprovalReason(activityUuid,activityDay,openid);
            if(reson!=null)
            {
                activityStateVo.setApprovalReason(reson);
            }
            activityStateVoList.add(activityStateVo);

        }
        activityVo.setActivityStateVoList(activityStateVoList);
       /* activityStateVo.setActivityDaySateList(activityDayList);
        activityStateVo.setDateList(dateList);
        activityStateVo.setRequestStateList(requestStateList);
        activityVo.setActivityStateVo(activityStateVo);*/
       /* activityVo.setActivityDaySateList(activityDayList);
        activityVo.setRequestStateList(requestStateList);
        activityVo.setDateList(dateList);*/


       /* if(currentDay.compareTo(endDay)>0)//当天在结束时间之后
        {
            activityVo.setActivityState(0);//不能参加
            for(String activityDay = startDay;activityDay.compareTo(endDay)<=0;activityDay = getNextDay(activityDay))
            {
                activityDayList.add(0);
            }

        } else  if(currentDay.compareTo(startDay)>0&&currentDay.compareTo(endDay)<=0)//当天在开始结束之间或者等于结束时间
        {
            int countFlag = 0;
            for(String activityDay = currentDay;activityDay.compareTo(endDay)<=0;activityDay = getNextDay(activityDay))
            {
                int joinCount = tpmActivityMapper.selectCountByDay(activityUuid,activityDay);
                //某一天的人数未满
                if(joinCount<totalCount)
                {
                    if(tpmActivityMapper.selectCountByOpenidAndDay(activityUuid,openid,activityDay)==0)//未申请或被拒绝
                    {
                        countFlag = 1;
                        activityDayList.add(1);//人未满未申请或者被拒绝
                    }
                    else {//未满且已申请
                        activityDayList.add(2);
                    }
                } else {//已招满
                    activityDayList.add(0);
                }
            }
            if (countFlag==1)
            {
                activityVo.setActivityState(1);//当天未申请或者被拒绝，可以再次申请
            }else {
                activityVo.setActivityState(0);
            }

        }
        else {//当天在开始时间之前或者等于开始时间
            int countFlag = 0;
            for(String activityDay = startDay;activityDay.compareTo(endDay)<=0;activityDay = getNextDay(activityDay))
            {
                int joinCount = tpmActivityMapper.selectCountByDay(activityUuid,activityDay);
                //某一天的人数未满
                if(joinCount<totalCount)
                {
                    if(tpmActivityMapper.selectCountByOpenidAndDay(activityUuid,openid,activityDay)==0)//未申请或被拒绝
                    {
                        countFlag = 1;
                        activityDayList.add(1);//人未满未申请或者被拒绝
                    }
                    else {//未满且已申请
                        activityDayList.add(2);
                    }
                } else {//已招满
                    activityDayList.add(0);
                }
            }
            if (countFlag==1)
            {
                activityVo.setActivityState(1);//当天未申请或者被拒绝，可以再次申请
            }else {
                activityVo.setActivityState(0);
            }
        }
        activityVo.setActivityDaySateList(activityDayList);*/
    }

    /**
     * 时间工具得到下一天的日期
     * @param time
     * @return
     * @throws Exception
     */
    public  String getNextDay(String time) throws  Exception
    {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //String riqi = "2017-12-31";
        Date currentTime = formatter.parse(time);
        calendar.setTime(currentTime);
        //"2017-12-29"
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();
        String dateString = formatter.format(date);
        //System.out.println(dateString);
        return  dateString;
    }

    @Override
    public String findActivityNameByUuid(String activityUuid) {
        return baseMapper.findActivityNameByUuid(activityUuid);
    }

    /**
     * 查询活动负责人的活动列表
     *
     * @param userInfo
     * @return
     */
    @Override
    public ResultVo managerActivityList(LoginUserInfo userInfo) throws Exception {

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String managerNo = userInfo.getEmployeeModel().getPerson_code();
        managerNo = CheckParamUtils.trimWithString(managerNo);
        List<ActivityVo> activityVoList = baseMapper.findManagerActivityList(managerNo);
        if (CollectionUtils.isEmpty(activityVoList)) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        String time = DateUtils.currentTime();
        for (ActivityVo activityVo : activityVoList) {
            //ActivityUtils.setStateByTime(true,null,activityVo,time);
            getActivityStateUtils(activityVo);
            activityVo.setRealSalary(null);
            ActivityUtils.changeDateTimeStr(activityVo);
        }
        return ResultVo.getDataWithSuccess(activityVoList);
    }

    @Override
    public ResultVo managerActivityDeatil(LoginUserInfo userInfo, String activityUuid) throws Exception {
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String managerNo = userInfo.getEmployeeModel().getPerson_code();
        activityUuid = CheckParamUtils.trimWithString(activityUuid);
        ActivityDetailVo activityDetailVo = baseMapper.findManagerActivityDeatil(managerNo, activityUuid);
        if (null == activityDetailVo) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        String mobile = activityDetailVo.getRequestTelphone();
        if(StringUtil.isNotNull(mobile)) {
        	byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobile,RSAUtils.PUBLIC_KEY);
			BASE64Encoder enc=new BASE64Encoder();
			mobile=enc.encode(decodedPwds2);
			activityDetailVo.setRequestTelphone(mobile);
        }
        ActivityVo activityVo = findActivityById(false,null, activityUuid);
        activityDetailVo.setActivityState(activityVo.getActivityState());//设置活动状态
        ActivityUtils.changeDateTimeStr(activityVo);
        activityDetailVo.setStartTime(activityVo.getStartTime());
        activityDetailVo.setEndTime(activityVo.getEndTime());
        return ResultVo.getDataWithSuccess(activityDetailVo);
    }

    @Override
    /**
     * 查询活动负责人的临促人员列表
     */
    public ResultVo activityUserList(LoginUserInfo userInfo, String activityUuid, String requestActivityDay, Integer current, Integer size) throws Exception {
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        if (!CheckParamUtils.checkPageParmByNum(current,size)){
            log.info(CommonConstants.PAGE_ERROR_MSG);
            return ResultVo.getDataWithSuccess(null);
        }
        Page<AcvitityUserVo> page = new Page<>(current,size);
        String managerNo = userInfo.getEmployeeModel().getPerson_code();
        activityUuid = CheckParamUtils.trimWithString(activityUuid);
        int userNo = baseMapper.getCountOpenNumber(activityUuid,requestActivityDay);
        int user_no = baseMapper.selectById(activityUuid).getUserNo();
        List<AcvitityUserVo> acvitityUserVoList = baseMapper.activityUserList(page,managerNo, activityUuid,requestActivityDay,userNo,user_no);
        if (CollectionUtils.isEmpty(acvitityUserVoList)) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
        String currentDate = DateUtils.getSysDateTimeString().substring(0, 10);
        for (AcvitityUserVo vo : acvitityUserVoList) {
            int count = tpmUserStatementModel.selectCount("request_state > 0 and activity_uuid={0} and openid={1}", activityUuid, vo.getOpenid());
            vo.setWorkDays(count);
            String wxSex = vo.getWxSex();
            if ("1".equals(wxSex)) {
                wxSex = "男";
            } else if("2".equals(wxSex)){
                wxSex = "女";
            }else
            {
                wxSex="";
            }
            vo.setWxSex(wxSex);
            if (currentDate.compareTo(requestActivityDay)>0)
            {
                vo.setOutOfDateOrNot("1");
            }else {
                vo.setOutOfDateOrNot("0");
            }
        }

        page.setRecords(acvitityUserVoList);
        return ResultVo.getDataWithSuccess(page);
    }


    @Override
    public ResultVo activityUserDetailByManager(LoginUserInfo userInfo, String activityUuid,String requestActivityDay, String openid) throws Exception {

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType == -1){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }else if(personType == 2){
            List<String> childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
            TpmActivityModel activityModel = baseMapper.selectById(activityUuid);
            if (CollectionUtils.isEmpty(childsDeptList) || !childsDeptList.contains(activityModel.getDept())){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
//                throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
            }
        }
        else if(personType == 0 || personType == 6){
            List<String> childsDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
            TpmActivityModel activityModel = baseMapper.selectById(activityUuid);
            if (CollectionUtils.isEmpty(childsDeptList) || !childsDeptList.contains(activityModel.getDept())){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
//                throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
            }
        }
        openid = CheckParamUtils.trimWithString(openid);
        activityUuid = CheckParamUtils.trimWithString(activityUuid);
        Integer count = baseMapper.selectCountByManager(personType,userInfo.getEmployeeModel().getPerson_code(),activityUuid,openid,requestActivityDay);
        if (count == 0) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);

//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        AcvitityUserVo acvitityUserVo = baseMapper.findUserInfoByOpenid(openid);
        String wxSex = acvitityUserVo.getWxSex();
        if ("1".equals(wxSex)) {
            wxSex = "男";
        } else if("2".equals(wxSex)){
            wxSex = "女";
        }else
        {
            wxSex="";
        }
        acvitityUserVo.setWxSex(wxSex);
        String mobile = acvitityUserVo.getMobile();
		if(StringUtil.isNotNull(mobile)) {
			/*if(mobile.length() >= 4) {
				mobile = mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
				acvitityUserVo.setMobile(mobile);
			}*/
			byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobile,RSAUtils.PUBLIC_KEY);
			BASE64Encoder enc=new BASE64Encoder();
			mobile=enc.encode(decodedPwds2);
			acvitityUserVo.setMobile(mobile);
		}
		String idCard = acvitityUserVo.getId_card();
        if(StringUtil.isNotNull(idCard)) {
            if(idCard.length() >=10) {
               acvitityUserVo.setId_card(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
            }
        }
        String accountValue = acvitityUserVo.getAccount_value();
        if(StringUtil.isNotNull(accountValue)) {
            String lastFour = accountValue.substring(accountValue.length() - 4);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < accountValue.length() - 4; i++) {
                stringBuffer.append("*");
            }
            //System.out.println(stringBuffer.toString()+lastFour);
            acvitityUserVo.setAccount_value(stringBuffer.toString() + lastFour);
        }
        TpmUserActivityModel userActivityModel = new TpmUserActivityModel();
        userActivityModel.setActivityUuid(activityUuid);
        userActivityModel.setOpenid(openid);
        userActivityModel.setRequestState(null);
        userActivityModel.setRequestActivityDay(requestActivityDay);
        userActivityModel = tpmUserActivityMapper.selectOne(userActivityModel);
        if (null == userActivityModel){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);

//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        acvitityUserVo.setRequestState(userActivityModel.getRequestState());
        acvitityUserVo.setRequestReason(userActivityModel.getReason());
        acvitityUserVo.setRequestActivityDay(userActivityModel.getRequestActivityDay());

        if (null == acvitityUserVo) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        return ResultVo.getDataWithSuccess(acvitityUserVo);
    }


    @Override
    public ResultVo findActivityDetailById(LoginUserInfo userInfo, String activityUuid) throws java.lang.Exception {

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType != -1){

            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String openid = userInfo.getWeixinUserInfo().getOpenId();
        activityUuid = CheckParamUtils.trimWithString(activityUuid);
        ActivityVo activityVo = findActivityById(true,openid, activityUuid);

        if (null == activityVo) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
       /* TpmUserActivityModel model = new TpmUserActivityModel();
        model.setActivityUuid(activityUuid);
        model.setOpenid(openid);
        model.setRequestState(null);
        model.setRequestActivityDay();
        model = tpmUserActivityMapper.selectOne(model);
        if (null != model) {
            activityVo.setReason(model.getReason());
        }*/
        ActivityUtils.changeDateTimeStr(activityVo);
        String mobile = activityVo.getManagerMobile();
        if (StringUtil.isNotBlank(mobile))
        {
            byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobile,RSAUtils.PUBLIC_KEY);
            BASE64Encoder enc=new BASE64Encoder();
            mobile=enc.encode(decodedPwds2);
            activityVo.setManagerMobile(mobile);
        }
        return ResultVo.getDataWithSuccess(activityVo);
    }


    /**
     * 通过id查询活动，并给活动添加状态
     * @param flag
     * @param openid
     * @param activityUuid
     * @return
     * @throws Exception
     */
    public ActivityVo findActivityById(Boolean flag,String openid, String activityUuid) throws Exception {
        ActivityVo activityVo = baseMapper.findActivityVoById(activityUuid);
        String time = DateUtils.currentTime();
        if (null == activityVo) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return null;
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }

        //给活动添加整体状态
        getActivityStateUtils(activityVo);
       //flag为true，给活动针对零促添加每一天的状态
        if(flag==true)
        {
            getActivityStateListUtils(activityVo,openid);
        }
        return activityVo;
    }


    /**
     * 临促申请参加活动的校验方法
     * @param userInfo
     * @param activityUuid
     * @param requestActivityDay
     * @return
     */
    public  ResultVo joinActivityByUserVerify(LoginUserInfo userInfo, String activityUuid,String requestActivityDay)
    {
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType != -1){
            log.info("personType错误,为{}",personType);
            return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String openid = userInfo.getWeixinUserInfo().getOpenId();
        openid = CheckParamUtils.trimWithString(openid);
        activityUuid = CheckParamUtils.trimWithString(activityUuid);
        //查询是否是曾经绑定过员工号的员工（禁止其参加活动）
        String bindingJoinFlag = tpmActivityMapper.getBindingJoinFlag();
        if (bindingJoinFlag!=null&&bindingJoinFlag.equals("1")) {
            Integer binding = tpmActivityMapper.selectCountBinding(openid);
            if (binding > 0) {
                log.info(CommonConstants.REFUSED_THE_BINDING_USER_MSG);
                return ResultVo.getByEnumCode(CommonConstants.REFUSED_THE_BINDING_USER_CODE);
            }
        }
        //查询活动是否存在
        TpmActivityModel activityModel = baseMapper.selectById(activityUuid);
        if (null == activityModel) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_CODE);
//            throw new ParamException(ResultVoUtils.paramException(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG, "id"));
        }
        //临促选择的活动时间是否有效
        if(activityModel.getStartTime()!=null&&activityModel.getEndTime()!=null)
        {
            String currentday = DateUtils.currentTime().substring(0,10);
            if(requestActivityDay.compareTo(currentday)<0)
            {
                log.info(CommonConstants.JOIN_ACTIVITY_DAY_OVER_MSG);
                return ResultVo.getByEnumCode(CommonConstants.JOIN_ACTIVITY_DAY_OVER_CODE);
            }
            if(requestActivityDay.compareTo(activityModel.getStartTime())<0||requestActivityDay.compareTo(activityModel.getEndTime())>0){
                log.info(CommonConstants.JOIN_ACTIVITY_DAY_WRONG_MSG);
                return ResultVo.getByEnumCode(CommonConstants.JOIN_ACTIVITY_DAY_WRONG_CODE);
            }
        }
        //查询临促是否填写了个人银行卡的信息
        String accountValue = tpmUserAccountInfoMapper.selectAccountInfo(openid);
        if("暂未填写".equals(accountValue)||"".equals(accountValue)||null==accountValue)
        {
            log.info(CommonConstants.ACCOUNT_VALUE_NOT_FOUND_MSG);
            return ResultVo.getByEnumCode(CommonConstants.ACCOUNT_VALUE_NOT_FOUND_CODE);
        }
        String currentTime = DateUtils.currentTime();
        String endTime = activityModel.getEndTime();
        if (DateUtils.compareTime(endTime, currentTime)){
            long startDiff = DateUtils.getDateDiff(currentTime.substring(0, 10), endTime);
            if (startDiff != 0){
                log.info(CommonConstants.ACTIVITY_IS_ENDING_MSG);
                return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_IS_ENDING_CODE);
            }
//            throw new ParamException(ResultVoUtils.paramException(CommonConstants.ACTIVITY_IS_ENDING_MSG, "id"));
        }

        //查询临促是否申请了同一天的多个活动(查看未被剔除，未被拒绝的本人当天申请有几条)
        Integer requestCountByDay = tpmUserActivityMapper.selectCount(Condition.create().where("openid ={0} and request_activity_day={1} and reject_flag!=1 and (request_state=2 or (request_state=1 and activity_uuid = {2}))",openid,requestActivityDay,activityUuid));
        if(!(requestCountByDay==null||requestCountByDay==0))
        {
            log.info(CommonConstants.TOO_MANY_ACTIVITY_REQUESTS_MSG);
            return ResultVo.getByEnumCode(CommonConstants.TOO_MANY_ACTIVITY_REQUESTS_CODE);
        }

        //查询临促是否存在,是否同在活动的这个城市
        TpmUserBaseInfoModel tpmUserBaseInfoModel = new TpmUserBaseInfoModel().selectById(openid);
        if (null == tpmUserBaseInfoModel) {
            log.info(CommonConstants.NOT_INFORMATION_MSG);
            return ResultVo.getByEnumCode(CommonConstants.NOT_INFORMATION_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.OPENID_IS_NOT_FOUND));
        }
       /* String city = tpmUserBaseInfoModel.getCity();
        String isExistCity = activityModel.getCity();
        if (StringUtils.isNotEmpty(city) && StringUtils.isNotEmpty(isExistCity)) {
            if (!city.equals(isExistCity)) {
                throw new ParamException(ResultVoUtils.defaultException(CommonConstants.IS_NOT_SAME_CITY));
            }
        } else {
            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.IS_NOT_FOUND_CITY));
        }*/
        //查询用户是否参加过这一天的该活动
       /* Integer userCount = tpmUserActivityMapper.selectCount(Condition.create().where("activity_uuid={0} and openid={1} and request_activity_day={2} and request_state!=0 and", activityUuid, openid ,requestActivityDay));
        if (userCount > 0) {
            log.info(CommonConstants.ACTIVITY_DATA_IS_JOIN_MSG);
            return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_DATA_IS_JOIN_CODE);
//            throw new ParamException(ResultVoUtils.paramException(CommonConstants.ACTIVITY_DATA_IS_JOIN_MSG, "id"));
        }*/
        //查询该活动这一天招募的人数
        if (null == activityModel.getUserNo()  || activityModel.getUserNo() == 0) {
            log.info("活动{}招募人数有误,请检查",activityUuid);
            return ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
        }
        Integer userNoCount = activityModel.getUserNo();
        //查询已招募的人数
        Integer joinCount = tpmUserActivityMapper.selectCount(Condition.create().where("activity_uuid={0} and request_state=2 and request_activity_day={1} and reject_flag!=1", activityUuid,requestActivityDay));
        if (null == joinCount) {
            log.info("活动{}已招募人数有误,请检查",activityUuid);
            return ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
        }
        if (joinCount >= userNoCount) {
            log.info("活动{}当天已招募结束",activityUuid);
            return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_RECRUIT_FINISH_CODE);
        }
        return  ResultVoUtils.success(CommonConstants.SUCCESS);
    }

    @Override
    public ResultVo joinActivityByUser(LoginUserInfo userInfo, String activityUuid,String requestActivityDay) throws Exception {

        String openid = userInfo.getWeixinUserInfo().getOpenId();
        //插入临促活动
        TpmUserActivityModel userActivityModel = new TpmUserActivityModel();
        userActivityModel.setUuid(IdWorker.get32UUID());
        userActivityModel.setActivityUuid(activityUuid);
        userActivityModel.setOpenid(openid);
        userActivityModel.setRequestTime(DateUtils.currentTime());
        userActivityModel.setRequestActivityDay(requestActivityDay);
        Integer isRefusedOrNot = tpmActivityMapper.isRefusedOrNot(userActivityModel);
        if(isRefusedOrNot==0) {//未被拒绝
            Integer isRejectOrnot = tpmActivityMapper.isRejectOrNot(userActivityModel);
            if(isRejectOrnot==0)
            {
                userActivityModel.insert();
            }else {
                tpmActivityMapper.updateRejectUser(userActivityModel);
            }
        }else {//将被拒绝过的申请设置为申请中
            tpmActivityMapper.updateRefusedRequest(userActivityModel);
        }
        return ResultVoUtils.success(CommonConstants.SUCCESS +",uuid:"+userActivityModel.getUuid());
    }

    @Override
    public ResultVo findActivityListByUser(LoginUserInfo userInfo, Integer requestState) throws Exception {

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType != -1){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String openid = userInfo.getWeixinUserInfo().getOpenId();
        if (null == requestState || requestState > 2 || requestState < 0) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.paramException(CommonConstants.PARAM_IS_ERROR, "state"));
        }
        List<ActivityVo> activityVoList = baseMapper.findActivityListByUser(openid,requestState);
        if (activityVoList.isEmpty()) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        String time = DateUtils.currentTime();
        for (ActivityVo activityVo : activityVoList) {
//            ActivityUtils.setStateByTime(false,openid,activityVo,time);
            getActivityStateUtils(activityVo);
            getActivityStateListUtils(activityVo,openid);
            ActivityUtils.changeDateTimeStr(activityVo);
            if(requestState == 0) {
	            String date = DateUtils.today();
	            String acUuid = activityVo.getActivityUuid();
	            TpmUserStatementModel ts = new TpmUserStatementModel();
	            ts.setOpenid(openid);
	            ts.setActivity_uuid(acUuid);
	            ts.setActivity_time(date);
	            TpmUserStatementModel tusm = tpmUserStatementService.queryUserStatement(ts);
	            if(tusm != null) {
	            	Integer hourState = tusm.getHours_state();
	            	activityVo.setHour_state(hourState);
	            }
            }
        }

        return ResultVo.getDataWithSuccess(activityVoList);
    }

    @Override
    public TpmActivityModel queryTpmActivityModel(String activityUuid) {
        return baseMapper.queryTpmActivityModel(activityUuid);
    }

	@Override
	public TpmUserStatementModel getManagerInfo(String activityUuid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("activity_uuid", activityUuid);
		TpmUserStatementModel tpmUserStatementModel = baseMapper.getManagerInfo(map);
		//处理活动负责人的手机号加上*
        String mobile = tpmUserStatementModel.getMobile();
        if(StringUtil.isNotNull(mobile)) {
            /*if(mobile.length() >= 4) {
                mobile = mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
                tpmUserStatementModel.setMobile(mobile);
            }*/
        	byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobile,RSAUtils.PUBLIC_KEY);
			BASE64Encoder enc=new BASE64Encoder();
			mobile=enc.encode(decodedPwds2);
			tpmUserStatementModel.setMobile(mobile);
        }
		return tpmUserStatementModel;

	}

    @Override
    public ResultVo findActivityUserAuditList(LoginUserInfo userInfo, String activityUuid, boolean booleanFlag) throws Exception{

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String managerNo = userInfo.getEmployeeModel().getPerson_code();

        Integer count = baseMapper.selectCount(Condition.create().where("activity_uuid={0} and manager={1}", activityUuid, managerNo));
        if (count==0){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        List<AcvitityUserVo> activityUserVoList = null;
        if (booleanFlag==false)
        {
            activityUserVoList = baseMapper.findUserListByAcitvityUuid(activityUuid);
            if (CollectionUtils.isEmpty(activityUserVoList)){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
    //            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
            }
            for (AcvitityUserVo acvitityUserVo : activityUserVoList){
                String id_card = acvitityUserVo.getId_card();
                long year = 0;
                try{
                    year = DateUtils.getYearByIdCard(id_card);
                }catch (Exception e){
                }
                acvitityUserVo.setAge(Integer.valueOf(year+""));
                acvitityUserVo.setId_card(null);
            }
            return ResultVo.getDataWithSuccess(activityUserVoList);
        }else {//查询该活动下所有待审核的申请
            activityUserVoList = baseMapper.findAllApprovingUserListByAcitvityUuid(activityUuid);
            if (activityUserVoList!=null) {
//                TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
                for (AcvitityUserVo vo : activityUserVoList) {
//                    int workdayCount = tpmUserStatementModel.selectCount("request_state > 0 and activity_uuid={0} and openid={1}", activityUuid, vo.getOpenid());
//                    vo.setWorkDays(workdayCount);
                    String wxSex = vo.getWxSex();
                    if ("1".equals(wxSex)) {
                        wxSex = "男";
                    } else if ("2".equals(wxSex)) {
                        wxSex = "女";
                    } else {
                        wxSex = "";
                    }
                    vo.setWxSex(wxSex);
                }
            }
            ActivityUserListVo activityUserListVo = new ActivityUserListVo();
            activityUserListVo.setTotalSize(activityUserVoList.size());
            activityUserListVo.setAcvitityUserVoList(activityUserVoList);
            return ResultVo.getDataWithSuccess(activityUserListVo);
        }
    }

    /**
     * 管理员强行停止某个活动
     * @param userInfo
     * @param activityUuid
     * @return
     */
    @Override
    public ResultVo stopActivity(LoginUserInfo userInfo, String activityUuid) {
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (!(personType ==1||personType==0||personType==6)){
            log.info("personType错误,为{}",personType);
            return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        TpmActivityModel activityModel = baseMapper.selectById(activityUuid);
        String currentDate = DateUtils.getSysDateTimeString().substring(0, 10);
        if(currentDate.compareTo(activityModel.getEndTime())>0)
        {
            throw new ParamException(ResultVoUtils.fail("操作失败!活动已结束,活动名称:"+activityModel.getProjectName()));
        }

        Integer resultCount = tpmActivityMapper.stopActivityById(activityUuid);
        if (resultCount > 0){
            return ResultVoUtils.success(CommonConstants.SUCCESS);
        }else {
            log.info(CommonConstants.FAIL_MSG);
            return ResultVo.getByEnumCode(CommonConstants.FAIL_CODE);
        }
    }
    @Override
    public ResultVo auditUserByManager(LoginUserInfo userInfo, String activityUuid, String openid,String requestActivityDay, Integer type, String reason)throws Exception{

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        activityUuid = CheckParamUtils.trimWithString(activityUuid);
        openid = CheckParamUtils.trimWithString(openid);
        reason = CheckParamUtils.trimWithString(reason);
        requestActivityDay = CheckParamUtils.trimWithString(requestActivityDay);
        reason = StringUtil.cleanXSS(reason);

        //是否活动负责人
        TpmActivityModel activityModel = baseMapper.selectById(activityUuid);
        if (null == activityModel|| !userInfo.getEmployeeModel().getPerson_code().equals(activityModel.getManager())){
            log.info(CommonConstants.ACTIVITY_NOT_EXISTS_OR_NO_AUTHORITY_MSG);
            return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_EXISTS_OR_NO_AUTHORITY_CODE);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }


        String currentDate = DateUtils.getSysDateTimeString().substring(0, 10);
        if(currentDate.compareTo(activityModel.getEndTime())>0)
        {
            throw new ParamException(ResultVoUtils.fail("操作失败!活动已结束,活动名称:"+activityModel.getProjectName()));
        }
        if (currentDate.compareTo(requestActivityDay)>0)
        {
            log.info(CommonConstants.AUDIT_DAY_IS_OUT_OF_DATE_MSG);
            return ResultVo.getByEnumCode(CommonConstants.AUDIT_DAY_IS_OUT_OF_DATE_MSG);
        }
        TpmUserActivityModel userActivityModel = new TpmUserActivityModel();
        if (type==1){
            if (StringUtils.isEmpty(reason)){
                reason = CommonConstants.ACTIVITY_AUDIT_PASS;
            }
            userActivityModel.setRequestState(2);
        }else {
            if (StringUtils.isEmpty(reason)){
                log.info(CommonConstants.ACTIVITY_AUDIT_REASON_NOT_EMPTY_MSG);
                return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_AUDIT_REASON_NOT_EMPTY_CODE);
//                throw new ParamException(ResultVoUtils.paramException(CommonConstants.ACTIVITY_AUDIT_REASON_NOT_EMPTY_MSG,"reason"));
            }
            userActivityModel.setRequestState(0);
        }
        userActivityModel.setReason(reason);
        userActivityModel.setAuditTime(DateUtils.currentTime());

        Integer userNo = activityModel.getUserNo();
        if (userNo == null || userNo ==0){
            log.info(CommonConstants.ACTIVITY_NO_ERROR_MSG);
            return ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.ACTIVITY_NO_ERROR_MSG));
        }

        if (type == 1){
            //查询每天的申请人数有没有超过
            int count = tpmUserActivityMapper.selectCount(Condition.create().where("activity_uuid={0} and request_state=2 and request_activity_day={1} and reject_flag!=1",activityUuid,requestActivityDay));
            if (userNo <= count){
                log.info(CommonConstants.ACTIVITY_RECRUIT_FINISH_MSG);
                return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_RECRUIT_FINISH_CODE);
            }
            //weiXinSendMessageServiceImpl.sendMessageActivityJoin(true,activityUuid,openid);
        }else {
           // weiXinSendMessageServiceImpl.sendMessageActivityJoin(false,activityUuid,openid);
        }
        TpmUserActivityModel tuam = new TpmUserActivityModel();
        tuam.setActivityUuid(activityUuid);
        tuam.setOpenid(openid);
        tuam.setRequestActivityDay(requestActivityDay);
        tuam.setRejectFlag(null);
        tuam.setRequestState(null);
        TpmUserActivityModel t = tpmUserActivityMapper.selectOne(tuam);
        if(t!=null&&t.getRequestState() != 1) {
        	 return ResultVo.getByEnumCode(CommonConstants.AUDIT_USER_FAIL_CODE);
        }
        if(userActivityModel.getRequestState() == 2) {
	        Integer requestCountByDay = tpmUserActivityMapper.selectCount(Condition.create().where("openid ={0} and request_activity_day={1} and reject_flag!=1 and request_state=2",openid,requestActivityDay));
	        if(!(requestCountByDay==null||requestCountByDay==0))
	        {
	            log.info(CommonConstants.HAVE_ACTIVITY_MSG);
	            return ResultVo.getByEnumCode(CommonConstants.HAVE_ACTIVITY_CODE);
	        }
        }
        Integer resultCount = tpmUserActivityMapper.update(userActivityModel, Condition.create().where("activity_uuid={0} and openid={1} and (request_state=1 or request_state= 0) and request_activity_day={2}", activityUuid, openid,requestActivityDay));

        return ResultVoUtils.success(CommonConstants.SUCCESS);
    }


    @Override
    public ResultVo findActivityListByPage(LoginUserInfo userInfo, ActivityQuery query) throws Exception{
        query = (ActivityQuery) CheckParamUtils.trimWithObjectField(query);
        Page<ActivityDetailVo> page = new Page(query.getCurrent(),query.getSize());

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        List<String> childsDeptList = new ArrayList<>();
        if (personType == -1){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        if(personType == 2){
            childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
            if (CollectionUtils.isEmpty(childsDeptList)){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
//                throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
            }
        }
        if(personType == 0 || personType == 6){
        	childsDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
          	if (CollectionUtils.isEmpty(childsDeptList)){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
            }
        }
        query.setActivityUuid(StringUtil.escapeStrWithLike(query.getActivityUuid()));
        query.setRequestId(StringUtil.escapeStrWithLike(query.getRequestId()));
        query.setDeptName(StringUtil.escapeStrWithLike(query.getDeptName()));
        query.setProjectName(StringUtil.escapeStrWithLike(query.getProjectName()));
        query.setRequestUserName(StringUtil.escapeStrWithLike(query.getRequestUserName()));
        query.setManagerName(StringUtil.escapeStrWithLike(query.getManagerName()));
        //query.set(StringUtil.escapeStrWithLike(query.getProjectName()));
        String city = query.getCity();
        if (StringUtils.isNotEmpty(city)){
            if (city.endsWith(CommonConstants.CITY)){
                int index = city.lastIndexOf(CommonConstants.CITY);
                city = city.substring(0,index);
            }
           query.setCity(StringUtil.escapeStrWithLike(city));
        }
        List<ActivityDetailVo> activityDetailVoList = baseMapper.selectActivityListMyPage(personType, userInfo.getEmployeeModel().getPerson_code(), childsDeptList, query, page);

        if (CollectionUtils.isEmpty(activityDetailVoList)){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        for (ActivityDetailVo activityDetailVo :activityDetailVoList)
        {
            if ("1".equals(activityDetailVo.getStopFlag()))
            {
                activityDetailVo.setActivityState(3);
            }
        }
        page.setRecords(activityDetailVoList);

        return ResultVo.getDataWithSuccess(page);
    }

    @Override
    public ResultVo findActivityDetail(LoginUserInfo userInfo, String activityUuid) throws Exception{
        activityUuid = CheckParamUtils.trimWithString(activityUuid);

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        List<String> childsDeptList = new ArrayList<>();
        if (personType == -1){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        if(personType == 2){
            childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
            if (CollectionUtils.isEmpty(childsDeptList)){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
//                throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
            }
        }
        if(personType == 0 || personType == 6){
        	childsDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
          	if (CollectionUtils.isEmpty(childsDeptList)){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
            }
        }
        ActivityDetailVo activityDetailVo = baseMapper.findActivityDetail(personType,userInfo.getEmployeeModel().getPerson_code(),childsDeptList,activityUuid);
        if (null == activityDetailVo) {
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        String mobile = activityDetailVo.getRequestTelphone();
    	if(StringUtil.isNotNull(mobile)) {
    		/*if(mobile.length() >= 4) {
    			mobile = mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
    			activityDetailVo.setRequestTelphone(mobile);
    		}*/
    		byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobile,RSAUtils.PUBLIC_KEY);
			BASE64Encoder enc=new BASE64Encoder();
			mobile=enc.encode(decodedPwds2);
			activityDetailVo.setRequestTelphone(mobile);
    	}
        return ResultVo.getDataWithSuccess(activityDetailVo);
    }


    @Override
    public ResultVo findActivityJoinListByPage(LoginUserInfo userInfo, ActivityJoinQuery query) throws Exception {
        query = (ActivityJoinQuery) CheckParamUtils.trimWithObjectField(query);
        Page<ActivityJoinDetailVo> page = new Page<>(query.getCurrent(),query.getSize());
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        List<String> childsDeptList = new ArrayList<>();
        if (personType == -1){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        if (personType == 2){
            childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
            if (CollectionUtils.isEmpty(childsDeptList)){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
//                throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
            }
        }

        if(personType == 0 || personType == 6){
        	childsDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
          	if (CollectionUtils.isEmpty(childsDeptList)){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
            }
        }
        query.setTelPhone(StringUtil.escapeStrWithLike(query.getTelPhone()));
        query.setIdCard(StringUtil.escapeStrWithLike(query.getIdCard()));
        query.setDeptName(StringUtil.escapeStrWithLike(query.getDeptName()));
        query.setManagerName(StringUtil.escapeStrWithLike(query.getManagerName()));
        query.setProjectName(StringUtil.escapeStrWithLike(query.getProjectName()));
        query.setUserName(StringUtil.escapeStrWithLike(query.getUserName()));
        String requestTime = query.getRequestTime();
        if (StringUtils.isNotEmpty(requestTime)){
            query.setRequestTime(requestTime.substring(0,10));
        }

        List<ActivityJoinDetailVo> activityJoinDetailVoList = baseMapper.findActivityJoinListMyPage(personType,userInfo.getEmployeeModel().getPerson_code(),childsDeptList,query,page);
        if (CollectionUtils.isEmpty(activityJoinDetailVoList)){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }

        String personCode = userInfo.getEmployeeModel().getPerson_code();
        for (ActivityJoinDetailVo activityJoinDetailVo : activityJoinDetailVoList){
            activityJoinDetailVo.setAge(String.valueOf(DateUtils.getYearByIdCard(activityJoinDetailVo.getIdCard())));
            if(personCode.equals(activityJoinDetailVo.getManager())){
            	activityJoinDetailVo.setIsManager(true);
            }

            //处理手机号加上*号
            String mobile = activityJoinDetailVo.getTelPhone();
            if(StringUtil.isNotNull(mobile)) {
                /*if(mobile.length() >= 4) {
                    mobile = mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
                    activityJoinDetailVo.getTelPhone();
                }*/
            	byte[] decodedPwds2  = RSAUtils.encryptByPublicKey(mobile,RSAUtils.PUBLIC_KEY);
    			BASE64Encoder enc=new BASE64Encoder();
    			mobile=enc.encode(decodedPwds2);
    			activityJoinDetailVo.setTelPhone(mobile);
            }
            //处理身份证号，加上*号
            String idCard = activityJoinDetailVo.getIdCard();
            if(StringUtil.isNotNull(idCard)) {
                if(idCard.length() >=10) {
                    activityJoinDetailVo.setIdCard(idCard.substring(0,6)+"********"+idCard.substring(idCard.length()-4));
                }
            }
            //处理性别
            String wxSex = activityJoinDetailVo.getWxSex();
            if ("1".equals(wxSex)) {
                wxSex = "男";
            } else if("2".equals(wxSex)){
                wxSex = "女";
            }else
            {
                wxSex="";
            }
            activityJoinDetailVo.setWxSex(wxSex);
        }

        page.setRecords(activityJoinDetailVoList);
        return ResultVo.getDataWithSuccess(page);
    }

    @Override
    public String findFinancialPersoncodeByActivityUuid(String activity_uuid){
        return baseMapper.findFinancialPersoncodeByActivityUuid(activity_uuid);
    }

    @Override
    public ResultVo modifyActivityManager(LoginUserInfo userInfo, String activityUuid, String personCode) throws Exception{
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType ==-1 || personType == 2){
            log.info(CommonConstants.NOT_PERMISSION_MSG);
            return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
        }
        TpmActivityModel activityModel = baseMapper.selectById(activityUuid);
        if (null == activityModel){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_CODE);
        }
        if (personType !=1 && personType !=0 && personType !=6){
            TpmProjectModel tpmProjectModel = new TpmProjectModel().selectById(activityModel.getRequestId());
            if (null == tpmProjectModel || !userInfo.getEmployeeModel().getPerson_code().equals(tpmProjectModel.getRequestUser())){
                log.info(CommonConstants.NOT_PERMISSION_MSG);
                return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
            }
        }

        int count = new TpmEmployeeModel().selectCount("person_code={0}", personCode);
        if (count == 0){
            log.info(CommonConstants.EMPLOYEE_IS_NOT_FOUND);
            return ResultVoUtils.fail(CommonConstants.EMPLOYEE_IS_NOT_FOUND);
        }
        String manager = activityModel.getManager();
        if (personCode.equals(manager)){
            return ResultVoUtils.fail(CommonConstants.DATA_IS_NO_CHANGE);
        }
        activityModel.setManager(personCode);
        boolean ret = activityModel.updateById();
//        String projectId = activityModel.getProjectId();
//        List<TpmPromotionFeeOriginalModel> tpfomList = baseMapper.selectPromotionFeeOriginalByProjectId(projectId);
//        if(tpfomList != null && tpfomList.size() > 0) {
//        	for (TpmPromotionFeeOriginalModel tpfom : tpfomList) {
//        		tpfom.setManager(personCode);
//        		tpfom.updateById();
//			}
//        }
        if (!ret){
            log.info("数据库更新活动负责人异常,请知悉,活动uuid:{},活动负责人code:{}",activityUuid,personCode);
            return ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
        }

        //插入日志
        TpmOptLogsModel tpmOptLogsModel = new TpmOptLogsModel();
        tpmOptLogsModel.setCreateTime(DateUtils.currentTime());
        String optPerocde = userInfo.getEmployeeModel().getPerson_code();
        String optName = userInfo.getEmployeeModel().getPerson_name();
        tpmOptLogsModel.setOptUser(optName.concat("(").concat(optPerocde).concat(")"));
        tpmOptLogsModel.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
        tpmOptLogsModel.setType(7);
        tpmOptLogsModel.setContent("修改活动编号为:"+activityUuid+",下的负责人,原活动负责人:"+manager+",现活动负责人:"+personCode);
        tpmOptLogsModel.insert();

        return ResultVo.getDataWithSuccess(null);
    }

	@Override
	public List<ActivityVo> selectUserNoAndCount(List<String> uuidList) {
		return baseMapper.selectUserNoAndCount(uuidList);
	}

    /**
     * 从活动的特定日期 剔除零促
     * @param userInfo
     * @param activityUuid
     * @param requestActivityDay
     * @param openid
     * @return
     */
    @Override
    public ResultVo rejectUser(LoginUserInfo userInfo, String activityUuid, String requestActivityDay, String openid) {
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        activityUuid = CheckParamUtils.trimWithString(activityUuid);
        openid = CheckParamUtils.trimWithString(openid);
        //String reason = CheckParamUtils.trimWithString("dsf");
        requestActivityDay = CheckParamUtils.trimWithString(requestActivityDay);
        //reason = StringUtil.cleanXSS(reason);
        //查询活动记录是否存在
        TpmActivityModel activityModel = baseMapper.selectById(activityUuid);
        if (null == activityModel|| !userInfo.getEmployeeModel().getPerson_code().equals(activityModel.getManager())){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_CODE);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        //查询剔除的人员当天是否打卡
        TpmUserStatementModel tpmUserStatementModelQuery = new TpmUserStatementModel();
        tpmUserStatementModelQuery.setActivity_uuid(activityUuid);
        tpmUserStatementModelQuery.setActivity_time(requestActivityDay);
        tpmUserStatementModelQuery.setOpenid(openid);
        TpmUserStatementModel tpmUserStatementModel = tpmUserStatementService.queryUserStatement(tpmUserStatementModelQuery);
        if(tpmUserStatementModel!=null)//当天已打卡
        {
            if(tpmUserStatementModel.getHours_state()==0||tpmUserStatementModel.getHours_state()==1)
            {
                log.info(CommonConstants.REJECT_USER_FAIL_MSG);
                return ResultVo.getByEnumCode(CommonConstants.REJECT_USER_FAIL_CODE);
            }
        }
        TpmUserActivityModel userActivityModel = new TpmUserActivityModel();
        userActivityModel.setRejectFlag(1);
        userActivityModel.setActivityUuid(activityUuid);
        userActivityModel.setRequestActivityDay(requestActivityDay);
        userActivityModel.setOpenid(openid);
        //Integer resultCount = tpmUserActivityMapper.update(userActivityModel, Condition.create().where("activity_uuid={0} and openid={1} and (request_state= 2) and request_activity_day={2}", activityUuid, openid,requestActivityDay));
        Integer resultCount = tpmActivityMapper.updateRejectFlag(userActivityModel);
        if (resultCount > 0){
            return ResultVoUtils.success(CommonConstants.SUCCESS);
        }else {
            log.info(CommonConstants.REJECT_USER_NOT_PASSED_MSG);
            return ResultVo.getByEnumCode(CommonConstants.REJECT_USER_NOT_PASSED_CODE);
        }

    }

    @Override
    public Integer selectCountByDay(String uuid, String activityDay) {
        return  tpmActivityMapper.selectCountByDay(uuid,activityDay);
    }

    @Override
    public Integer selectTotalUserNo(String uuid) {
        return tpmActivityMapper.selectTotalUserNo(uuid);
    }

    @Override
    public ResultVo conversionActivity(LoginUserInfo userInfo) throws Exception {
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        List<TpmUserActivityModel> tpmUserActivityModelList = tpmActivityMapper.selectActivityList();
        if(tpmUserActivityModelList!=null)
        {

            for (TpmUserActivityModel tpmUserActivityModel: tpmUserActivityModelList)
            {
                int flag = 0;
                //tpmActivityMapper.updateFirstActivity(tpmUserActivityModelList[i]);
                TpmActivityModel tpmActivityModel = tpmActivityMapper.selectTime(tpmUserActivityModel.getActivityUuid());
                if(tpmActivityModel==null)
                {
                    log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                    return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_CODE);
                }
                String startDay = tpmActivityModel.getStartTime();
                String endDay = tpmActivityModel.getEndTime();

                for(String activityDay = startDay;activityDay.compareTo(endDay)<=0;activityDay = getNextDay(activityDay)) {
                    tpmUserActivityModel.setRequestActivityDay(activityDay);
                    if (flag == 0)//第一条更新
                    {
                        tpmActivityMapper.updateFirstActivityData(tpmUserActivityModel);
                    } else {//其余插入
                    //tpmActivityMapper.insertActivityData(tpmUserActivityModel);
                        tpmUserActivityModel.setUuid(StringUtil.getUUID());
                        tpmUserActivityModel.insert();
                    }
                    flag = 1;
                }
            }
        }
        return ResultVoUtils.success(CommonConstants.SUCCESS);
    }




}
