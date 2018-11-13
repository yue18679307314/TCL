package com.kuyu.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import com.kuyu.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.common.CommonConstants;
import com.kuyu.mapper.TpmAttendenceMapper;
import com.kuyu.mapper.TpmBranchAdminMapper;
import com.kuyu.mapper.TpmLoanBillMapper;
import com.kuyu.mapper.TpmUserStatementMapper;
import com.kuyu.mapper.TpmWorkAttendenceDetailMapper;
import com.kuyu.mapper.UserRoleInfoMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmAttendenceModel;
import com.kuyu.model.TpmLoanBillModel;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.model.TpmWorkAttendenceDetailModel;
import com.kuyu.service.TpmActivityService;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.service.TpmUserStatementService;
import com.kuyu.task.FinancialSendMessageToWechatService;
import com.kuyu.util.ActivityUtils;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.borrow.BorrowOrderVo;
import com.kuyu.vo.query.AttendanceManagementCheckQuery;
import com.kuyu.vo.query.AttendanceManagementQuery;
import com.kuyu.vo.query.SettlementManagementCheckQuery;
import com.kuyu.vo.query.SettlementManagementQuery;
@Service
@Transactional
public class TpmUserStatementServiceImpl extends ServiceImpl<TpmUserStatementMapper, TpmUserStatementModel> implements TpmUserStatementService{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TpmActivityServiceImpl tpmActivityServiceImpl;
	
	@Autowired
	private UserRoleInfoMapper userRoleInfoMapper;
	
	@Autowired
	private TpmOptLogsService tpmOptLogsService;
	
	@Autowired
	private FinancialSendMessageToWechatService financialSendMessageToWechatService;
	
	@Autowired
	private TpmUserStatementService tpmUserStatementService;
	
	@Autowired
	private TpmActivityService tpmActivityService;
	
	@Autowired
	private TpmUserBaseInfoService tpmUserBaseInfoService;
	
	@Autowired
	private TpmWorkAttendenceDetailMapper tpmWorkAttendenceDetailMapper;
	
	@Autowired
	private TpmAttendenceMapper tpmAttendenceMapper;
	
	@Autowired
	private TpmLoanBillMapper tpmLoanBillMapper;
	
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;
	
	@Value("${outputAttendance_path}")
	private String attendencePath;
	
	@Value("${outputAttendance_url}")
	private String attendenceURl;
	
	@Value("${outputSettlement_path}")
	private String outputSettlementPath;
	
	@Value("${outputSettlement_url}")
	private String outputSettlementURl;

	@Override
	public ResultVo findUserStatementActivityListByOpenid(LoginUserInfo userInfo) throws Exception {

		Integer personType = CheckParamUtils.getPersonType(userInfo);
		if (personType != -1){
			log.info("personType错误,为{}",personType);
			return ResultVo.getDataWithSuccess(null);
//			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
		}
		String openid = userInfo.getWeixinUserInfo().getOpenId();
		openid = CheckParamUtils.trimWithString(openid);

		List<ActivityVo> activityVoList = baseMapper.findUserStatementActivityListByOpenid(openid);
		if (activityVoList.isEmpty()) {
			log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
			return ResultVo.getDataWithSuccess(null);
//			throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
		}
		String time = DateUtils.currentTime();
		double total = 0;// 总收益
		double lastTotal = 0;// 近期总收益
		for (ActivityVo activityVo : activityVoList) {
			int dateTimeType = ActivityUtils.getDateTimeType(activityVo, time);
			if (dateTimeType == 1) {
				activityVo.setActivityState(2);
			} else if (dateTimeType == 2){
				activityVo.setActivityState(3);// 已结束
			}
			Double realSalary = activityVo.getRealSalary();//即将收到的报酬
			lastTotal += realSalary;
			if(realSalary ==0)
			{
				activityVo.setIsHasLastRealSalary(0);
			}

			Double totalRealSalary = activityVo.getTotalRealSalary();//已经付款成功收到的报酬
			if(null==totalRealSalary)
			{
				totalRealSalary=0.0;
			}
			total += totalRealSalary;

			activityVo.setUnitPrice(null);
		}

		StatementActivityListVo statementVo = new StatementActivityListVo();
		statementVo.setLastTotalSalary(lastTotal);
		statementVo.setTotalRealSalary(total);
		statementVo.setActivityVoList(activityVoList);

		return ResultVo.getDataWithSuccess(statementVo);
	}

	@Override
	public ResultVo findStatementActivityDetailList(LoginUserInfo userInfo, String activityUuid) throws Exception {

		Integer personType = CheckParamUtils.getPersonType(userInfo);
		if (personType != -1){
			log.info("personType错误,为{}",personType);
			return ResultVo.getDataWithSuccess(null);

//			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
		}
		String openid = userInfo.getWeixinUserInfo().getOpenId();
		openid = CheckParamUtils.trimWithString(openid);

		List<StatementDetailVo> statementDetailVoList = baseMapper.findStatementActivityDetailList(activityUuid,openid);
		if (statementDetailVoList.isEmpty()) {
			log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
			return ResultVo.getDataWithSuccess(null);

//			throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
		}
		double total = 0;
		for (StatementDetailVo statementDetailVo : statementDetailVoList) {
			if (statementDetailVo.getPayState() !=0) {// 非付款失败
				total += statementDetailVo.getRealSalary();
			}
		}

		StatementActivityDetailVo statementActivityDetailVo = new StatementActivityDetailVo();
		statementActivityDetailVo.setProjectName(tpmActivityServiceImpl.findActivityNameByUuid(activityUuid));
		statementActivityDetailVo.setStatementVoList(statementDetailVoList);
		statementActivityDetailVo.setTotalRealSalary(total);

		return ResultVo.getDataWithSuccess(statementActivityDetailVo);
	}

	/**
	 * 根据activity_uuid，openid查询单条记录
	 * 
	 * @param tpmUserStatementModel
	 * @return
	 */
	public TpmUserStatementModel queryUserStatement(TpmUserStatementModel tpmUserStatementModel) {
		return baseMapper.queryUserStatement(tpmUserStatementModel);
	}

