package com.kuyu.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.TpmUserActivityMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.service.TpmActivityService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.vo.ActivityDetailVo;
import com.kuyu.vo.ActivityJoinDetailVo;
import com.kuyu.vo.ActivityVo;
import com.kuyu.vo.AcvitityUserVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.ActivityJoinQuery;
import com.kuyu.vo.query.ActivityQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 *
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 活动列表Controller
 */
@Api(tags = "立项活动服务接口服务")
@AOP_Controller_LOG
@RequestMapping("/tpmActivity")
public class TpmActivityController extends BaseController{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TpmActivityService tpmActivityServiceImpl;

    @Autowired
    private TpmUserActivityMapper tpmUserActivityMapper;

    @ApiOperation(value = "根据城市查询活动列表",response = ActivityVo.class)
    @GetMapping("/user/activity/list/{type}/{city}")
    public ResultVo findActivityListByCity(@ApiParam(value = "类型,为1查询全部,不为1查询自己进行的活动",required = true)@PathVariable Integer type, @ApiParam(value = "当前城市",required = true) @PathVariable("city") String city) throws Exception{
        return tpmActivityServiceImpl.findActivityListByCity(getUserInfo(), type,  city);
    }

    @ApiOperation(value = "根据申请状态查询活动列表",response = ActivityVo.class)
    @GetMapping("/user/center/activitylist/{state}")
    public ResultVo findActivityListByUser(@ApiParam(value = "申请状态,0为进行中,1为已申请,2为已结束",required = true)@PathVariable("state")Integer requestState) throws Exception{
        return tpmActivityServiceImpl.findActivityListByUser(getUserInfo(),requestState);
    }

    @ApiOperation(value = "根据活动uuid查询活动详情",response = ActivityVo.class)
    @GetMapping("/user/activity/detail/{id}")
    public ResultVo findActivityDetailById(@ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid) throws Exception{
        return tpmActivityServiceImpl.findActivityDetailById(getUserInfo(),activityUuid);
    }

    @ApiOperation("临促申请参加活动")
    @GetMapping("/user/activity/join/{id}/{requestActivityDays}")
    public ResultVo joinActivity(@ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid,
                                 @ApiParam(value = "活动举办日期",required = true)@PathVariable("requestActivityDays") String requestActivityDays) throws Exception {

        //this.getLoginUserInfo();
        String[] requestActivityDayList = requestActivityDays.trim().split(",");
        ResultVo resultVo = new ResultVo();
        ResultVo verifyResultVo ;
        for (String requestActivityDay : requestActivityDayList)
        {
            verifyResultVo = tpmActivityServiceImpl.joinActivityByUserVerify(getLoginUserInfo(),activityUuid,requestActivityDay);
            if(!verifyResultVo.getCode().equals("0"))
            {
                if (verifyResultVo.getCode().equals("-9005"))
                {
                    verifyResultVo.setMsg("您"+requestActivityDay+"日已申请参加过活动！");
                }
                return verifyResultVo;
            }
        }
        for(String requestActivityDay: requestActivityDayList)
        {
            resultVo = tpmActivityServiceImpl.joinActivityByUser(getUserInfo(),activityUuid,requestActivityDay);
        }
         return resultVo;
    }

    @ApiOperation(value = "查询活动负责人的项目列表",response = ActivityVo.class)
    @GetMapping("/manager/activity/list")
    public ResultVo managerActivityList() throws Exception {
        return tpmActivityServiceImpl.managerActivityList(getUserInfo());
    }

    @ApiOperation(value = "查询活动负责人的项目详情",response = ActivityDetailVo.class)
    @GetMapping("/manager/activity/detail/{id}")
    public ResultVo managerActivityDetail(@ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid) throws Exception {
        return tpmActivityServiceImpl.managerActivityDeatil(getUserInfo(),activityUuid);
    }

    @ApiOperation(value = "查询活动负责人的临促人员列表",response = AcvitityUserVo.class)
    @GetMapping("/manager/activity/userlist/{id}/{requestActivityDay}/{current}/{size}")
    public ResultVo activityUserList(@ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid,
                                     @ApiParam(value = "活动日期",required = true)@PathVariable("requestActivityDay") String requestActivityDay,
                                     @ApiParam(value = "当前页",required = true)@PathVariable("current") Integer current,
                                     @ApiParam(value = "每页显示记录数",required = true)@PathVariable("size") Integer size) throws Exception {
        return tpmActivityServiceImpl.activityUserList(getUserInfo(),activityUuid,requestActivityDay,current,size);
    }

    @ApiOperation(value = "活动负责人查看临促人员详细信息",response = AcvitityUserVo.class)
    @GetMapping("/manager/activity/userdetail/{id}/{requestActivityDay}/{openid}")
    public ResultVo activityUserDetailByManager(@ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid,
                                                @ApiParam(value = "申请活动日期",required = true)@PathVariable("requestActivityDay") String requestActivityDay,
                                                @ApiParam(value = "临促openid",required = true)@PathVariable("openid") String openid) throws Exception {
        return tpmActivityServiceImpl.activityUserDetailByManager(getUserInfo(),activityUuid,requestActivityDay,openid);
    }

