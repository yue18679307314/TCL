package com.kuyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.model.TpmRepaymentModel;
import com.kuyu.service.TpmRepaymentService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.RepaymentManagementReturnVo;
import com.kuyu.vo.RepaymentManagementVo;
import com.kuyu.vo.RepaymentVo;
import com.kuyu.vo.ResultVo;

/**
 * @Author tang_zhen
 * @Date 2017/9/29
 * @Description
 */
@AOP_Controller_LOG
@Api(tags = "报销单接收接口")
@RequestMapping("/tpmRepayment")
public class TpmRepaymentController extends BaseController {

	@Autowired
	private TpmRepaymentService tpmRepaymentService;

	/**
	 * 将报销单的相关信息存入临促系统数据库
	 * 
	 * @param tpmRepaymentModel
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "存入报销单数据")
	// @RequestMapping(value = "/insert",method= RequestMethod.POST)
	@PostMapping("/insert")
	public FinancialResultVo insertData(
			@ApiParam("实体类参数，报销单的主信息，支付信息，支付细节") @RequestBody TpmRepaymentModel tpmRepaymentModel) throws Exception {
		// userBaseInfoService.insertData(userBaseInfoModel);
		return tpmRepaymentService.insertRepayment(tpmRepaymentModel);
	}

	/**
	 * 报销单的查询
	 * 
	 * @param repaymentVo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("查询报销单的相关数据")
	// @RequestMapping(value = "/insert",method= RequestMethod.POST)
	@PostMapping("/queryRepaymentInfo")
	public ResultVo queryData(@ApiParam("实体类参数，报销单的主信息，支付信息，支付细节") @RequestBody RepaymentVo repaymentVo)
			throws Exception {
		// userBaseInfoService.insertData(userBaseInfoModel);

		Page<TpmRepaymentModel> tpmRepaymentModelList = tpmRepaymentService.queryRepayment(repaymentVo,
				this.getLoginUserInfo());
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		rs.setData(tpmRepaymentModelList);
		return rs;
	}

	/**
	 * 报销管理查询
	 * 
	 * @param rmv
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "PC报销管理查询", response = RepaymentManagementReturnVo.class)
	@RequestMapping(value = "/queryRepaymentManagementList", method = RequestMethod.POST)
	public ResultVo queryRepaymentManagementList(
			@ApiParam(value = "报销管理查询类", required = true) @RequestBody RepaymentManagementVo rmv) throws Exception {
		rmv = (RepaymentManagementVo) CheckParamUtils.trimWithObjectField(rmv);
		return ResultVo.getData(ResultVo.SUCCESS,
				tpmRepaymentService.queryRepaymentManagementList(rmv, this.getLoginUserInfo()));
	}

	/**
	 * 报销管理详情查询
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "PC报销管理详情查询", response = RepaymentManagementReturnVo.class)
	@RequestMapping(value = "/queryRepaymentManagementDetails", method = RequestMethod.GET)
	public ResultVo queryRepaymentManagementDetails(
			@ApiParam(value = "结算单uuid", required = true) @RequestParam String uuid) throws Exception {
		if (StringUtil.isEmpty(uuid)) {
			return ResultVo.get(ResultVo.SUCCESS);
		}
		return ResultVo.getData(ResultVo.SUCCESS, tpmRepaymentService.queryRepaymentManagementDetails(uuid));
	}

	/**
	 * 导出考勤表
	 * 
	 * @param rmv
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "PC报销管理生成考勤表")
	@RequestMapping(value = "/importAttendence", method = RequestMethod.POST)
	public ResultVo importAttendence(
			@ApiParam(value = "报销管理查询类", required = true) @RequestBody RepaymentManagementVo rmv) throws Exception {

		return tpmRepaymentService.importAttendence(rmv, this.getLoginUserInfo());
	}

	/**
	 * "PC报销管理考勤表查询
	 * 
	 * @param attendenceUuid
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "PC报销管理考勤表查询")
	@RequestMapping(value = "/queryAttendences", method = RequestMethod.POST)
	public ResultVo queryAttendences(
			@ApiParam(value = "报销管理查询类", required = true) @RequestBody RepaymentManagementVo rmv) throws Exception {
		rmv = (RepaymentManagementVo) CheckParamUtils.trimWithObjectField(rmv);

		return tpmRepaymentService.queryAttendences(rmv, getLoginUserInfo());
	}

	/**
	 * 下载考勤表
	 * 
	 * @param rmv
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "PC报销管理下载路径")
	@RequestMapping(value = "/downloadUrl", method = RequestMethod.GET)
	public ResultVo downloadUrl(@ApiParam(value = "考勤表单号", required = true) @RequestParam String attendenceUuid) throws Exception {

		Integer personType = CheckParamUtils.getPersonType(getLoginUserInfo());
		if(personType == -1) {
			return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
		}
		String filePath = tpmRepaymentService.getDownloadUrl(attendenceUuid, getLoginUserInfo());
		return ResultVo.getData(ResultVo.SUCCESS, filePath);
	}

}