	/**
	 * 查询考勤记录
	 * 
	 * @param tpmUserStatementModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultVo queryRecords(TpmUserStatementModel tpmUserStatementModel) throws Exception {
		if (StringUtil.isEmpty(tpmUserStatementModel.getOpenid())) {
			log.info(CommonConstants.OPENID_NOT_FOUND_MSG);
//			throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getData(ResultVo.SUCCESS,null);
		}
		Map<String, Object> map = new HashMap<>();
		String activityMonth = tpmUserStatementModel.getMonth();
		if (StringUtil.isEmpty(activityMonth)) {
			activityMonth = DateUtils.today();
		} else {
			if (activityMonth.length() < 7) {
				log.info(CommonConstants.PARAM_IS_ERROR_MSG);
//				throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
				return ResultVo.getData(ResultVo.SUCCESS,null);
			} else if (activityMonth.length() > 7) {
				activityMonth = activityMonth.substring(0, 7);
			}
			StringBuffer date = new StringBuffer().append(activityMonth).append("-01");
			activityMonth = date.toString();
		}
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(activityMonth);
		String startTime = DateUtils.getReqDate(DateUtils.getFirstDayOfMonth(date));
		String endTime = DateUtils.getReqDate(DateUtils.getLastDayOfMonth(date));
		map.put("activity_uuid", tpmUserStatementModel.getActivity_uuid());
		map.put("openid", tpmUserStatementModel.getOpenid());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<TpmUserStatementModel> tusmList = baseMapper.queryRecords(map);
		return ResultVo.getData(ResultVo.SUCCESS, tusmList);
	}

	/**
	 * 添加考勤记录，如果没有上班时间则新增
	 * 
	 * @param tpmUserStatementModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultVo insertRecords(TpmUserStatementModel tpmUserStatementModel) throws Exception {
		tpmUserStatementModel.setActivity_time(DateUtils.today());
		tpmUserStatementModel.setUuid(StringUtil.getUUID());
		baseMapper.insertRecords(tpmUserStatementModel);
		return ResultVo.get(ResultVo.SUCCESS);
	}

	/**
	 * 更新考勤记录，如果已经有上班打卡时间，则更新
	 * 
	 * @param tpmUserStatementModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultVo updateRecords(TpmUserStatementModel tpmUserStatementModel) throws Exception {
		String work_start_time = baseMapper.queryUserStatement(tpmUserStatementModel).getWork_start_time();
		String work_end_time = tpmUserStatementModel.getWork_end_time();
		long d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(work_start_time).getTime();
		long d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(work_end_time).getTime();
		int hours = (int) ((d2 - d1) / (60 * 60 * 1000));
		int minute = (int)((d2 - d1 - hours * 60 * 60 * 1000) / (60 * 1000));
		tpmUserStatementModel.setWork_hours(hours);
		double real_salary = tpmUserStatementModel.getReal_salary();
		if(hours < tpmUserStatementModel.getPlanWorkHours()) {
			real_salary = real_salary / tpmUserStatementModel.getPlanWorkHours() * hours;
			double minSalary = minute / 60.0 * (tpmUserStatementModel.getReal_salary() / tpmUserStatementModel.getPlanWorkHours());
			real_salary = real_salary + minSalary;
		}
		tpmUserStatementModel.setWork_minute(minute);
		tpmUserStatementModel.setValid_work_hours(hours);
		tpmUserStatementModel.setValid_work_minute(minute);
		real_salary = Math.ceil(real_salary);
		tpmUserStatementModel.setReal_salary(real_salary);
//		tpmUserStatementModel.setRequest_state(1);//默认已生成结算单
//		baseMapper.update(tpmUserStatementModel, Condition.create().where("openid={0} and activity_uuid={1} and activity_time={2}",tpmUserStatementModel.getOpenid(),tpmUserStatementModel.getActivity_uuid(),tpmUserStatementModel.getActivity_time()));
		baseMapper.updateRecords(tpmUserStatementModel);
		return ResultVo.get(ResultVo.SUCCESS);
	}

	/**
	 * 根据活动id和活动时间查询
	 */
	@Override
	public List<TpmUserStatementModel> attendanceManagement(Map<String, Object> map) {
		List<TpmUserStatementModel> userStatementList = baseMapper.attendanceManagement(map);
		return userStatementList;
	}
	
	/**
	 * 根据活动id和活动时间查询
	 */
	@Override
	public List<TpmUserStatementModel> attendanceManagementBypage(Map<String, Object> map,Page<TpmUserStatementModel> page ) {
		List<TpmUserStatementModel> userStatementList = baseMapper.attendanceManagementBypage(map,page);
		return userStatementList;
	}

	/**
	 * 调整工时
	 * 
	 * @param tpmUserStatementModel
	 * @return
	 * @throws Exception 
	 */
	public Integer adjustWorkingHours(TpmUserStatementModel tpmUserStatementModel,LoginUserInfo userInfo) throws Exception {
		TpmOptLogsModel tolm = new TpmOptLogsModel();
		tolm.setType(3);
		String opt_user = userInfo.getEmployeeModel().getPerson_name()+"("+userInfo.getEmployeeModel().getPerson_code()+")";
		tolm.setOptUser(opt_user);
		tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
		TpmUserStatementModel tusm = baseMapper.queryUserStatement(tpmUserStatementModel);
		String openName = tpmUserBaseInfoService.getOpenName(tusm.getOpenid());
		StringBuffer content = new StringBuffer("临促"+openName+"("+tusm.getOpenid()+")"+tusm.getActivity_time()+"的结算单"+tusm.getUuid());
		if(tpmUserStatementModel.getHours_state() == 1) {
			double real_salary = tpmUserStatementModel.getUnit_price();
			int hours = tpmUserStatementModel.getValid_work_hours();
			if( hours < tpmUserStatementModel.getPlanWorkHours()) {
				real_salary = real_salary / tpmUserStatementModel.getPlanWorkHours() * hours ;
				double minSalary = tpmUserStatementModel.getValid_work_minute() / 60.0 * (tpmUserStatementModel.getUnit_price() / tpmUserStatementModel.getPlanWorkHours());
				real_salary = real_salary + minSalary;
			}
			real_salary = Math.ceil(real_salary);
			tpmUserStatementModel.setReal_salary(real_salary);
			if(real_salary != tusm.getReal_salary()) {
				content.append("审核通过,实际工作时间由"+tusm.getValid_work_hours()+"时"+tusm.getWork_minute()+"分调整为："+tpmUserStatementModel.getValid_work_hours()
					+"时"+tpmUserStatementModel.getValid_work_minute()+"分，实际工资由"+tusm.getReal_salary()+"元变为"+real_salary+"元");
				if(StringUtil.isNotNull(tpmUserStatementModel.getReduce_hours_reason())) {
					content.append(",调整理由为："+tpmUserStatementModel.getReduce_hours_reason());
				}
			}
		}else {
			content.append("审核不通过");
		}
		if(StringUtil.isNotNull(tpmUserStatementModel.getReason())) {
			content.append(","+"审核备注为："+tpmUserStatementModel.getReason());
		}
		tolm.setContent(content.toString());
		tpmOptLogsService.insertOptLogs(tolm);
		
		return baseMapper.adjustWorkingHours(tpmUserStatementModel);
	}