    @ApiOperation(value = "活动负责人查看活动需要审核临促人员列表",response = AcvitityUserVo.class)
    @GetMapping("/manager/activity/useraudit/{id}")
    public ResultVo activityUserAuditListByManager(@ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid) throws Exception {
        return tpmActivityServiceImpl.findActivityUserAuditList(getUserInfo(),activityUuid,false);
    }

    @ApiOperation(value = "活动负责人查看某个活动下所有需要审核临促人员列表",response = AcvitityUserVo.class)
    @GetMapping("/manager/activity/alluseraudit/{id}")
    public ResultVo activityAllUserAuditListByManager(@ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid) throws Exception {
        return tpmActivityServiceImpl.findActivityUserAuditList(getUserInfo(),activityUuid,true);
    }


    @ApiOperation(value = "活动负责人审核临促人员")
    @GetMapping("/manager/activity/useraudit/{id}/{openid}/{activityDay}/{type}")
    public ResultVo activityUserAuditByManager(
            @ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid,
            @ApiParam(value = "openid",required = true)@PathVariable("openid") String openid,
            @ApiParam(value = "申请参加活动日期activityDay",required = true)@PathVariable("activityDay") String activityDay,
            @ApiParam(value = "审核类型:1通过,其它不通过",required = true)@PathVariable("type") Integer type,
            @ApiParam(value = "是否通过的原因描述,不通过时不能为空")@RequestParam(value = "reason",required = false) String reason) throws Exception {
        return tpmActivityServiceImpl.auditUserByManager(getUserInfo(),activityUuid,openid,activityDay,type,reason);
    }


    @ApiOperation(value = "促销活动列表页分页",response = ActivityDetailVo.class)
    @PostMapping("/promotion/activitylistpage")
    public ResultVo findActivityList(@RequestBody ActivityQuery query) throws Exception {
        return tpmActivityServiceImpl.findActivityListByPage(getUserInfo(),query);
    }

    @ApiOperation(value = "管理员停止某个活动",response = ActivityDetailVo.class)
    @GetMapping("/promotion/stopActivity/{id}")
    public ResultVo adminStopActivity(@ApiParam(value = "活动uuid",required = true)@PathVariable("id")String activityUuid) throws Exception {
        return tpmActivityServiceImpl.stopActivity(getUserInfo(),activityUuid);
    }

    @ApiOperation(value = "促销活动详情页",response = ActivityDetailVo.class)
    @GetMapping("/promotion/activitydetail/{id}")
    public ResultVo findActivityDetail(@ApiParam(value = "活动uuid",required = true)@PathVariable("id")String activityUuid) throws Exception {
        return tpmActivityServiceImpl.findActivityDetail(getUserInfo(),activityUuid);
    }

    @ApiOperation(value = "活动申请列表页分页",response = ActivityJoinDetailVo.class)
    @PostMapping("/activity/join/activitylistpage")
    public ResultVo findActivityJoinListByPage(@RequestBody ActivityJoinQuery query) throws Exception {
        return tpmActivityServiceImpl.findActivityJoinListByPage(getUserInfo(),query);
    }

    @ApiOperation(value = "活动申请列表页批量审核临促人员")
    @GetMapping("/activity/join/useraudit/{ids}/{openids}/{activityDays}/{type}")
    public ResultVo activityUserAuditByManager(
            @ApiParam(value = "批量活动uuid",required = true)@PathVariable("ids") String activityUuids,
            @ApiParam(value = "批量openid",required = true)@PathVariable("openids") String openids,
            @ApiParam(value = "批量申请活动日期activityDays",required = true)@PathVariable("activityDays") String activityDays,
            @ApiParam(value = "审核类型:1通过,其它不通过",required = true)@PathVariable("type") Integer type) throws Exception {

        LoginUserInfo userInfo = getUserInfo();
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }

