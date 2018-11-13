package com.kuyu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.model.TpmWorkAttendenceDetailModel;
import com.kuyu.service.TpmActivityService;
import com.kuyu.service.TpmFinancialService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.service.TpmUserStatementService;
import com.kuyu.service.TpmWorkAttendenceDetailService;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ActivityDetailVo;
import com.kuyu.vo.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "财务审核接口")
@AOP_Controller_LOG
@RequestMapping("/tpmFinancial")
public class TpmFinancialController extends BaseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TpmFinancialService tpmFinancialService;

	@Resource
	private TpmActivityService tpmActivityService;
	
	@Resource
	private TpmUserStatementService tpmUserStatementService;
	
	@Resource
	private TpmUserBaseInfoService tpmUserBaseInfoService;
	
	@Resource
	private TpmWorkAttendenceDetailService tpmWorkAttendenceDetailService;
	/**
	 * 根据财务人员工号查询他所负责的活动
	 * 
	 * @param person_code
	 * @return
	 * @throws ParamException
	 */
	@ApiOperation(value = "查询活动列表", notes = "根据财务人员工号查询他所负责的活动",response = ActivityDetailVo.class)
	@RequestMapping(value = "/queryActivityList", method = { RequestMethod.GET })
	public ResultVo queryActivityList(
			@ApiParam(value = "审核状态，1为全部，2为审核完成，3未有待审核；默认为1",required=false) @RequestParam(required = false,defaultValue = "1") String state,
			@ApiParam(value = "当前页，默认为1",required=false) @RequestParam(required = false,defaultValue = "1") Integer current,
			@ApiParam(value = "每页大小，默认为10",required=false) @RequestParam(required = false,defaultValue = "10") Integer size
			) throws ParamException {
		String person_code = getPersonCode();/*person_code = "12312312";*/
		if (StringUtil.isEmpty(person_code)) {
			log.info("person_code错误，为{}",person_code);
//			throw new ParamException(ResultVoUtils.paramException("员工工号不存在", "person_code"));
			return ResultVo.getData(ResultVo.SUCCESS, null);
		}
		return ResultVo.getData(ResultVo.SUCCESS, tpmFinancialService.queryActivity(person_code,state,current,size));
	
	}
	/**
	 * 财务人员根据活动id查询每个活动所有的结算单
	 * @param activityUuid
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询结算单", notes = "财务人员根据活动id查询所有结算单",response = TpmUserStatementModel.class)
	@RequestMapping(value = "/queryStatement", method = {RequestMethod.GET})
	public ResultVo queryStatement(@ApiParam(value = "活动id", required = true) @RequestParam String activityUuid
//					@ApiParam(value = "活动时间(YYYY-MM-DD)", required = false) @RequestParam(required = false) String activityTime
			) throws Exception {
		if (StringUtil.isEmpty(activityUuid)) {
			log.info("activityUuid错误，为{}",activityUuid);
//			throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getData(ResultVo.SUCCESS, null);
		}
//		if(StringUtil.isEmpty(activityTime)){
//			activityTime = DateUtils.today();
//		}
		TpmActivityModel tpmActivityModel = tpmActivityService.queryTpmActivityModel(activityUuid);
		if (tpmActivityModel == null) {
			log.info("activityUuid错误，活动不存在",activityUuid);
//			throw new ParamException(ResultVoUtils.paramException("活动id不存在", "activityUuid"));
			return ResultVo.getData(ResultVo.SUCCESS, null);
		}		
		double unit_price = tpmActivityModel.getUnitPrice();
		int workHours = tpmActivityModel.getWorkHours();
		Map<String, Object> map = new HashMap<>();
		map.put("activity_uuid", activityUuid);
//		map.put("activity_time", activityTime);
		List<TpmUserStatementModel> list = tpmUserStatementService.attendanceManagement(map);
		List<TpmUserStatementModel> resultList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (TpmUserStatementModel tpmUserStatementModel : list) {
				tpmUserStatementModel.setUnit_price(unit_price);
				String openid = tpmUserStatementModel.getOpenid();
				String name = tpmUserBaseInfoService.getOpenName(openid);
				String activity_time = tpmUserStatementModel.getActivity_time();
				Map<String,Object> map2 = new HashMap<>();
				map2.put("activity_uuid", activityUuid);
				map2.put("openid", openid);
				map2.put("dateTime", activity_time);
				List<TpmWorkAttendenceDetailModel> twadmList = tpmWorkAttendenceDetailService.queryList(map2);
				if(twadmList.size() > 0 && twadmList != null) {
					String start_clock_address = twadmList.get(0).getClock_city()+twadmList.get(0).getClock_address();
					String end_clock_address;
					if(twadmList.size() > 1) {
						int lastIndext = twadmList.size() - 1;
						end_clock_address = twadmList.get(lastIndext).getClock_city() + twadmList.get(lastIndext).getClock_address();
						tpmUserStatementModel.setEnd_clock_address(end_clock_address);
					}
					tpmUserStatementModel.setStart_clock_address(start_clock_address);
				}
				TpmUserStatementModel tsm = tpmActivityService.getManagerInfo(activityUuid);
				tpmUserStatementModel.setPerson_name(tsm.getPerson_name());
				String mobile = tsm.getMobile();
				if(StringUtil.isNotNull(mobile)) {
					tpmUserStatementModel.setMobile(mobile);
				}
				tpmUserStatementModel.setSettlement_method(tsm.getSettlement_method());
				tpmUserStatementModel.setName(name);
				tpmUserStatementModel.setPlanWorkHours(workHours);
				resultList.add(tpmUserStatementModel);
			}
		}
		return ResultVo.getData(ResultVo.SUCCESS, resultList);
	}
	
	/**
	 * 财务人员审核结算单
	 * @param id
	 * @param requestState
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "财务人员审核结算单", notes = "财务人员审核结算单")
	@RequestMapping(value = "/financialAudit", method = {RequestMethod.GET})
	public ResultVo financialAudit(	@ApiParam(value = "结算单id", required = true)  @RequestParam String id,
			@ApiParam(value = "参加的状态: 4 财务审核通过,5 财务审核不通过", required = true)  @RequestParam String requestState,
			@ApiParam(value = "备注原因", required = false)  @RequestParam(required = false) String reason) throws Exception{
		if(StringUtil.isNotNull(reason)) {
			reason = StringUtil.cleanXSS(reason);
		}
		if(StringUtil.isEmpty(id)) {
			log.info(CommonConstants.STATEMENT_NOT_EXIST_MSG);
//			throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_IS_NULL_CODE);
		}
		if(StringUtil.isEmpty(requestState)) {
			log.info(CommonConstants.CHECK_STATUS_MSG);
			return ResultVo.getByEnumCode(CommonConstants.CHECK_STATUS_CODE);
		}
		TpmUserStatementModel tpmUserStatementModel = tpmUserStatementService.selectById(id);
		if(tpmUserStatementModel.getRequest_state() == 4 || tpmUserStatementModel.getRequest_state() == 5) {
			return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_DONE_CODE);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("uuid", id);
		map.put("originalAmount", tpmUserStatementModel.getReal_salary());
		int request_state = 4;
		if(requestState.equals("通过") || requestState.equals("4")) {
			request_state = 4;
		}else if(requestState.equals("驳回") || requestState.equals("5")) {
			request_state = 5;
			if(StringUtil.isEmpty(reason)) {
				return ResultVo.getByEnumCode(CommonConstants.AUDIT_NO_PASS_CODE);
			}
			TpmActivityModel tam = tpmActivityService.queryTpmActivityModel(tpmUserStatementModel.getActivity_uuid());
			int valid_work_hours = tpmUserStatementModel.getValid_work_hours();
			double real_salary = tam.getUnitPrice();
			if(valid_work_hours < tam.getWorkHours()) {
				real_salary = (real_salary / tam.getWorkHours() * valid_work_hours) + (tpmUserStatementModel.getValid_work_minute() / 60.0 * (tam.getUnitPrice() / tam.getWorkHours()));
			}
			real_salary = Math.ceil(real_salary);
			tpmUserStatementModel.setReal_salary(real_salary);
		}
		map.put("request_state", request_state);
		map.put("reason", reason);
		map.put("incentive_amount", tpmUserStatementModel.getIncentive_amount());
		map.put("incentive_reason", tpmUserStatementModel.getIncentive_reason());
		map.put("reduce_money", tpmUserStatementModel.getReduce_money());
		map.put("reduce_money_reason", tpmUserStatementModel.getReduce_money_reason());
		map.put("real_salary", tpmUserStatementModel.getReal_salary());
		map.put("adjust_money_note", tpmUserStatementModel.getAdjust_money_note());
		return tpmFinancialService.financialAudit(map);
	}
}