	/**
	 * 调整工资，扣除或者奖励
	 * 
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ResultVo adjustMoney(TpmUserStatementQuery tpmUserStatementQuery,LoginUserInfo userInfo) throws Exception {
		
		TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
		tpmUserStatementModel.setActivity_uuid(tpmUserStatementQuery.getActivity_uuid());
		tpmUserStatementModel.setOpenid(tpmUserStatementQuery.getOpenid());
		tpmUserStatementModel.setActivity_time(tpmUserStatementQuery.getActivity_time());
		TpmUserStatementModel  tusm  = baseMapper.queryUserStatement(tpmUserStatementModel);
		if(tusm == null) {
			log.info(CommonConstants.STATEMENT_NOT_EXIST_MSG);
//			throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
			return ResultVo.getByEnumCode(CommonConstants.STATEMENT_NOT_EXIST_CODE);
		}
		if( tusm.getHours_state() == null || tusm.getHours_state() == 0) {
			log.info(CommonConstants.HOUR_STATE0_MSG);
//			throw new ParamException(new ResultVo(CommonConstants.ERROR_DEFAULT_CODE,CommonConstants.HOUR_STATE0));
			return ResultVo.getByEnumCode(CommonConstants.HOUR_STATE0_CODE);
		}
		if(tusm.getHours_state() == 2) {
			log.info(CommonConstants.HOUR_STATE2_MSG);
//			throw new ParamException(new ResultVo(CommonConstants.ERROR_DEFAULT_CODE,CommonConstants.HOUR_STATE2));
			return ResultVo.getByEnumCode(CommonConstants.HOUR_STATE2_CODE);
		}
		if(tusm.getRequest_state() != 1 && tusm.getRequest_state() != 5) {
			return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_DONE_CODE);
		}
		if(tpmUserStatementQuery.getRequest_state() == 2) {
			double realSalary;
			if(tusm.getReal_salary() == null) {
				realSalary = 0;
			}else {
				realSalary = tusm.getReal_salary();
			}
			double adjustMoney = tpmUserStatementQuery.getAdjust_money();
			adjustMoney = Double.valueOf(String.format("%.2f", adjustMoney));
			tpmUserStatementQuery.setAdjust_money(adjustMoney);
			if(tpmUserStatementQuery.getIsIncentiveOrReduce()) {
				String activity_uuid = tpmUserStatementQuery.getActivity_uuid();
				Double incentiveAmount = tpmActivityService.queryTpmActivityModel(activity_uuid).getIncentiveAmount();
				if(incentiveAmount == null) {
					incentiveAmount = 0.0;
				}
				Double incentiveCount = baseMapper.getIncentiveCount(activity_uuid);
				if(incentiveCount == null) {
					incentiveCount = 0.0;
				}
				incentiveCount = incentiveCount + adjustMoney;
				if(incentiveCount > incentiveAmount) {
					log.info(CommonConstants.REWARD_OVER_PREDICTION_MSG);
					return ResultVo.getByEnumCode(CommonConstants.REWARD_OVER_PREDICTION_CODE);
				}
				realSalary = realSalary + tpmUserStatementQuery.getAdjust_money();
				tpmUserStatementModel.setIncentive_amount(tpmUserStatementQuery.getAdjust_money());
				tpmUserStatementModel.setIncentive_reason(tpmUserStatementQuery.getAdjust_money_reason());
			}else {
				realSalary = realSalary - tpmUserStatementQuery.getAdjust_money();
				tpmUserStatementModel.setReduce_money(tpmUserStatementQuery.getAdjust_money());
				tpmUserStatementModel.setReduce_money_reason(tpmUserStatementQuery.getAdjust_money_reason());
				if(realSalary < 0) {
					realSalary = 0;
				}
			}
			tpmUserStatementModel.setReal_salary(realSalary);
		}
		tpmUserStatementModel.setRequest_state(tpmUserStatementQuery.getRequest_state());
		tpmUserStatementModel.setReason(tpmUserStatementQuery.getReason());
		tpmUserStatementModel.setStatement_check_time(DateUtils.currentTime());
		baseMapper.adjustMoney(tpmUserStatementModel);
		
		TpmOptLogsModel tolm = new TpmOptLogsModel();
		tolm.setType(5);
		String opt_user = userInfo.getEmployeeModel().getPerson_name()+"("+userInfo.getEmployeeModel().getPerson_code()+")";
		tolm.setOptUser(opt_user);
		tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
		String openName = tpmUserBaseInfoService.getOpenName(tusm.getOpenid());
		StringBuffer content = new StringBuffer("临促"+openName+"("+tusm.getOpenid()+")"+tusm.getActivity_time()+"的结算单"+tusm.getUuid());
		if(tpmUserStatementQuery.getRequest_state() == 2){
			content.append("审核通过");
			if(tpmUserStatementQuery.getAdjust_money() != 0) {
				content.append(",实际报酬由"+tusm.getReal_salary()+"元调整为"+tpmUserStatementModel.getReal_salary()+"元");
				if(StringUtil.isNotNull(tpmUserStatementQuery.getAdjust_money_reason())) {
					content.append(",调整理由是："+tpmUserStatementQuery.getAdjust_money_reason());
				}
			}
		}else {
			content.append("审核不通过");
		}
		content.append(",审核备注为："+tpmUserStatementQuery.getReason());
		tolm.setContent(content.toString());
		tpmOptLogsService.insertOptLogs(tolm);
		
		return ResultVo.get(ResultVo.SUCCESS);
	}

	/**
	 * 考勤管理查询
	 * @throws Exception 
	 */
	@Override
	public Page<AttendanceManagementVo> queryAttendanceManagement(LoginUserInfo userInfo,AttendanceManagementQuery amq) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		List<String> childsDeptList = new ArrayList<>();
		if(personType == -1) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
//			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
			return null;
		}
		if(personType == 2) {
			childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if(childsDeptList == null || childsDeptList.size() == 0) {
				log.info("部门列表childsDeptList错误，为{}",childsDeptList);
//				throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
				return null;
			}
			amq.setList(childsDeptList);
		}
		if(personType == 0 || personType == 6){
			List<String> branchDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
			if(branchDeptList == null || branchDeptList.size() == 0) {
				log.info("部门列表branchDeptList错误，为{}",branchDeptList);
				return null;
			}
			amq.setList(branchDeptList);
		}
		amq.setPersonCode(userInfo.getEmployeeModel().getPerson_code());
		amq.setPersonType(personType);
		Page<AttendanceManagementVo> page = new Page<>(amq.getCurrent(),amq.getSize());
		List<AttendanceManagementVo> list = baseMapper.queryAttendanceManagement(amq, page);
		if(personType == 4) {
			if(list != null && list.size() > 0) {
				String personCode = userInfo.getEmployeeModel().getPerson_code();
				for (AttendanceManagementVo attendanceManagementVo : list) {
					if(personCode.equals(attendanceManagementVo.getManager())) {
						attendanceManagementVo.setIsManager(true);
					}
					TpmWorkAttendenceDetailModel tadm = new TpmWorkAttendenceDetailModel();
					tadm.setOpenid(attendanceManagementVo.getOpenid());
					tadm.setActivity_uuid(attendanceManagementVo.getActivity_uuid());
					tadm.setClock_time(attendanceManagementVo.getWork_start_time());
					List<TpmWorkAttendenceDetailModel> taList = tpmWorkAttendenceDetailMapper.queryTpmWorkAttendenceDetailModel(tadm);
					if(taList != null && taList.size() > 0) {
						TpmWorkAttendenceDetailModel ta = taList.get(0);
						attendanceManagementVo.setStart_clock_address(ta.getClock_city()+ta.getClock_address());
					}
					if(StringUtil.isNotNull(attendanceManagementVo.getWork_end_time())) {
						tadm.setClock_time(attendanceManagementVo.getWork_end_time());
						List<TpmWorkAttendenceDetailModel> taaList = tpmWorkAttendenceDetailMapper.queryTpmWorkAttendenceDetailModel(tadm);
						if(taaList != null && taaList.size() > 0) {
							TpmWorkAttendenceDetailModel taa = taaList.get(0);
							attendanceManagementVo.setEnd_clock_address(taa.getClock_city()+taa.getClock_address());
						}
					}
				}
			}
		}
		
		page.setRecords(list);
		return page;
	}


	@Override
	public TpmUserStatementModel queryAttendanceDetails(String uuid){
		return baseMapper.queryAttendanceDetails(uuid);
	}

	@Override
	public ResultVo checkAttendanceManagement(LoginUserInfo userInfo,AttendanceManagementCheckQuery amcq) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		if(personType == -1 || personType == 1 || personType == 2 || personType == 0 || personType == 6) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
