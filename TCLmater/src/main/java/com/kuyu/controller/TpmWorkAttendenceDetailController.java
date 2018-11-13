package com.kuyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.model.TpmWorkAttendenceDetailModel;
import com.kuyu.service.TpmActivityService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.service.TpmUserStatementService;
import com.kuyu.service.TpmWorkAttendenceDetailService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.AttendanceManagementVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.TpmUserStatementQuery;
import com.kuyu.vo.TpmUserStatementVo;
import com.kuyu.vo.TpmWorkAttendenceVo;
import com.kuyu.vo.query.AttendanceManagementCheckQuery;
import com.kuyu.vo.query.AttendanceManagementQuery;

@Api(tags = "考勤接口")
@AOP_Controller_LOG
@RequestMapping("/workAttendenceDetail")
public class TpmWorkAttendenceDetailController extends BaseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TpmWorkAttendenceDetailService tpmWorkAttendenceDetailService;

	@Resource
	private TpmUserStatementService tpmUserStatementService;

	@Resource
	private TpmActivityService tpmActivityService;

	@Resource
	private TpmUserBaseInfoService tpmUserBaseInfoService;

	/**
	 * 打卡,在明细表中插入一条记录，如果是初次打卡，在结算表中新增打卡记录，否则更新下班打卡时间
	 * 
	 * @param tpmWorkAttendenceDetailModel
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "打卡", notes = "临促打卡，当天第一次打卡，新增结算信息，否则更新")
	@RequestMapping(value = "/signIn", method = { RequestMethod.POST })
	public ResultVo signIn(
			@ApiParam(value = "打卡实体类，不需要传入id，打卡时间和openid", required = true) @RequestBody TpmWorkAttendenceDetailModel tpmWorkAttendenceDetailModel)
			throws Exception {
		String openid = getOpenid();
		tpmWorkAttendenceDetailModel.setOpenid(openid);
		if (StringUtil.isEmpty(tpmWorkAttendenceDetailModel.getOpenid())) {
			log.info(CommonConstants.OPENID_NOT_FOUND_MSG);
			return ResultVo.getByEnumCode(CommonConstants.OPENID_NOT_FOUND_CODE);
		}
		if (StringUtil.isEmpty(tpmWorkAttendenceDetailModel.getUrl())) {
			log.info(CommonConstants.URL_IS_NULL_MSG);
			return ResultVo.getByEnumCode(CommonConstants.URL_IS_NULL_CODE);
		}
		if (StringUtil.isEmpty(tpmWorkAttendenceDetailModel.getActivity_uuid())) {
			log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
		}
		TpmActivityModel tpmActivityModel = tpmActivityService
				.queryTpmActivityModel(tpmWorkAttendenceDetailModel.getActivity_uuid());
		if (tpmActivityModel == null) {
			log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
			// throw new ParamException(ResultVoUtils.paramException("活动id不存在",
			// "activity_uuid"));
		}
		TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
		tpmUserStatementModel.setActivity_uuid(tpmWorkAttendenceDetailModel.getActivity_uuid());
		tpmUserStatementModel.setOpenid(tpmWorkAttendenceDetailModel.getOpenid());
		tpmUserStatementModel.setActivity_time(DateUtils.today());
		TpmUserStatementModel tusm = tpmUserStatementService.queryUserStatement(tpmUserStatementModel);
		if (tusm == null) {
			String clock_time = DateUtils.currentTime();
			tpmWorkAttendenceDetailModel.setClock_time(clock_time);
			tpmWorkAttendenceDetailService.signIn(tpmWorkAttendenceDetailModel);
			tpmUserStatementModel.setWork_start_imgurl(tpmWorkAttendenceDetailModel.getUrl());
			tpmUserStatementModel.setWork_start_time(clock_time);
			return tpmUserStatementService.insertRecords(tpmUserStatementModel);
		}
		if (tusm.getHours_state() != 0) {
			log.info(CommonConstants.CANNOT_SIGN_IN_MSG);
			// throw new ParamException(ResultVoUtils.paramException("结算单已审核，您不能再打卡", ""));
			return ResultVo.getByEnumCode(CommonConstants.CANNOT_SIGN_IN_CODE);
		}
		String clock_time = DateUtils.currentTime();
		tpmWorkAttendenceDetailModel.setClock_time(clock_time);
		tpmWorkAttendenceDetailService.signIn(tpmWorkAttendenceDetailModel);
		double unitPrice = tpmActivityModel.getUnitPrice();
		tpmUserStatementModel.setReal_salary(unitPrice);
		tpmUserStatementModel.setWork_end_imgurl(tpmWorkAttendenceDetailModel.getUrl());
		tpmUserStatementModel.setPlanWorkHours(tpmActivityModel.getWorkHours());
		tpmUserStatementModel.setWork_end_time(clock_time);
		return tpmUserStatementService.updateRecords(tpmUserStatementModel);
	}

	/**
	 * 查询考勤记录
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询考勤记录", notes = "根据活动id和openid以及月份查询打卡记录", response = TpmUserStatementVo.class)
	@RequestMapping(value = "/queryRecords", method = { RequestMethod.POST })
	public ResultVo queryRecords(
			@ApiParam(value = "活动id", required = false) @RequestParam(required = false) String activity_uuid,
			@ApiParam(value = "查询月份，默认当前月(YYYY-MM)", required = false) @RequestParam(required = false) String month)
			throws Exception {
		TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
		tpmUserStatementModel.setActivity_uuid(activity_uuid);
		tpmUserStatementModel.setMonth(month);
		tpmUserStatementModel.setOpenid(checkOpenid());
		if (StringUtil.isEmpty(tpmUserStatementModel.getOpenid())) {
			log.info(CommonConstants.OPENID_NOT_FOUND_MSG);
			return ResultVo.getData(ResultVo.SUCCESS, null);
		}
		ResultVo resultVo = tpmUserStatementService.queryRecords(tpmUserStatementModel);
		List<TpmWorkAttendenceVo> records = new ArrayList<>();
		List<TpmUserStatementModel> tusList = (List<TpmUserStatementModel>) resultVo.getData();
		TpmUserStatementVo tpmUserStatementVo = new TpmUserStatementVo();
		int countNormal = 0;
		int countAbnormal = 0;
		if (tusList != null && tusList.size() > 0) {
			Map<String, TpmUserStatementModel> tusmMap = new LinkedHashMap<>();
			for (TpmUserStatementModel tusm : tusList) {
				if (!StringUtil.isNotNull(tusmMap.get(tusm.getUuid()))) {
					int workHours = tusm.getPlanWorkHours();
					if (!tusm.getActivity_time().equals(DateUtils.today())) {
						if (tusm.getWork_hours() >= workHours) {
							countNormal++;
						} else {
							countAbnormal++;
						}
					}
					tusm.setStart_clock_address(tusm.getClock_city() + tusm.getClock_address());
				} 
				else {
					TpmUserStatementModel t = tusmMap.get(tusm.getUuid());
					t.setEnd_clock_address(tusm.getClock_city() + tusm.getClock_address());
					tusm = t;
				}
				tusmMap.put(tusm.getUuid(), tusm);
			}
			for (Map.Entry<String, TpmUserStatementModel> entry : tusmMap.entrySet()) {
				TpmUserStatementModel tusm = entry.getValue();
				TpmWorkAttendenceVo tav = new TpmWorkAttendenceVo();
				tav.setWorkStartAddress(tusm.getStart_clock_address());
				tav.setWorkEndAddress(tusm.getEnd_clock_address());
				tav.setActivityTime(tusm.getActivity_time());
				String endTime = tusm.getWork_end_time();
				if (StringUtil.isNotNull(endTime)) {
					tav.setWorkEndTime(endTime.substring(11));
				}
				tav.setWorkEndUrl(tusm.getWork_end_imgurl());
				tav.setWorkStartTime(tusm.getWork_start_time().substring(11));
				tav.setWorkStartUrl(tusm.getWork_start_imgurl());
				tav.setWorkHours(tusm.getWork_hours());
				tav.setWorkMinutes(tusm.getWork_minute());
				tav.setPlanWorkHours(tusm.getPlanWorkHours());
				records.add(tav);
			}
			tpmUserStatementVo.setRecords(records);
			tpmUserStatementVo.setCountAbnormal(countAbnormal);
			tpmUserStatementVo.setCountNormal(countNormal);
		}
		return ResultVo.getData(ResultVo.SUCCESS, tpmUserStatementVo);
	}

	/**
	 * 考勤管理查询
	 * 
	 * @param activity_uuid
	 * @param activity_time
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "考勤管理查询", notes = "根据活动id和活动时间查询考勤", response = TpmUserStatementModel.class)
	@RequestMapping(value = "/attendanceManagement", method = { RequestMethod.GET })
	public ResultVo attendanceManagement(@ApiParam(value = "活动id", required = true) @RequestParam String activity_uuid,
			@ApiParam(value = "活动时间(YYYY-MM-DD)", required = false) @RequestParam(required = false) String activity_time,
			@ApiParam(value = "查询审核的状态：0全部，1待审核，默认查全部", required = false) @RequestParam(required = false,defaultValue = "0") String state,
			@ApiParam(value = "当前页，默认为1", required = false) @RequestParam(required = false, defaultValue = "1") Integer current,
			@ApiParam(value = "每页大小，默认为10", required = false) @RequestParam(required = false, defaultValue = "10") Integer size)
			throws Exception {
		if (StringUtil.isEmpty(activity_uuid)) {
			log.info("activity_uuid错误，为{}", activity_uuid);
			// throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getData(ResultVo.SUCCESS, null);
		}
		TpmActivityModel tpmActivityModel = tpmActivityService.queryTpmActivityModel(activity_uuid);
		if (tpmActivityModel == null) {
			log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
			// throw new ParamException(ResultVoUtils.paramException("活动id不存在",
			// "activity_uuid"));
			return ResultVo.getData(ResultVo.SUCCESS, null);
		}
		if (StringUtil.isEmpty(activity_time)) {
			activity_time = DateUtils.today();
		}
		//
		Page<TpmUserStatementModel> page = new Page<>(current, size);
		Map<String, Object> map = new HashMap<>();
		map.put("activity_uuid", activity_uuid);
		map.put("activity_time", activity_time);
		map.put("state", state);
		List<TpmUserStatementModel> tusms = tpmUserStatementService.attendanceManagementBypage(map, page);
		List<TpmUserStatementModel> tusmList = new ArrayList<TpmUserStatementModel>();
		for (TpmUserStatementModel tpmUserStatementModel : tusms) {
			String openid = tpmUserStatementModel.getOpenid();
			String activityTime = tpmUserStatementModel.getActivity_time();
			Map<String, Object> map2 = new HashMap<>();
			map2.put("activity_uuid", activity_uuid);
			map2.put("openid", openid);
			map2.put("dateTime", activityTime);
			List<TpmWorkAttendenceDetailModel> twadmList = tpmWorkAttendenceDetailService.queryList(map2);
			if (twadmList.size() > 0 && twadmList != null) {
				String start_clock_address = twadmList.get(0).getClock_city() + twadmList.get(0).getClock_address();
				String end_clock_address;
				if (twadmList.size() > 1) {
					int lastIndext = twadmList.size() - 1;
					end_clock_address = twadmList.get(lastIndext).getClock_city()
							+ twadmList.get(lastIndext).getClock_address();
					tpmUserStatementModel.setEnd_clock_address(end_clock_address);
				}
				tpmUserStatementModel.setStart_clock_address(start_clock_address);
			}
			String startTime = tpmUserStatementModel.getWork_start_time().substring(11);
			String endTime = tpmUserStatementModel.getWork_end_time();
			if (StringUtil.isNotNull(endTime)) {
				endTime = endTime.substring(11);
			}
			tpmUserStatementModel.setWork_start_time(startTime);
			tpmUserStatementModel.setWork_end_time(endTime);
			tpmUserStatementModel.setReason(tpmUserStatementModel.getAdjust_hours_note());
			String name = tpmUserBaseInfoService.getOpenName(openid);
			int planWorkHours = tpmActivityModel.getWorkHours();
			tpmUserStatementModel.setPlanWorkHours(planWorkHours);
			tpmUserStatementModel.setName(name);
			tusmList.add(tpmUserStatementModel);
		}
		page.setRecords(tusmList);
		return ResultVo.getData(ResultVo.SUCCESS, page);
	}

	/**
	 * 调整工时
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "调整工时", notes = "当工时状态为待审核，根据活动id，活动时间，临促id修改工时")
	@RequestMapping(value = "/adjustWorkingHours", method = { RequestMethod.POST })
	public ResultVo adjustWorkingHours(
			@ApiParam(value = "结算单查询类", required = true) @RequestBody TpmUserStatementQuery tpmUserStatementQuery)
			throws Exception {
		String openid = tpmUserStatementQuery.getOpenid();
		String activity_uuid = tpmUserStatementQuery.getActivity_uuid();
		String activity_time = tpmUserStatementQuery.getActivity_time();
		Integer valid_work_hours = tpmUserStatementQuery.getValid_work_hours();
		String reduce_hours_reason = tpmUserStatementQuery.getReduce_hours_reason();
		Integer hours_state = tpmUserStatementQuery.getHours_state();
		String reason = tpmUserStatementQuery.getReason();
		if(StringUtil.isNotNull(reduce_hours_reason)) {
			reduce_hours_reason = StringUtil.cleanXSS(reduce_hours_reason);
		}
		if(StringUtil.isNotNull(reason)) {
			reason = StringUtil.cleanXSS(reason);
		}
		if (StringUtil.isEmpty(activity_uuid)) {
			log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
			// throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
		}
		if (StringUtil.isEmpty(openid)) {
			log.info(CommonConstants.OPENID_NOT_FOUND_MSG);
			return ResultVo.getByEnumCode(CommonConstants.OPENID_NOT_FOUND_CODE);
		}
		if (StringUtil.isEmpty(activity_time)) {
			log.info(CommonConstants.ACTIVITY_TIME_IS_NULL_MSG);
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_TIME_IS_NULL_CODE);
		}
		if (!StringUtil.isNotNull(valid_work_hours)) {
			log.info(CommonConstants.VALID_WORK_HOURS_NULL_MSG);
			return ResultVo.getByEnumCode(CommonConstants.VALID_WORK_HOURS_NULL_CODE);
		}
		TpmActivityModel tpmActivityModel = tpmActivityService.queryTpmActivityModel(activity_uuid);
		if (tpmActivityModel == null) {
			log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
			// throw new ParamException(ResultVoUtils.paramException("活动id不存在",
			// "activity_uuid"));
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
		}
		if (tpmUserBaseInfoService.getUserBaseInfo(openid) == null) {
			log.info(CommonConstants.OPENID_NOT_FOUND_MSG);
			// throw new ParamException(ResultVoUtils.paramException("临促id不存在", "openid"));
			return ResultVo.getByEnumCode(CommonConstants.OPENID_NOT_FOUND_CODE);
		}
		TpmUserStatementModel tm = new TpmUserStatementModel();
		tm.setOpenid(openid);
		tm.setActivity_uuid(activity_uuid);
		tm.setActivity_time(activity_time);
		tm = tpmUserStatementService.queryUserStatement(tm);
		if(tm.getHours_state() != 0) {
			return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_DONE_CODE);
		}
		if(hours_state == 1) {
			if(tm.getWork_hours() != valid_work_hours || tm.getWork_minute()!=tpmUserStatementQuery.getWork_minute()){
				if(StringUtil.isEmpty(reduce_hours_reason)) {
					return ResultVo.getByEnumCode(CommonConstants.AUDIT__PASS_CODE);
				}
			}
			if(tpmUserStatementQuery.getValid_work_hours() == 0 && tpmUserStatementQuery.getWork_minute() == 0) {
				return ResultVo.getByEnumCode(CommonConstants.WORK_TIME_ERROR_CODE);
			}
		}
		if (hours_state == 2) {
			if (!StringUtil.isNotNull(reason)) {
				log.info(CommonConstants.AUDIT_NO_PASS_MSG);
				return ResultVo.getByEnumCode(CommonConstants.AUDIT_NO_PASS_CODE);
			}
		}
		TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
		tpmUserStatementModel.setOpenid(openid);
		tpmUserStatementModel.setActivity_uuid(activity_uuid);
		tpmUserStatementModel.setActivity_time(activity_time);
		tpmUserStatementModel.setValid_work_hours(valid_work_hours);
		tpmUserStatementModel.setValid_work_minute(tpmUserStatementQuery.getWork_minute());
		tpmUserStatementModel.setReduce_hours_reason(reduce_hours_reason);
		tpmUserStatementModel.setHours_state(hours_state);
		tpmUserStatementModel.setReason(reason);
		tpmUserStatementModel.setCheck_time(DateUtils.currentTime());
		double unitPrice = tpmActivityModel.getUnitPrice();
		tpmUserStatementModel.setUnit_price(unitPrice);
		tpmUserStatementModel.setPlanWorkHours(tpmActivityModel.getWorkHours());
		tpmUserStatementService.adjustWorkingHours(tpmUserStatementModel, getUserInfo());
		return ResultVo.get(ResultVo.SUCCESS);
	}

	/**
	 * PC考勤管理查询
	 * 
	 * @param amq
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "PC考勤管理查询", notes = "考勤管理查询", response = AttendanceManagementVo.class)
	@RequestMapping(value = "queryAttendanceManagement", method = { RequestMethod.POST })
	public ResultVo queryAttendanceManagement(
			@ApiParam(value = "PC考勤管理查询类", required = true) @RequestBody(required = true) AttendanceManagementQuery amq)
			throws Exception {
		amq = (AttendanceManagementQuery) CheckParamUtils.trimWithObjectField(amq);
		Page<AttendanceManagementVo> page = tpmUserStatementService.queryAttendanceManagement(getUserInfo(), amq);
		ResultVo resultVo = new ResultVo();
		resultVo.setCode(ResultVo.SUCCESS);
		resultVo.setData(page);
		return resultVo;
	}

	/**
	 * PC考勤管理详情查询
	 * 
	 * @param uuid
	 * @return
	 */
	@ApiOperation(value = "PC考勤管理详情查询", notes = "考勤管理详情查询", response = TpmUserStatementModel.class)
	@RequestMapping(value = "queryAttendanceDetails", method = { RequestMethod.GET })
	public ResultVo queryAttendanceDetails(
			@ApiParam(value = "结算单uuid", required = true) @RequestParam(required = true) String uuid) {
		TpmUserStatementModel tpmUserStatementModel = tpmUserStatementService.queryAttendanceDetails(uuid);
		if (StringUtil.isNotNull(tpmUserStatementModel)) {
			String openid = tpmUserStatementModel.getOpenid();
			String activityTime = tpmUserStatementModel.getActivity_time();
			String activity_uuid = tpmUserStatementModel.getActivity_uuid();
			Map<String, Object> map2 = new HashMap<>();
			map2.put("activity_uuid", activity_uuid);
			map2.put("openid", openid);
			map2.put("dateTime", activityTime);
			List<TpmWorkAttendenceDetailModel> twadmList = tpmWorkAttendenceDetailService.queryList(map2);
			if (twadmList.size() > 0 && twadmList != null) {
				String start_clock_address = twadmList.get(0).getClock_city() + twadmList.get(0).getClock_address();
				String end_clock_address;
				if (twadmList.size() > 1) {
					int lastIndext = twadmList.size() - 1;
					end_clock_address = twadmList.get(lastIndext).getClock_city()
							+ twadmList.get(lastIndext).getClock_address();
					tpmUserStatementModel.setEnd_clock_address(end_clock_address);
				}
				tpmUserStatementModel.setStart_clock_address(start_clock_address);
			}
			String startTime = tpmUserStatementModel.getWork_start_time().substring(11);
			String endTime = tpmUserStatementModel.getWork_end_time();
			if (StringUtil.isNotNull(endTime)) {
				endTime = endTime.substring(11);
			}
			tpmUserStatementModel.setWork_start_time(startTime);
			tpmUserStatementModel.setWork_end_time(endTime);

		}
		return ResultVo.getData(ResultVo.SUCCESS, tpmUserStatementModel);
	}

	/**
	 * PC考勤管理审核
	 * 
	 * @param amcq
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "PC考勤管理审核", notes = "考勤管理审核")
	@RequestMapping(value = "checkAttendanceManagement", method = { RequestMethod.POST })
	public ResultVo checkAttendanceManagement(
			@ApiParam(value = "PC考勤审核查询类", required = true) @RequestBody(required = true) AttendanceManagementCheckQuery amcq)
			throws Exception {
		if (amcq.getUuidList().size() == 0 || amcq == null) {
			log.info(CommonConstants.SETTLEMENT_IS_NULL_MSG);
			// throw new ParamException(ResultVoUtils.paramException("结算单uuid列表为空",
			// "uuidList"));
			return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_IS_NULL_CODE);
		}
		if (!StringUtil.isNotNull(amcq.getCheckStatus())) {
			// throw new ParamException(ResultVoUtils.paramException("审核状态为空",
			// "checkStatus"));
			log.info(CommonConstants.CHECK_STATUS_CODE);
			return ResultVo.getByEnumCode(CommonConstants.CHECK_STATUS_CODE);
		}
		if(StringUtil.isNotNull(amcq.getReduce_hours_reason())) {
			String reason = StringUtil.cleanXSS(amcq.getReduce_hours_reason());
			amcq.setReduce_hours_reason(reason);
		}
		if(StringUtil.isNotNull(amcq.getAdjust_hours_note())) {
			String note = StringUtil.cleanXSS(amcq.getAdjust_hours_note());
			amcq.setReduce_hours_reason(note);
		}
		if (amcq.getUuidList().size() == 1 && amcq.getSingleCheck() == 1) {
			if (!StringUtil.isNotNull(amcq.getValid_work_hours())) {
				return ResultVo.getByEnumCode(CommonConstants.VALID_WORK_HOURS_NULL_CODE);
			}
			TpmUserStatementModel tpmUserStatementModel = tpmUserStatementService
					.queryAttendanceDetails(amcq.getUuidList().get(0));
			String activity_uuid = tpmUserStatementModel.getActivity_uuid();
			TpmActivityModel tpmActivityModel = tpmActivityService.queryTpmActivityModel(activity_uuid);
			if (tpmActivityModel == null) {
				log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
				return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
			}
			if(amcq.getCheckStatus().equals("1")) {
				if(amcq.getValid_work_hours() == 0 && amcq.getWork_minute() == 0) {
					return ResultVo.getByEnumCode(CommonConstants.WORK_TIME_ERROR_CODE);
				}
				if(tpmUserStatementModel.getWork_hours() != amcq.getValid_work_hours()
					|| tpmUserStatementModel.getWork_minute()!=amcq.getWork_minute()) {
					if(StringUtil.isEmpty(amcq.getReduce_hours_reason())) {
						return ResultVo.getByEnumCode(CommonConstants.AUDIT__PASS_CODE);
					}
					int hours = amcq.getValid_work_hours();
					double real_salary = tpmActivityModel.getUnitPrice();
					int setPlanWorkHours = tpmActivityModel.getWorkHours();
					if (hours < setPlanWorkHours) {
						real_salary = real_salary / setPlanWorkHours * hours;
						real_salary = real_salary + (amcq.getWork_minute() / 60.0
								* (tpmActivityModel.getUnitPrice() / tpmActivityModel.getWorkHours()));
					}
					real_salary = Math.ceil(real_salary);
					amcq.setReal_salary(real_salary);
					amcq.setValid_work_hours(hours);
				}
			}
		}
		return tpmUserStatementService.checkAttendanceManagement(getUserInfo(), amcq);
	}
	
	
	@ApiOperation(value = "查询工作时间", notes = "根据活动id查询当天已经工作的时间")
	@RequestMapping(value = "queryWorkTime", method = { RequestMethod.GET})
	public ResultVo queryWorkTime(
			@ApiParam(value = "活动uuid", required = true) @RequestParam(required = true) String activityUuid	) throws ParseException {
		if(StringUtil.isEmpty(activityUuid)) {
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
		}
		TpmActivityModel tam = tpmActivityService.queryTpmActivityModel(activityUuid);
		if(tam == null) {
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
		}
		String openid = getOpenid();
		if(StringUtil.isEmpty(openid)) {
			return ResultVo.getByEnumCode(CommonConstants.OPENID_NOT_FOUND_CODE);
		}
		TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
		tpmUserStatementModel.setActivity_uuid(activityUuid);
		tpmUserStatementModel.setOpenid(openid);
		tpmUserStatementModel.setActivity_time(DateUtils.today());
		TpmUserStatementModel tusm = tpmUserStatementService.queryUserStatement(tpmUserStatementModel);
		if(tusm != null) {
			String workStartTime = tusm.getWork_start_time();
			String workEndTime = DateUtils.currentTime();
			long d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(workStartTime).getTime();
			long d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(workEndTime).getTime();
			int hours = (int) ((d2 - d1) / (60 * 60 * 1000));
			int minutes = (int)((d2 - d1 - hours * 60 * 60 * 1000) / (60 * 1000));
			int planWorkHours = tam.getWorkHours();
			tpmUserStatementModel.setPlanWorkHours(planWorkHours);
			tpmUserStatementModel.setWork_hours(hours);
			tpmUserStatementModel.setWork_minute(minutes);
			return ResultVo.getData(ResultVo.SUCCESS, tpmUserStatementModel);
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}
	
	@ApiOperation(value = "考勤管理导出")
	@RequestMapping(value = "/outputAttendance",method = {RequestMethod.POST})
	public ResultVo outputAttendance(@ApiParam(value = "PC考勤管理查询类", required = true) @RequestBody(required = true) AttendanceManagementQuery amq) throws Exception {
		amq = (AttendanceManagementQuery) CheckParamUtils.trimWithObjectField(amq);
		
		return tpmUserStatementService.outputAttendance(getUserInfo(),amq);
	}
}