        String[] activityUuidList = activityUuids.trim().split(",");
        String[] openidList = openids.trim().split(",");
        String[] requestActivityDayList = activityDays.trim().split(",");
        if (activityUuidList.length == 0|| openidList.length ==0 || requestActivityDayList.length==0||activityUuidList.length != openidList.length||activityUuidList.length!=requestActivityDayList.length){
            log.info(CommonConstants.PARAM_IS_ERROR_MSG);
            return ResultVo.getByEnumCode(CommonConstants.PARAM_IS_ERROR_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.PARAM_IS_ERROR));
        }

        String person_code = userInfo.getEmployeeModel().getPerson_code();
        for (String uuid : activityUuidList){
            TpmActivityModel activityModel = tpmActivityServiceImpl.selectById(uuid);
            /*TpmActivityOriginalModel tpmActivityOriginalModel = new TpmActivityOriginalModel().selectById
                    (activityModel.getProjectId());*/
            String manager = activityModel.getManager();
            String prjectName = activityModel.getProjectName();
            if (!manager.equals(person_code)){
                throw new ParamException(ResultVoUtils.fail("操作失败!没有权限审核该活动:"+prjectName));
            }
           /* int count = tpmUserActivityMapper.selectCount(Condition.create().where("activity_uuid={0} and request_state=2",uuid));
            if (count>=activityModel.getUserNo() && type == 1){
                throw new ParamException(ResultVoUtils.fail("操作失败!活动已招募结束,活动名称:"+prjectName));
            }*/
            String currentDate = DateUtils.getSysDateTimeString().substring(0, 10);
            if(currentDate.compareTo(activityModel.getEndTime())>0)
            {
                throw new ParamException(ResultVoUtils.fail("操作失败!活动已结束,活动名称:"+prjectName));
            }

        }

        Map<String,HashMap<String,Integer>> uuidMap = new HashMap<>();
        if(type == 1) {
        	 Map<String,Integer> mapCountNo = new HashMap<>();
 	        for(int i = 0; i < requestActivityDayList.length; i++) {
 	        	String openid = openidList[i];
 	        	String date = requestActivityDayList[i];
 	        	String od = openid + date;
 	        	if(mapCountNo.containsKey(od)) {
 	        		return ResultVo.getByEnumCode(CommonConstants.ONLY_ALLOW_ONE_CODE);
 	        	}
 	        	mapCountNo.put(od, 1);
 	        }
	        for (int i = 0; i < activityUuidList.length; i++){
	            String uuid = activityUuidList[i];
	            String date = requestActivityDayList[i];
	            //String openid = openidList[i];
	
	        	if(uuidMap.get(activityUuidList[i]) == null) {//map里没有活动
	                HashMap<String,Integer> dateMap = new HashMap<>();
	                dateMap.put(date,1);
	                uuidMap.put(uuid, dateMap);
	        	}else {//map里有活动
	               // ArrayList<HashMap<String,Integer>> dateList = new ArrayList<>();
	                HashMap<String,Integer> dateMap = uuidMap.get(activityUuidList[i]);
	                if (dateMap.get(date)==null)//没有该日期
	                {
	                    dateMap.put(date,1);
	//                        dateList.add(map);
	                }else {
	                    dateMap.put(date, dateMap.get(date) + 1);
	                }
	        		//uuidMap.put(activityUuidList[i], uuidMap.get(activityUuidList[i])+1);
	        	}
	            Integer joinNo  = tpmActivityServiceImpl.selectCountByDay(uuid,date);
	        	Integer userNo  = tpmActivityServiceImpl.selectTotalUserNo(uuid);
	        	if(joinNo+uuidMap.get(uuid).get(date)>userNo)
	            {
	                return ResultVo.getByEnumCode(CommonConstants.PERSON_NUM_OVER_CODE);
	            }
	        }
        }
        /*List<ActivityVo>  avList = tpmActivityServiceImpl.selectUserNoAndCount(uuidList);
        for (ActivityVo activityVo : avList) {
			if(uuidMap.get(activityVo.getActivityUuid()) + activityVo.getCount() > activityVo.getUserNo()) {
				return ResultVo.getByEnumCode(CommonConstants.PERSON_NUM_OVER_CODE);
			}
		}*/
        
        ResultVo vo = new ResultVo();
        for (int i = 0; i < activityUuidList.length; i++){
            if (type ==1){
                vo = tpmActivityServiceImpl.auditUserByManager(userInfo,activityUuidList[i],openidList[i],requestActivityDayList[i],type,CommonConstants.ACTIVITY_AUDIT_PASS);
            }else {
                vo = tpmActivityServiceImpl.auditUserByManager(userInfo,activityUuidList[i],openidList[i],requestActivityDayList[i],type,CommonConstants.ACTIVITY_AUDIT_NOT_PASS);
            }
        }
        return vo;
    }

    @ApiOperation(value = "修改活动负责人",response = ActivityJoinDetailVo.class)
    @GetMapping("/activity/modifymanager/{id}/{personcode}")
    public ResultVo modifyActivityManager(@ApiParam("活动uuid") @PathVariable("id") String activityUuid,@ApiParam("工号") @PathVariable("personcode") String personCode) throws Exception {
        return tpmActivityServiceImpl.modifyActivityManager(getUserInfo(),activityUuid,personCode);
    }
    @ApiOperation(value = "剔除临促")
    @GetMapping("/activity/rejectUser/{id}/{activityDay}/{openid}")
    public ResultVo rejectUser(@ApiParam("活动uuid") @PathVariable("id") String activityUuid,
                               @ApiParam("临促参与的活动日期") @PathVariable("activityDay") String activityDay,
                               @ApiParam("临促openid") @PathVariable("openid") String openid) throws Exception {
        return tpmActivityServiceImpl.rejectUser(getUserInfo(),activityUuid,activityDay,openid);
    }

    @ApiOperation(value = "将原来的活动申请数据转为现在的版本数据")
    @GetMapping("/activity/conversionData")
    public ResultVo conversionData() throws Exception {
        return tpmActivityServiceImpl.conversionActivity(getUserInfo());
    }

}