//			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
			return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
		}
		String personCode = userInfo.getEmployeeModel().getPerson_code();
		List<String> uuidList = amcq.getUuidList();
		for (String uuid : uuidList) {
			TpmUserStatementModel tusm = tpmUserStatementService.queryAttendanceDetails(uuid);
			if(tusm.getHours_state() != 0) {
				return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_DONE_CODE);
			}
			String manager = tpmActivityService.queryTpmActivityModel(tusm.getActivity_uuid()).getManager();
			if(!personCode.equals(manager)) {
				log.info(CommonConstants.NOT_PERMISSION_MSG);
				return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
			}
		}
		if(!amcq.getCheckStatus().equals("2") && !amcq.getCheckStatus().equals("1")) {
			log.info(CommonConstants.PARAM_IS_ERROR_MSG);
//			throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getByEnumCode(CommonConstants.PARAM_IS_ERROR_CODE);
		}
		TpmOptLogsModel tolm = new TpmOptLogsModel();
		tolm.setType(2);
		String opt_user = userInfo.getEmployeeModel().getPerson_name()+"("+userInfo.getEmployeeModel().getPerson_code()+")";
		tolm.setOptUser(opt_user);
		tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
		String check;
		if(amcq.getCheckStatus().equals("1")) {
			check = "审核通过";
		}else {
			check = "审核不通过";
			if(!StringUtil.isNotNull(amcq.getAdjust_hours_note())) {
				return ResultVo.getByEnumCode(CommonConstants.AUDIT_NO_PASS_CODE);
			}
		}
//		List<String> uuidList = amcq.getUuidList();
		for (String uuid : uuidList) {
			TpmUserStatementModel tus = tpmUserStatementService.selectById(uuid);
			String openName = tpmUserBaseInfoService.getOpenName(tus.getOpenid());
			StringBuffer content = new StringBuffer("临促"+openName+"("+tus.getOpenid()+")"+tus.getActivity_time()+"的结算单"+uuid+check);
			if(amcq.getCheckStatus().equals("1") && amcq.getReal_salary() != null && tus.getReal_salary() != amcq.getReal_salary() ) {
				content.append(",工时由"+tus.getValid_work_hours()+"时"+tus.getValid_work_minute()+"分调整为"+amcq.getValid_work_hours()+
						"时"+amcq.getWork_minute()+"分,实际报酬由"+tus.getReal_salary()+"元调整为"+amcq.getReal_salary()+"元,调整理由为："+amcq.getReduce_hours_reason());
			}
			if(StringUtil.isNotNull(amcq.getAdjust_hours_note())) {
				content.append(";备注："+amcq.getAdjust_hours_note());
			}
			tolm.setContent(content.toString());
			System.out.println(content.length());
			tpmOptLogsService.insertOptLogs(tolm);
		}
		baseMapper.checkAttendanceManagement(amcq);
		return ResultVo.get(ResultVo.SUCCESS);
	}
	
	@Override
	public Page<SettlementManagementVo> querySettlementManagement(LoginUserInfo userInfo,SettlementManagementQuery smq) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		List<String> childsDeptList = new ArrayList<>();
		if(personType == -1) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
