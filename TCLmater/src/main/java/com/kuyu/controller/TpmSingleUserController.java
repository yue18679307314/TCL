package com.kuyu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baomidou.mybatisplus.mapper.Condition;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.service.TpmProjectService;
import com.kuyu.service.TpmSingleUserService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.PersonCodeVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.TpmSingleUserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "创建立项单可使用人接口")
@AOP_Controller_LOG
@RequestMapping("/tpmSingleUser")
public class TpmSingleUserController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private TpmSingleUserService tpmSingleUserService;
	
	@Resource
	private TpmProjectService tpmProjectService;
	
	@Resource
	private TpmEmployeeService tpmEmployeeService;
	
	/**
	 * 创建立项单可使用人
	 * @param tpmSingleUserVo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("创建立项单可使用人")
	@RequestMapping(value = "/createSingleUser" , method = {RequestMethod.POST})
	public FinancialResultVo createSingleUser(@RequestBody TpmSingleUserVo tpmSingleUserVo) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(getUserInfo());
		if (personType != 1 && personType != 0 && personType != 6) {
			return FinancialResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
		}
		String fsscBill = tpmSingleUserVo.getFssc_bill();
		List<PersonCodeVo> personList = tpmSingleUserVo.getPerson_list();
		
		if(StringUtil.isEmpty(fsscBill)) {
			log.info(CommonConstants.FSSC_BILL_MSG);
//			throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return FinancialResultVo.getByEnumCode(CommonConstants.FSSC_BILL_CODE);
		}
		if(personList == null || personList.size() != 1) {
			return FinancialResultVo.getByEnumCode(CommonConstants.PERSONCODE_NOT_FOUND_CODE);
		}
		for (PersonCodeVo person : personList) {
			String personCode = person.getPerson_code();
			if(tpmEmployeeService.selectOne(Condition.create().eq("person_code", personCode)) == null) {
				log.info(CommonConstants.PERSONCODE_NOT_FOUND_MSG);
//				throw new ParamException(ResultVoUtils.paramException("工号不存在", personCode));
				return FinancialResultVo.getByEnumCode(CommonConstants.PERSONCODE_NOT_FOUND_CODE);
			}
		}
		FinancialResultVo financialResultVo =  tpmSingleUserService.post(tpmSingleUserVo);
		if(financialResultVo.getRet_code().equals("9999")) {
			tpmSingleUserService.insertSingleUser(fsscBill,personList,getLoginUserInfo());
		}
		return financialResultVo;
	}
}
