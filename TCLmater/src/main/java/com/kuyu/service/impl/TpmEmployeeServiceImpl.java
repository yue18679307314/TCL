package com.kuyu.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.TpmEmployeeMapper;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.util.RSAUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.TpmEmployeeVo;

import sun.misc.BASE64Encoder;

@Service
@Transactional
public class TpmEmployeeServiceImpl extends ServiceImpl<TpmEmployeeMapper, TpmEmployeeModel>
		implements TpmEmployeeService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 根据id查询员工详细信息
	 * 
	 * @throws Exception
	 */
	@Override
	public ResultVo getTpmEmployee(TpmEmployeeModel tpmEmployeeModel) throws Exception {
		if (StringUtil.isEmpty(tpmEmployeeModel.getPerson_code())) {
			log.info(CommonConstants.PARAM_IS_ERROR_MSG);
			// throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getData(ResultVo.SUCCESS, null);
		}
		TpmEmployeeModel tpmEmployee = baseMapper.getTpmEmployee(tpmEmployeeModel);
		if(tpmEmployee != null && StringUtil.isNotBlank(tpmEmployee.getMobile())) {
			String mobile = tpmEmployee.getMobile();
			if (StringUtil.isNotNull(mobile)) {
				/*
				 * if(mobile.length() >= 4) { mobile =
				 * mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
				 * tpmEmployee.setMobile(mobile); }
				 */
				byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(mobile, RSAUtils.PUBLIC_KEY);
				BASE64Encoder enc = new BASE64Encoder();
				mobile = enc.encode(decodedPwds2);
				tpmEmployee.setMobile(mobile);
			}
		}
		ResultVo resultVo = new ResultVo();
		resultVo.setData(tpmEmployee);
		resultVo.setCode(ResultVo.SUCCESS);
		return resultVo;
	}
	
	@Override
	public TpmEmployeeModel getTpmEmployeeByItcode(String itcode) throws Exception {
		TpmEmployeeModel tpmEmployee = baseMapper.getTpmEmployeeByItcode(itcode);
		if (tpmEmployee != null) {
			String mobile = tpmEmployee.getMobile();
			if (StringUtil.isNotNull(mobile)) {
				/*
				 * if(mobile.length() >= 4) { mobile =
				 * mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
				 * tpmEmployee.setMobile(mobile); }
				 */
				byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(mobile, RSAUtils.PUBLIC_KEY);
				BASE64Encoder enc = new BASE64Encoder();
				mobile = enc.encode(decodedPwds2);
				tpmEmployee.setMobile(mobile);
			}
		}
		return tpmEmployee;
	}

	/**
	 * 新增
	 * 
	 * @throws ParamException
	 */
	@Override
	public ResultVo insertTpmEmployee(TpmEmployeeModel tpmEmployeeModel) throws ParamException {
		if (StringUtil.isNotNull(tpmEmployeeModel.getItcode()) && StringUtil.isNotNull(tpmEmployeeModel.getOrg_code())
				&& StringUtil.isNotNull(tpmEmployeeModel.getOrg_name())
				&& StringUtil.isNotNull(tpmEmployeeModel.getPerson_code())
				&& StringUtil.isNotNull(tpmEmployeeModel.getPerson_name())) {
			String uuid = StringUtil.getUUID();
			tpmEmployeeModel.setUuid(uuid);
			baseMapper.insertTpmEmployee(tpmEmployeeModel);
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}

	/**
	 * 更新
	 */
	@Override
	public ResultVo updateTpmEmployee(TpmEmployeeModel tpmEmployeeModel) {
		if (StringUtil.isNotNull(tpmEmployeeModel.getPerson_code())) {
			baseMapper.updateTpmEmployee(tpmEmployeeModel);
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}

	/**
	 * 查询员工信息列表
	 * 
	 * @throws Exception
	 */
	@Override
	public ResultVo getEmployeeList(TpmEmployeeVo tpmEmployeeVo) throws Exception {
		Page<TpmEmployeeModel> page = new Page<>(tpmEmployeeVo.getCurrent(), tpmEmployeeVo.getSize());
		List<TpmEmployeeModel> employeeList = baseMapper.getEmployeeList(tpmEmployeeVo, page);
		if(employeeList != null && employeeList.size() > 0) {
			for (TpmEmployeeModel tpmEmployeeModel : employeeList) {
				if (tpmEmployeeModel != null) {
					String mobile = tpmEmployeeModel.getMobile();
					if (StringUtil.isNotNull(mobile)) {
						/*
						 * if(mobile.length() >= 4) { mobile =
						 * mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
						 * tpmEmployeeModel.setMobile(mobile); }
						 */
						byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(mobile, RSAUtils.PUBLIC_KEY);
						BASE64Encoder enc = new BASE64Encoder();
						mobile = enc.encode(decodedPwds2);
						tpmEmployeeModel.setMobile(mobile);
					}
				}
			}
		}
		page.setRecords(employeeList);
		return ResultVo.getDataWithSuccess(page);
	}

	@Override
	public List<String> queryOrgCodeList(List<String> list) {
		return baseMapper.queryOrgCodeList(list);
	}

	@Override
	public TpmEmployeeModel getTpmEmployeebyPersonCode(String personCode) throws Exception {

		TpmEmployeeModel tpmEmployeeModel = baseMapper.getTpmEmployeebyPersonCode(personCode);
		if(tpmEmployeeModel != null) {
			String mobile = tpmEmployeeModel.getMobile();
			if (StringUtil.isNotNull(mobile)) {
				/*
				 * if(mobile.length() >= 4) { mobile =
				 * mobile.substring(0,3)+"****"+mobile.substring(mobile.length()-4);
				 * tpmEmployeeModel.setMobile(mobile); }
				 */
				byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(mobile, RSAUtils.PUBLIC_KEY);
				BASE64Encoder enc = new BASE64Encoder();
				mobile = enc.encode(decodedPwds2);
				tpmEmployeeModel.setMobile(mobile);
			}
		}
		return tpmEmployeeModel;
	}

	@Override
	public TpmEmployeeModel getTpmEmployeeByTem(TpmEmployeeModel tpmEmployeeModel) throws Exception {
		if (StringUtil.isEmpty(tpmEmployeeModel.getPerson_code())) {
			log.info(CommonConstants.PARAM_IS_ERROR_MSG);
			// throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return null;
		}
		TpmEmployeeModel tpmEmployee = baseMapper.getTpmEmployee(tpmEmployeeModel);
		return tpmEmployee;
	}

}