//			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
			return null;
		}
		if(personType == 2) {
			childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if(childsDeptList == null || childsDeptList.size() == 0) {
				log.info("部门列表错误，为{}",childsDeptList);
//				throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
				return null;
			}
			smq.setList(childsDeptList);
		}
		if(personType == 0 || personType == 6){
			List<String> branchDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
			if(branchDeptList == null || branchDeptList.size() == 0) {
				log.info("部门列表branchDeptList错误，为{}",branchDeptList);
				return null;
			}
			smq.setList(branchDeptList);
		}
		smq.setPersonCode(userInfo.getEmployeeModel().getPerson_code());
		smq.setPersonType(personType);
		if(StringUtil.isNotNull(smq.getFsscLoanBillNo())){
			List<TpmLoanBillModel> tlbmList = tpmLoanBillMapper.selectList(new EntityWrapper<TpmLoanBillModel>().like("fssc_loan_bill_no", smq.getFsscLoanBillNo()));
			List<String> tList = new ArrayList<>();
			if(tlbmList != null && tlbmList.size() > 0){
				for (TpmLoanBillModel tpmLoanBillModel : tlbmList) {
					tList.add(tpmLoanBillModel.getLoad_bill_uuid());
				}
			}
			smq.setLbuList(tList);
		}
		Page<SettlementManagementVo> page = new Page<>(smq.getCurrent(),smq.getSize());
		List<SettlementManagementVo> list = new ArrayList<>();
		if(StringUtil.isEmpty(smq.getFsscLoanBillNo()) || 
				(StringUtil.isNotNull(smq.getFsscLoanBillNo()) && smq.getLbuList() != null && smq.getLbuList().size() > 0)){
			list = baseMapper.querySettlementManagement(smq,page);
		}
		if(list != null && list.size() > 0) {
			List<String> uuidList = new ArrayList<>();
			Map<String,SettlementManagementVo> map = new HashMap<>();
			for (SettlementManagementVo settlementManagementVo : list) {
				String dept = settlementManagementVo.getOrgCode();
				TpmDeptEmplyeeVo tdev = userRoleInfoMapper.getUserInfo(dept);
				if(tdev != null) {
					settlementManagementVo.setFinancialPersonCode(tdev.getPerson_code());
					settlementManagementVo.setFinancialPersonName(tdev.getPerson_name());
				}
				uuidList.add(settlementManagementVo.getUuid());
				map.put(settlementManagementVo.getUuid(), settlementManagementVo);
			}
			List<TpmAttendenceModel> tamList = tpmAttendenceMapper.selectByTusUuidList(uuidList);
			if(tamList != null && tamList.size() > 0) {
				for (TpmAttendenceModel tam : tamList) {
					SettlementManagementVo smv = map.get(tam.getTus_uuid());
					smv.setAttendencePersonName(tam.getPerson_name());
					smv.setAttendenceTime(tam.getCreate_time());
				}
			}
			for (SettlementManagementVo smv : list) {
				String loadBillUuid = smv.getLoadBillUuid();
				if(loadBillUuid != null){
					TpmLoanBillModel tlbm = tpmLoanBillMapper.selectById(loadBillUuid);
					if(tlbm != null){
						String fsscLoanBillNo = tlbm.getFssc_loan_bill_no();
						smv.setFsscLoanBillNo(fsscLoanBillNo);
					}
				}
			}
			if(personType == 4) {
				String personCode = userInfo.getEmployeeModel().getPerson_code();
				for (SettlementManagementVo settlementManagementVo : list) {
					if(personCode.equals(settlementManagementVo.getManager())) {
						settlementManagementVo.setIsManager(true);
					}
				}
			}else if(personType == 2 || personType == 6) {
				String personCode = userInfo.getEmployeeModel().getPerson_code();
				for (SettlementManagementVo settlementManagementVo : list) {
					if(personCode.equals(settlementManagementVo.getFinancialPersonCode())){
						settlementManagementVo.setIsFinancial(true);
					}
				}
			}
		}
		page.setRecords(list);
		return page;
	}


	@Override
	public ResultVo checkSettlementManagement(LoginUserInfo userInfo,SettlementManagementCheckQuery smcq) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		if(personType == -1 || personType == 1 || personType == 0) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
