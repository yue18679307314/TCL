package com.kuyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import com.kuyu.vo.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.service.TpmUserAccountInfoService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.BankInfoVo;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.VerifyBankAccountVo;


/**
 * @Author tang_zhen
 * @Date 2017/9/25
 * @Description
 */
@AOP_Controller_LOG
@Api(tags = "关注用户的基本信息")
@RequestMapping("/tpmUserBaseInfo")
public class TpmUserBaseInfoController extends BaseController {

	@Autowired
	private TpmUserBaseInfoService tpmUserBaseInfoService;

	@Autowired
	private TpmUserAccountInfoService tpmUserAccountInfoService;

	/**
	 * 基本数据的插入或者更新
	 * 
	 * @param userBaseInfoModel
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("存入数据")
	@PostMapping("/insert")
	public ResultVo insertData(@ApiParam("实体类参数，银行卡相关信息填写时可忽略") @RequestBody TpmUserBaseInfoModel userBaseInfoModel)
			throws Exception {

		userBaseInfoModel.setCreate_time(new DateUtils().getSysDateTimeString());
		userBaseInfoModel.setOpenid(this.getOpenid());
		if (this.getLoginUserInfo().getEmployeeModel() == null) {
			userBaseInfoModel.setBinding_verify("0");
		} else {
			userBaseInfoModel.setBinding_verify("1");
		}

		return tpmUserBaseInfoService.insertData(userBaseInfoModel);
	}

	/**
	 * 银行卡信息的插入或者更新操作
	 * 
	 * @param tpmUserBaseInfoModel
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("存入银行卡相关信息")
	// @RequestMapping(value = "/insert",method= RequestMethod.POST)
	@PostMapping("/insertAccountInfo")
	public ResultVo insertAccountData(
			@ApiParam("实体类参数，只填写以open和account开头的银行卡相关的参数信息") @RequestBody TpmUserBaseInfoModel tpmUserBaseInfoModel)
			throws Exception {

		Integer personType = CheckParamUtils.getPersonType(this.getLoginUserInfo());
		tpmUserBaseInfoModel.setOpenid(this.getOpenid());
		return tpmUserAccountInfoService.updateAccountData(personType, tpmUserBaseInfoModel);
	}

	/**
	 * 查询临促用户本人的基本信息
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询临促用户本人的基本信息", response = TpmUserBaseInfoModel.class)
	@RequestMapping(value = "/queryUserInfo", method = RequestMethod.GET)
	// @PostMapping("/queryUserInfo")
	public ResultVo queryUserInfo() throws Exception {

		TpmUserBaseInfoModel userBaseInfoModel = tpmUserBaseInfoService.getUserBaseInfo(this.getOpenid());
		if (this.getLoginUserInfo() == null)// 未登录
		{
			return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION);
		}
		if (this.getLoginUserInfo().getEmployeeModel() == null) {
			userBaseInfoModel.setBinding_verify("0");
		} else {
			userBaseInfoModel.setBinding_verify("1");
			userBaseInfoModel.setITcode(this.getLoginUserInfo().getEmployeeModel().getItcode());
		}
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		rs.setData(userBaseInfoModel);
		return rs;
	}

	/**
	 * 根据openid查询用户基本信息
	 * 
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据openid查询用户基本信息", response = TpmUserBaseInfoModel.class)
	@RequestMapping(value = "/getBaseInfoByOpenid", method = RequestMethod.GET)
	// @PostMapping("/queryUserInfo")
	public ResultVo queryUserInfoByOpenid(
			@ApiParam(value = "openid", required = true) @RequestParam("openid") String openid) throws Exception {
		if (openid == null || "".equals(openid)) {
			ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
			rs.setData(null);
			return rs;
		}
		TpmUserBaseInfoModel userBaseInfoModel = tpmUserBaseInfoService.getUserBaseInfo(openid);
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		rs.setData(userBaseInfoModel);
		return rs;
	}

	/**
	 * 根据各项信息查询用户基本信息
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据各项信息查询用户基本信息", response = TpmUserBaseInfoModel.class)
	@RequestMapping(value = "/queryUserInfoList", method = RequestMethod.POST)
	public ResultVo queryUserInfoList(
			@ApiParam("实体类参数，可根据姓名，微信昵称，省，市，手机号查询") @RequestBody TpmUserBaseInfoModel tpmUserBaseInfoModel)
			throws Exception {

		Page<TpmUserBaseInfoModel> userBaseInfoModelList = tpmUserBaseInfoService
				.getUserBaseInfoList(this.getLoginUserInfo(), tpmUserBaseInfoModel);
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		rs.setData(userBaseInfoModelList);
		return rs;
	}

	/**
	 * 返回可选的银行
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "返回可选的银行名")
	@RequestMapping(value = "/getBankList", method = RequestMethod.GET)
	public ResultVo getBankList() throws Exception {
		List<String> bankList = tpmUserAccountInfoService.getBankList();
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		rs.setData(bankList);
		return rs;
	}

	/**
	 * 返回省
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "返回省")
	@RequestMapping(value = "/getProvinceList", method = RequestMethod.GET)
	public ResultVo getProvinceList() throws Exception {
		List<ProvinceVo> provinceList = tpmUserBaseInfoService.getProvinceList();
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		rs.setData(provinceList);
		return rs;
	}
	/**
	 * 返回省下面的地级市列表
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "返回省下面的地级市列表")
	@RequestMapping(value = "/getProvinceList/{provinceId}", method = RequestMethod.GET)
	public ResultVo getCityList(@PathVariable("provinceId") String provinceId) throws Exception {
		List<String> cityList = tpmUserBaseInfoService.getCityList(provinceId);
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		rs.setData(cityList);
		return rs;
	}

	/**
	 * 返回银行卡审核状态
	 * 
	 * @return
	 */
	@ApiOperation(value = "返回银行卡审核状态的接口")
	@RequestMapping(value = "/getBankAccountVerify", method = RequestMethod.GET)
	public ResultVo getBankAccountVerify() {

		return tpmUserAccountInfoService.getTheBankAccountVerify(this.getOpenid());
	}

