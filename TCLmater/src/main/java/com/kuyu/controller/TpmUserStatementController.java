package com.kuyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.kuyu.vo.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.service.TpmActivityService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.service.TpmUserStatementService;
import com.kuyu.service.UserRoleInfoService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.query.SettlementManagementCheckQuery;
import com.kuyu.vo.query.SettlementManagementQuery;

/**
 * @Author jt_L
 * @Date 2017-09-22
 * @Description 临促结算单表Controller
 */
@Api(tags = "临促结算服务")
@AOP_Controller_LOG
@RequestMapping("/tpmUserStatement")
public class TpmUserStatementController extends BaseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private TpmUserStatementService tpmUserStatementServiceImpl;

	@Resource
	private TpmActivityService tpmActivityService;
	
	@Resource
	private TpmUserBaseInfoService tpmUserBaseInfoService;
	
	@Resource
	private UserRoleInfoService userRoleInfoService;
	
	/**
	 * 根据openid查询结算活动列表收益情况
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询临促所有活动列表收益情况",response = StatementActivityListVo.class)
	@GetMapping("/user/statementActivityList/")
	public ResultVo findUserStatementActivity() throws Exception {
		return tpmUserStatementServiceImpl.findUserStatementActivityListByOpenid(getUserInfo());
	}

    /**
     * 根据openid查询结算活动列表收益情况
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询临促结算活动明细列表收益情况", response = StatementActivityDetailVo.class)
    @GetMapping("/user/income/detaillist/{id}")
    public ResultVo findStatementActivityDetailList(@ApiParam(value = "活动uuid", required = true) @PathVariable("id") String activityUuid) throws Exception {
        return tpmUserStatementServiceImpl.findStatementActivityDetailList(getUserInfo(),activityUuid);
    }

	/**
	 * 结算审核查询
	 * 
	 * @param activityUuid
	 * @param activityTime
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "结算审核查询",notes = "现场负责人根据活动id和时间(默认为当前天)查询每结算单",response = TpmUserStatementModel.class)
	@RequestMapping(value = "/queryStatement", method = {RequestMethod.GET})
	public ResultVo queryStatement(@ApiParam(value = "活动id", required = true) @RequestParam String activityUuid,
			@ApiParam(value = "活动时间(YYYY-MM-DD)，默认为当前天", required = false) @RequestParam(required = false) String activityTime,
			@ApiParam(value = "查询审核的状态：0全部，2待审核，默认查全部", required = false) @RequestParam(required = false,defaultValue = "0") String state,
			@ApiParam(value = "当前页，默认为1", required = false) @RequestParam(required = false, defaultValue = "1") Integer current,
			@ApiParam(value = "每页大小，默认为10", required = false) @RequestParam(required = false, defaultValue = "10") Integer size
			) throws Exception {
		if (StringUtil.isEmpty(activityUuid) ) {
			log.info("activityUuid错误，为{}",activityUuid);
//			throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getData(ResultVo.SUCCESS,null);
		}
		if(StringUtil.isEmpty(activityTime)){
			activityTime = DateUtils.today();
		}
		TpmActivityModel tpmActivityModel = tpmActivityService.queryTpmActivityModel(activityUuid);
		if (tpmActivityModel == null) {
			log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
//			throw new ParamException(ResultVoUtils.paramException("活动id不存在", "activityUuid"));
			return ResultVo.getData(ResultVo.SUCCESS,null);
		}
		Page<TpmUserStatementModel> page = new Page<>(current,size);
		double unit_price = tpmActivityModel.getUnitPrice();
		int planWorkHours = tpmActivityModel.getWorkHours();
		Map<String, Object> map = new HashMap<>();
		map.put("activity_uuid", activityUuid);
		map.put("activity_time", activityTime);
		map.put("statement", "1");
		map.put("state", state);
		List<TpmUserStatementModel> list = tpmUserStatementServiceImpl.attendanceManagementBypage(map, page);
		List<TpmUserStatementModel> resultList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (TpmUserStatementModel tpmUserStatementModel : list) {
				tpmUserStatementModel.setReason(tpmUserStatementModel.getAdjust_money_note());
				tpmUserStatementModel.setUnit_price(unit_price);
				tpmUserStatementModel.setPlanWorkHours(planWorkHours);
				String openid = tpmUserStatementModel.getOpenid();
				String name = tpmUserBaseInfoService.getOpenName(openid);
				tpmUserStatementModel.setName(name);
				String dept = tpmActivityModel.getDept();
				TpmDeptEmplyeeVo tdev = userRoleInfoService.getUserInfo(dept);
				if(tdev != null) {
					tpmUserStatementModel.setFinancialPersonCode(tdev.getPerson_code());
					tpmUserStatementModel.setFinancialPersonName(tdev.getPerson_name());
				}
				resultList.add(tpmUserStatementModel);
			}
		}
		page.setRecords(resultList);
		return ResultVo.getData(ResultVo.SUCCESS, page);
	}

	/**
	 * 调整工资，奖励或者扣除
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "调整工资", notes = "扣除或者奖励")
	@RequestMapping(value = "/adjustMoney", method = { RequestMethod.POST })
	public ResultVo adjustMoney(@ApiParam(value="结算单查询类",required = true)@RequestBody TpmUserStatementQuery tpmUserStatementQuery) throws Exception {
		if(StringUtil.isNotNull(tpmUserStatementQuery.getAdjust_money_reason())) {
			String reason = StringUtil.cleanXSS(tpmUserStatementQuery.getAdjust_money_reason());
			tpmUserStatementQuery.setAdjust_money_reason(reason);
		}
		if(StringUtil.isNotNull(tpmUserStatementQuery.getReason())) {
			String reason = StringUtil.cleanXSS(tpmUserStatementQuery.getReason());
			tpmUserStatementQuery.setReason(reason);
		}
		if (StringUtil.isEmpty(tpmUserStatementQuery.getActivity_uuid())) {
			log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
//			throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
		}
		if(StringUtil.isEmpty(tpmUserStatementQuery.getOpenid())) {
			log.info(CommonConstants.OPENID_NOT_FOUND_MSG);
			return ResultVo.getByEnumCode(CommonConstants.OPENID_NOT_FOUND_CODE);
		}
		if(StringUtil.isEmpty(tpmUserStatementQuery.getActivity_time())) {
			log.info(CommonConstants.ACTIVITY_TIME_IS_NULL_MSG);
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_TIME_IS_NULL_CODE);
		}
		if(!StringUtil.isNotNull(tpmUserStatementQuery.getRequest_state())) {
			log.info(CommonConstants.CHECK_STATUS_MSG);
			return ResultVo.getByEnumCode(CommonConstants.CHECK_STATUS_CODE);
		}
		if(!StringUtil.isNotNull(tpmUserStatementQuery.getAdjust_money())) {
			log.info(CommonConstants.ADJUST_MONEY_NULL_MSG);
			return ResultVo.getByEnumCode(CommonConstants.ADJUST_MONEY_NULL_CODE);
		}
		TpmActivityModel tam = tpmActivityService.queryTpmActivityModel(tpmUserStatementQuery.getActivity_uuid());
		if (tam == null) {
			log.info(CommonConstants.ACTIVITY_NOT_FOUND_MSG);
//			throw new ParamException(ResultVoUtils.paramException("活动id不存在", "activity_uuid"));
			return ResultVo.getByEnumCode(CommonConstants.ACTIVITY_NOT_FOUND_CODE);
		}
		if(tpmUserBaseInfoService.getUserBaseInfo(tpmUserStatementQuery.getOpenid()) == null) {
			log.info(CommonConstants.OPENID_NOT_FOUND_MSG);
//			throw new ParamException(ResultVoUtils.paramException("临促id不存在", "openid"));
			return ResultVo.getByEnumCode(CommonConstants.OPENID_NOT_FOUND_CODE);
		}
		if(tpmUserStatementQuery.getRequest_state() == 0) {
			if(!StringUtil.isNotNull(tpmUserStatementQuery.getReason())) {
				log.info(CommonConstants.AUDIT_NO_PASS_MSG);
				return ResultVo.getByEnumCode(CommonConstants.AUDIT_NO_PASS_CODE);
			}
		}
		if(tpmUserStatementQuery.getRequest_state() == 2) {
			String orgCode = tam.getDept();
			if(!StringUtil.isNotNull(userRoleInfoService.getPersonCodeByOrgCode(orgCode))) {
				return ResultVo.getByEnumCode(CommonConstants.FINANCIAL_NULL_CODE);
			}
			if(tpmUserStatementQuery.getAdjust_money() != 0 && StringUtil.isEmpty(tpmUserStatementQuery.getAdjust_money_reason()) ){
				return ResultVo.getByEnumCode(CommonConstants.AUDIT__PASS_CODE);
			}
		}
		return tpmUserStatementServiceImpl.adjustMoney(tpmUserStatementQuery,getLoginUserInfo());
	}
	
	/**
	 * PC结算管理查询
	 * @param smq
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "PC结算管理查询", notes = "结算管理查询",response = SettlementManagementVo.class)
	@RequestMapping(value = "/querySettlementManagement", method = { RequestMethod.POST })
	public ResultVo querySettlementManagement(@ApiParam(value = "结算管理查询类")@RequestBody SettlementManagementQuery smq) throws Exception{
		smq = (SettlementManagementQuery) CheckParamUtils.trimWithObjectField(smq);
		Page<SettlementManagementVo> page = tpmUserStatementServiceImpl.querySettlementManagement(getUserInfo(),smq);
		ResultVo resultVo = new ResultVo();
		resultVo.setCode(ResultVo.SUCCESS);
		resultVo.setData(page);
		return resultVo;
	}

	/**
	 * PC结算管理审核
	 * @param smcq
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "PC结算管理审核",notes = "结算管理审核")
	@RequestMapping(value = "checkSettlementManagement", method = { RequestMethod.POST })
	public ResultVo checkSettlementManagement(@ApiParam(value = "PC结算审核查询类",required = true)@RequestBody(required = true) SettlementManagementCheckQuery smcq) throws Exception {
		if(StringUtil.isNotNull(smcq.getAdjust_money_reason())) {
			String reason = smcq.getAdjust_money_reason();
			smcq.setAdjust_money_reason(reason);
		}
		if(StringUtil.isNotNull(smcq.getAdjust_money_note())) {
			String note = smcq.getAdjust_money_note();
			smcq.setAdjust_money_reason(note);
		}
		if(StringUtil.isNotNull(smcq.getReason())) {
			String reason = smcq.getReason();
			smcq.setReason(reason);
		}
		if(smcq.getUuidList().size() == 0 || smcq == null) {
			log.info(CommonConstants.SETTLEMENT_IS_NULL_MSG);
//			throw new ParamException(ResultVoUtils.paramException("结算单uuid列表为空", "uuidList"));
			return ResultVo.getByEnumCode(CommonConstants.SETTLEMENT_IS_NULL_CODE);
		}
		if(StringUtil.isEmpty(smcq.getCheckStatus())) {
			log.info(CommonConstants.CHECK_STATUS_MSG);
//			throw new ParamException(ResultVoUtils.paramException("审核状态为空", "checkStatus"));
			return ResultVo.getByEnumCode(CommonConstants.CHECK_STATUS_CODE);
		}
		if(smcq.getIsFinacial() == null) {
			log.info(CommonConstants.CHECK_STATUS_MSG);
//			throw new ParamException(ResultVoUtils.paramException("审核类型为空", "isFinacial"));
			return ResultVo.getByEnumCode(CommonConstants.CHECK_STATUS_CODE);
		}
		return tpmUserStatementServiceImpl.checkSettlementManagement(getUserInfo(),smcq);
	}
	
	/**
	 * PC结算管理详情查询
	 * @param uuid
	 * @return
	 * @throws ParamException
	 */
	@ApiOperation(value = "PC结算管理详情查询",notes = "结算管理详情查询",response = SettlementManagementVo.class)
	@RequestMapping(value = "querySettlementDetails",method = {RequestMethod.GET})
	public ResultVo querySettlementDetails(@ApiParam(value = "结算单uuid",required = true)@RequestParam(required = true) String uuid) throws ParamException {
		if(StringUtil.isEmpty(uuid)) {
			log.info("结算单uuid错误，为{}",uuid);
//			throw new ParamException(ResultVoUtils.paramException("结算单uuid为空", "uuid"));
			return ResultVo.getData(ResultVo.SUCCESS,null);
		}
		SettlementManagementVo settlementManagementVo = tpmUserStatementServiceImpl.querySettlementDetails(uuid);
		return ResultVo.getData(ResultVo.SUCCESS, settlementManagementVo);
	}
	
	@ApiOperation(value = "获取当前登录用户角色")
	@RequestMapping(value = "getUserRole", method = {RequestMethod.GET})
	public ResultVo getUserRoleByPersonCode() throws Exception {
		String role = this.getLoginUserInfo().getUserRole();
		return ResultVo.getData(ResultVo.SUCCESS,role);
	}
	
	@ApiOperation(value = "待办事项查询")
	@RequestMapping(value = "getToDoList", method = {RequestMethod.GET})
	public ResultVo getToDoList() throws Exception {
		return tpmUserStatementServiceImpl.getToDoList(getUserInfo());
	}

	
	@ApiOperation(value = "结算管理导出")
	@RequestMapping(value = "outputSettlement", method = {RequestMethod.POST})
	public ResultVo outputSettlement(@ApiParam(value = "结算管理查询类")@RequestBody SettlementManagementQuery smq) throws Exception{
		smq = (SettlementManagementQuery) CheckParamUtils.trimWithObjectField(smq);
		return tpmUserStatementServiceImpl.outputSettlement(getUserInfo(),smq);
	}
	
}