//			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
			return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
		}else{
			if(personType == 4) {
				if(smcq.getIsFinacial() == true) {
					log.info(CommonConstants.NOT_PERMISSION_MSG);
	//				throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
					return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
				}
				if(!smcq.getCheckStatus().equals("0") && !smcq.getCheckStatus().equals("2")) {
					log.info(CommonConstants.CHECK_STATUS_MSG);
	//				throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
					return ResultVo.getByEnumCode(CommonConstants.CHECK_STATUS_CODE);
				}
				for(String uuid : smcq.getUuidList()) {
					TpmActivityModel tam = tpmActivityService.queryTpmActivityModel(tpmUserStatementService.queryAttendanceDetails(uuid).getActivity_uuid());
					String manager = tam.getManager();
					if(!userInfo.getEmployeeModel().getPerson_code().equals(manager)) {
						log.info(CommonConstants.NOT_PERMISSION_MSG);
						return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
					}
					if(smcq.getCheckStatus().equals("2")){
						String orgCode = tam.getDept();
						if(!StringUtil.isNotNull(userRoleInfoMapper.getPersonCodeByOrgCode(orgCode))) {
							return ResultVo.getByEnumCode(CommonConstants.FINANCIAL_NULL_CODE);
						}
					}
				}
				if(smcq.getCheckStatus().equals("0")) {
					if(StringUtil.isEmpty(smcq.getAdjust_money_note())) {
						log.info(CommonConstants.AUDIT_NO_PASS_MSG);
						return ResultVo.getByEnumCode(CommonConstants.AUDIT_NO_PASS_CODE);
					}
				}
			}
			List<TpmUserStatementModel> tpmList = baseMapper.selectTusmByUuids(smcq.getUuidList());
			for (TpmUserStatementModel tpm : tpmList) {
				if(personType == 4) {
					if(tpm.getHours_state() == 0) {
						return ResultVo.getByEnumCode(CommonConstants.HOUR_STATE0_CODE);
					}else if(tpm.getHours_state() == 2) {
						return ResultVo.getByEnumCode(CommonConstants.HOUR_STATE2_CODE);
					}
					if(tpm.getRequest_state() != 1 && tpm.getRequest_state() != 5) {
						return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_DONE_CODE);
					}
				}else if(personType == 2 || personType == 6) {
					if(tpm.getRequest_state() == 4 || tpm.getRequest_state() == 5) {
						return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_DONE_CODE);
					}
				}
			}
			if(personType == 2 || personType == 6) {
				if(smcq.getIsFinacial() == false) {
					log.info(CommonConstants.NOT_PERMISSION_MSG);
	//				throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
					return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
				}
				if(!smcq.getCheckStatus().equals("4") && !smcq.getCheckStatus().equals("5")) {
					log.info(CommonConstants.CHECK_STATUS_MSG);
	//				throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
					return ResultVo.getByEnumCode(CommonConstants.CHECK_STATUS_CODE);
				}
				/*if(smcq.getCheckStatus().equals("4")) {
					for (String uuid : smcq.getUuidList()) {//通过时发消息提醒
						WechatSendMessageVo wcsmv = baseMapper.selectWechatStatementPassMessage(uuid);
						financialSendMessageToWechatService.sendMessageFinancialPassed(wcsmv);
					}
				}*/
				if(smcq.getCheckStatus().equals("5")) {
					if(StringUtil.isEmpty(smcq.getReason())) {
						return ResultVo.getByEnumCode(CommonConstants.AUDIT_NO_PASS_CODE);
					}
					List<String> list = smcq.getUuidList();
					List<WechatSendMessageVo> wcsmvList = baseMapper.selectWechatNotPassStatementMessage(list);
					for (WechatSendMessageVo wcsmv : wcsmvList) {
//						System.out.println(wcsmv.toString());
						financialSendMessageToWechatService.sendMessageFinancialNotPassed(wcsmv);
					}
				}
				if(smcq.getCheckStatus().equals("5")) {
					for (TpmUserStatementModel tusm : tpmList) {
						TpmActivityModel tam = tpmActivityService.queryTpmActivityModel(tusm.getActivity_uuid());
						int valid_work_hours = tusm.getValid_work_hours();
						double real_salary = tam.getUnitPrice();
						if(valid_work_hours < tam.getWorkHours()) {
							real_salary = (real_salary / tam.getWorkHours() * valid_work_hours) + (tusm.getValid_work_minute() / 60.0 * (tam.getUnitPrice() / tam.getWorkHours()));
						}
						real_salary = Math.ceil(real_salary);
						smcq.setReal_salary(real_salary);
						List<String> list = new ArrayList<>();
						list.add(tusm.getUuid());
						smcq.setUuidList(list);
						smcq.setCheckTime(DateUtils.currentTime());
						baseMapper.checkSettlementManagement(smcq);
					}
					
				}else {
					smcq.setCheckTime(DateUtils.currentTime());
					baseMapper.checkSettlementManagement(smcq);
				}
			}
			if(personType == 4) {
				Double realSalaryFlag = null;
				if(smcq.getUuidList().size() == 1 && smcq.getSingleCheck() == 1) {
					if("2".equals(smcq.getCheckStatus())) {
						Double adjust_money = smcq.getAdjust_money();
						if(adjust_money == null) {
							return ResultVo.getByEnumCode(CommonConstants.ADJUST_MONEY_NULL_CODE);
						}
						if(adjust_money != 0) {
							if(StringUtil.isEmpty(smcq.getAdjust_money_reason())) {
								return ResultVo.getByEnumCode(CommonConstants.AUDIT__PASS_CODE);
							}
							String isIncentiveOrReduce = smcq.getIsIncentiveOrReduce();
							if(isIncentiveOrReduce == null) {
								return ResultVo.getByEnumCode(CommonConstants.CHECK_STATUS_CODE);
							}else {
								String uuid = smcq.getUuidList().get(0);
								TpmUserStatementModel tus = tpmUserStatementService.selectById(uuid);
								String activity_uuid = tus.getActivity_uuid();
								Double incentiveAmount = tpmActivityService.queryTpmActivityModel(activity_uuid).getIncentiveAmount();
								if(incentiveAmount == null) {
									incentiveAmount = 0.0;
								}
								Double realSalary = tpmList.get(0).getReal_salary();
								realSalaryFlag = realSalary;
								if("1".equals(isIncentiveOrReduce)) {
									Double incentiveCount = baseMapper.getIncentiveCount(activity_uuid);
									if(incentiveCount == null) {
										incentiveCount = 0.0;  
									}
									incentiveCount = incentiveCount + adjust_money;
									if(incentiveCount > incentiveAmount) {
										log.info(CommonConstants.REWARD_OVER_PREDICTION_MSG);
										return ResultVo.getByEnumCode(CommonConstants.REWARD_OVER_PREDICTION_CODE);
									}
									realSalary += adjust_money;
								}else if("0".equals(isIncentiveOrReduce)){
									realSalary -= adjust_money;
									if(realSalary < 0) {
										realSalary = 0.0;
									}
								}
								smcq.setReal_salary(realSalary);
							}
						}else {
							smcq.setIsIncentiveOrReduce("3");
						}
					}
				}
				smcq.setStatement_check_time(DateUtils.currentTime());
				baseMapper.checkSettlementManagement(smcq);
				TpmOptLogsModel tolm = new TpmOptLogsModel();
				tolm.setType(4);
				String opt_user = userInfo.getEmployeeModel().getPerson_name()+"("+userInfo.getEmployeeModel().getPerson_code()+")";
				tolm.setOptUser(opt_user);
				tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
				String check;
				if(smcq.getCheckStatus().equals("2")) {
					check = "审核通过";
				}else {
					check = "审核不通过";
				}
				List<String> uuidList = smcq.getUuidList();
				for (String uuid : uuidList) {
					TpmUserStatementModel tus = tpmUserStatementService.selectById(uuid);
					String openName = tpmUserBaseInfoService.getOpenName(tus.getOpenid());
					String content = "临促"+openName+"("+tus.getOpenid()+")"+tus.getActivity_time()+"的结算单"+uuid+check;
					if(realSalaryFlag != null && realSalaryFlag != tus.getReal_salary()) {
						content += ";实际工资由"+realSalaryFlag+"元调整为"+tus.getReal_salary()+"元，调整原因为："+smcq.getAdjust_money_reason();
					}
					content += ";备注："+smcq.getAdjust_money_note();
					tolm.setContent(content);
					tpmOptLogsService.insertOptLogs(tolm);
				}
			}
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}

	@Override
	public SettlementManagementVo querySettlementDetails(String uuid) {
		return baseMapper.querySettlementDetails(uuid);
	}

    @Override
    public List<PaymentErrorVo> findUserStatementPaymentError(){
        return baseMapper.findUserStatementPaymentError();
    }

	@Override
	public double findRealSalaryByProjectId(String projectId){
		return baseMapper.findRealSalaryByProjectId(projectId);
	}

    @Override
    public List<TpmUserStatementModel> findActivityUuidByProjectId(String projectId){
        return baseMapper.findActivityUuidByProjectId(projectId);
    }

    @Override
    public List<BorrowOrderVo> importBorrowMoneyList() {
        return baseMapper.importBorrowMoneyList();
    }

	@Override
	public double findTotalSalaryByProjectId(String projectId) {
		return baseMapper.findTotalSalaryByProjectId(projectId);
	}

	@Override
	public ResultVo getToDoList(LoginUserInfo userInfo) {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		if(personType == -1) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
			return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
		}
		TpmToDoListVo tdlv = new TpmToDoListVo();
		String personCode = userInfo.getEmployeeModel().getPerson_code();
		if(personType == 2 || personType == 6) {
			Integer settlementNo = baseMapper.selectFinancialToDoNo(personCode);
			tdlv.setSettlementNo(settlementNo);
		}
		if(personType == 4) {
			Integer settlementNo = baseMapper.selectSettleMentToDoNo(personCode,0);
			tdlv.setSettlementNo(settlementNo);
			Integer attendenceNo = baseMapper.selectSettleMentToDoNo(personCode,1);
			tdlv.setAttendenceNo(attendenceNo);
			Integer activityNo = baseMapper.selectActivityToDoNo(personCode);
			tdlv.setActivityNo(activityNo);
		}
		return ResultVo.getData(ResultVo.SUCCESS, tdlv);
	}

	@Override
	public ResultVo outputAttendance(LoginUserInfo userInfo,AttendanceManagementQuery amq) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		List<String> childsDeptList = new ArrayList<>();
		if(personType == -1) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
			return ResultVo.get(ResultVo.SUCCESS);
		}
		if(personType == 2) {
			childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if(childsDeptList == null || childsDeptList.size() == 0) {
				log.info("部门列表childsDeptList错误，为{}",childsDeptList);
				return ResultVo.get(ResultVo.SUCCESS);
			}
			amq.setList(childsDeptList);
		}
		if(personType == 0 || personType == 6){
			List<String> branchDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
			if(branchDeptList == null || branchDeptList.size() == 0) {
				log.info("部门列表branchDeptList错误，为{}",branchDeptList);
				return ResultVo.get(ResultVo.SUCCESS);
			}
			amq.setList(branchDeptList);
		}
		amq.setPersonCode(userInfo.getEmployeeModel().getPerson_code());
		amq.setPersonType(personType);
		Page<AttendanceManagementVo> page = new Page<>(amq.getCurrent(),amq.getSize());
		List<AttendanceManagementVo> amvList = null;
		if("1".equals(amq.getFlag())) {
			amvList = baseMapper.queryAttendanceManagement(amq);
		}else {
			amvList = baseMapper.queryAttendanceManagement(amq, page);
		}
		String url = attendencePath;
		String path = null;
		if(amvList != null && amvList.size() > 0){
			int no = 1;
			for (AttendanceManagementVo attendanceManagementVo : amvList) {
				attendanceManagementVo.setNo(no+"");
				no++;
			}
			path = createAttendencesXls(amvList,url);
		}
		return ResultVo.getData(ResultVo.SUCCESS, path);
	}
	
	@Override
	public ResultVo outputSettlement(LoginUserInfo userInfo,SettlementManagementQuery smq) throws Exception{

		Integer personType = CheckParamUtils.getPersonType(userInfo);
		List<String> childsDeptList = new ArrayList<>();
		if(personType == -1) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
//			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
			return ResultVo.get(ResultVo.SUCCESS);
		}
		if(personType == 2) {
			childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if(childsDeptList == null || childsDeptList.size() == 0) {
				log.info("部门列表错误，为{}",childsDeptList);
//				throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
				return ResultVo.get(ResultVo.SUCCESS);
			}
			smq.setList(childsDeptList);
		}
		if(personType == 0 || personType == 6){
			List<String> branchDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
			if(branchDeptList == null || branchDeptList.size() == 0) {
				log.info("部门列表branchDeptList错误，为{}",branchDeptList);
				return ResultVo.get(ResultVo.SUCCESS);
			}
			smq.setList(branchDeptList);
		}
		smq.setPersonCode(userInfo.getEmployeeModel().getPerson_code());
		smq.setPersonType(personType);
		if(StringUtil.isNotNull(smq.getFsscLoanBillNo())){
			List<TpmLoanBillModel> tlbmList = tpmLoanBillMapper.selectList(new EntityWrapper<TpmLoanBillModel>().eq("fssc_loan_bill_no", smq.getFsscLoanBillNo()));
			List<String> tList = new ArrayList<>();
			if(tlbmList != null && tlbmList.size() > 0){
				for (TpmLoanBillModel tpmLoanBillModel : tlbmList) {
					tList.add(tpmLoanBillModel.getLoad_bill_uuid());
				}
			}
			smq.setLbuList(tList);
		}
		Page<SettlementManagementVo> page = new Page<>(smq.getCurrent(),smq.getSize());
		List<SettlementManagementVo> list = null;
		if("1".equals(smq.getFlag())) {
			list = baseMapper.querySettlementManagement(smq);
		}else {
			list = baseMapper.querySettlementManagement(smq,page);
		}
		String path = null;
		if(list != null && list.size() > 0) {
			List<String> uuidList = new ArrayList<>();
			Map<String,SettlementManagementVo> map = new HashMap<>();
			for (SettlementManagementVo settlementManagementVo : list) {
				String dept = settlementManagementVo.getOrgCode();
				TpmDeptEmplyeeVo tdev = userRoleInfoMapper.getUserInfo(dept);
				if(tdev != null) {
					settlementManagementVo.setFinancialPersonCode(tdev.getPerson_code());
					settlementManagementVo.setFinancialPersonName(tdev.getPerson_name());
				}
				uuidList.add(settlementManagementVo.getUuid());
				map.put(settlementManagementVo.getUuid(), settlementManagementVo);
			}
			List<TpmAttendenceModel> tamList = tpmAttendenceMapper.selectByTusUuidList(uuidList);
			if(tamList != null && tamList.size() > 0) {
				for (TpmAttendenceModel tam : tamList) {
					SettlementManagementVo smv = map.get(tam.getTus_uuid());
					smv.setAttendencePersonName(tam.getPerson_name());
					smv.setAttendenceTime(tam.getCreate_time());
				}
			}
			for (SettlementManagementVo smv : list) {
				String loadBillUuid = smv.getLoadBillUuid();
				if(loadBillUuid != null){
					TpmLoanBillModel tlbm = tpmLoanBillMapper.selectById(loadBillUuid);
					if(tlbm != null){
						String fsscLoanBillNo = tlbm.getFssc_loan_bill_no();
						smv.setFsscLoanBillNo(fsscLoanBillNo);
					}
				}
			}
			String url = outputSettlementPath;
			if(list != null && list.size() > 0){
				int no = 1;
				for (SettlementManagementVo smv : list) {
					smv.setNo(no+"");
					no++;
				}
			}
			path = createSettlementXls(list,url);
		}
		return ResultVo.getData(ResultVo.SUCCESS, path);
	}

	public String createAttendencesXls(List<AttendanceManagementVo> amvList, String url) throws IOException {
		File tempfile = new File(url);
		if (!tempfile.exists()) {
			tempfile.mkdirs();
		}
		String path = StringUtil.getUUID()+".xls";
		File file = new File(url+"/"+ path);
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("考勤表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row.createCell(0);
		// 设置单元格内容
		cell.setCellValue("考勤管理表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
		HSSFCellStyle  style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont  fontStyle = wb.createFont(); 
		fontStyle.setFontName("微软雅黑"); 
		fontStyle.setFontHeightInPoints((short)16);
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(fontStyle);  
		cell.setCellStyle(style);
		
		HSSFCellStyle  style2 = wb.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		fontStyle.setFontName("宋体"); 
		fontStyle.setFontHeightInPoints((short)12);
		HSSFFont  fontStyle2 = wb.createFont(); 
		style2.setFont(fontStyle2);  
		List<String> headList = getAttendencesHead();
		row = sheet.createRow(1);
		for(int i = 0; i < headList.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(headList.get(i));
			if(i == 11 || i == 10 || i == 13) {
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * 18);
			}
			if(i == 1 || i == 3) {
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * 25);
			}
			style2 = wb.createCellStyle();
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(style2);
		}
		int rowNo = 1;
		for (AttendanceManagementVo amv : amvList) {
			rowNo++;
			row = sheet.createRow(rowNo);
			List<String> list = getAttendencesContent(amv);
			for(int i = 0; i < list.size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(list.get(i));
				cell.setCellStyle(style2);
			}
		}
		FileOutputStream fout = new FileOutputStream(file);
		wb.write(fout);
		fout.close();
		return attendenceURl+"/"+path;
	}

	public List<String> getAttendencesHead() {
		String[] arr = new String[] { "序号", "预算部门","活动负责人","活动名称","姓名","考勤日期","考勤状态","要求工时","打卡工时","实际工时","签到时间","签退时间","审核状态","审核时间"};
		List<String> list = Arrays.asList(arr);
		return list;
	}
	public List<String> getAttendencesContent(AttendanceManagementVo amv) {
		String state = amv.getHours_state();
		if("0".equals(state)){
			state = "未审核";
		}else if("1".equals(state)){
			state = "通过";
		}else{
			state = "不通过";
		}
		String timeState = "异常";
		if(new Integer(amv.getWork_hours()) >= new Integer(amv.getPlanWorkHours())){
			timeState = "正常";
		}
		String[] arr = new String[] {amv.getNo(),amv.getDept(),amv.getPerson_name(),amv.getActivity_name(),amv.getName(),amv.getActivity_time(),timeState,amv.getPlanWorkHours()+"时",
				amv.getWork_hours()+"时"+amv.getWork_minute()+"分",amv.getValid_work_hours()+"时"+amv.getValid_work_minute()+"分",amv.getWork_start_time(),amv.getWork_end_time(),state,amv.getCheck_time()};
		List<String> list = Arrays.asList(arr);
		return list;
	}

	
	public String createSettlementXls(List<SettlementManagementVo> smvList, String url) throws IOException {
		File tempfile = new File(url);
		if (!tempfile.exists()) {
			tempfile.mkdirs();
		}
		String path = StringUtil.getUUID()+".xls";
		File file = new File(url+"/"+ path);
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("结算表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row.createCell(0);
		// 设置单元格内容
		cell.setCellValue("结算管理表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 23));
		HSSFCellStyle  style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont  fontStyle = wb.createFont(); 
		fontStyle.setFontName("微软雅黑"); 
		fontStyle.setFontHeightInPoints((short)16);
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(fontStyle);  
		cell.setCellStyle(style);
		
		HSSFCellStyle  style2 = wb.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		fontStyle.setFontName("宋体"); 
		fontStyle.setFontHeightInPoints((short)12);
		HSSFFont  fontStyle2 = wb.createFont(); 
		style2.setFont(fontStyle2);  
		List<String> headList = getSettlementHead();
		row = sheet.createRow(1);
		for(int i = 0; i < headList.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(headList.get(i));
			if(i == 4 || i == 7 || i == 15 || i == 16 || i == 17 || i == 18 ||  i == 19 || i == 21 || i == 22 || i == 23 ) {
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * 18);
			}
			if(i == 1 || i == 5 || i == 14) {
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * 25);
			}
			style2 = wb.createCellStyle();
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(style2);
		}
		int rowNo = 1;
		for (SettlementManagementVo smv : smvList) {
			rowNo++;
			row = sheet.createRow(rowNo);
			List<String> list = getSettlementContent(smv);
			for(int i = 0; i < list.size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(list.get(i));
				cell.setCellStyle(style2);
			}
		}
		FileOutputStream fout = new FileOutputStream(file);
		wb.write(fout);
		fout.close();
		return outputSettlementURl+"/"+path;
	}

	public List<String> getSettlementHead() {
		String[] arr = new String[] { "序号", "预算部门","活动负责人","财务负责人","借款单号","活动名称","姓名","考勤日期","计划工时","实际工时","标准日薪","实际日薪","奖惩",
				"结算总费用","备注","活动负责人审核状态","活动负责人考勤审核时间","活动负责人结算审核时间","财务审核状态","财务审核时间","付款状态","预计结算日期","考勤表生成人员","考勤表生成时间"};
		List<String> list = Arrays.asList(arr);
		return list;
	}
	public List<String> getSettlementContent(SettlementManagementVo smv) {
		String adjustMoney = "";
		String note = "";
		if(smv.getIncentive_amount() != null){
			adjustMoney = "+"+smv.getIncentive_amount()+"元";
			note = smv.getIncentive_reason();
		}
		if(smv.getReduce_money() != null){
			adjustMoney = "-"+smv.getReduce_money()+"元";
			note = smv.getReduce_money_reason();
		}
		double real_salary = Double.valueOf(smv.getUnit_price());
		if(Integer.valueOf(smv.getValid_work_hours()) < Integer.valueOf(smv.getPlanWorkHour())) {
			real_salary = real_salary / new Double(smv.getPlanWorkHour()) * Integer.valueOf(smv.getValid_work_hours());
			double minSalary = smv.getValid_work_minute() / 60.0 * (Double.valueOf(smv.getUnit_price()) / Double.valueOf(smv.getPlanWorkHour()));
			real_salary = real_salary + minSalary;
		}
		String managerState = "";
		String financialState = "";
		if(smv.getRequest_state().equals("0")){
			managerState = "不通过";
		}
		if(smv.getRequest_state().equals("1")){
			managerState = "未审核";
		}
		if(smv.getRequest_state().equals("2")){
			managerState = "通过";
			financialState = "未审核";
		}
		if(smv.getRequest_state().equals("5")){
			managerState = "未审核";
			financialState = "不通过";
		}
		if(smv.getRequest_state().equals("4")){
			managerState = "通过";
			financialState = "通过";
		}
		String payState = "第三方支付";
		if("公司直付".equals(smv.getSettlementMethod())){
			if(smv.getPay_state().equals("-1")){
				payState = "未付款";
			}else if(smv.getPay_state().equals("1")){
				payState = "申请付款中";
			}else if(smv.getPay_state().equals("0")){
				payState = "失败";
			}else{
				payState = "付款成功";
			}
		}
		String[] arr = new String[] {smv.getNo(),smv.getDept(),smv.getManagerName(),smv.getFinancialPersonName(),smv.getFsscLoanBillNo(),smv.getActivity_name(),smv.getName(),
				smv.getActivity_time(),smv.getPlanWorkHour()+"时",smv.getValid_work_hours()+"时"+smv.getValid_work_minute()+"分",smv.getUnit_price()+"元",real_salary+"元",
				adjustMoney,smv.getReal_salary()+"元",note,managerState,smv.getCheckTime(),smv.getStatementCheckTime(),financialState,smv.getFinancialCheckTime(),
				payState,smv.getSettlement_cycle(),smv.getAttendencePersonName(),smv.getAttendenceTime()};
		List<String> list = Arrays.asList(arr);
		return list;
	}

	@Override
	public String downloadSettlement(String path) {
		String filePath = attendencePath + "/outputSettlement" + path;
		return filePath;
	}


	public MailVo findMailOfManagerAndFinacical(String paymentId) {

    	MailVo mailVo = baseMapper.getMail(paymentId);
    	return mailVo;
	}

}