	/**
	 * 财务验证银行账号接口
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "财务验证银行账号接口")
	@RequestMapping(value = "/receiveInfoFromFinance", method = RequestMethod.POST)
	// @PostMapping("/queryUserInfo")
	public FinancialResultVo getInfoFromFinance(
			@ApiParam(value = "VerifyBankAccountVo", required = true) @RequestBody VerifyBankAccountVo verifyBankAccountVo)
			throws Exception {
		return tpmUserAccountInfoService.insertBankAccountVerify(verifyBankAccountVo);
	}

	@ApiOperation(value = "查询银行账户信息")
	@RequestMapping(value = "/outputBankInfo", method = RequestMethod.POST)
	public ResultVo outputBankInfo(@ApiParam(value = "银行账户导出查询类") @RequestBody BankInfoVo bifv) throws Exception {

		bifv = (BankInfoVo) CheckParamUtils.trimWithObjectField(bifv);
		return tpmUserAccountInfoService.outputBankInfo(bifv, getLoginUserInfo());
	}

	@ApiOperation(value = "获取下载的url")
	@RequestMapping(value = "/getBankInfoUrl", method = RequestMethod.GET)
	public ResultVo getBankInfoUrl(@ApiParam(required = true,value = "临促的openid列表") @RequestParam List<String> openidList,
			@ApiParam(required = true,value = "pdf格式或者xls格式") @RequestParam String pdfOrxls) throws Exception {
		String file = null;
		if (StringUtil.isNotNull(openidList)) {
			if (openidList.size() > 0) {
				file = tpmUserAccountInfoService.getBankInfoUrl(openidList,pdfOrxls, this.getLoginUserInfo());
			}
		}
		return ResultVo.getData(ResultVo.SUCCESS, file);
	}

}
