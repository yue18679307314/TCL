package com.kuyu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.service.TpmDeptService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.TpmDeptVo;
import com.kuyu.vo.TpmEmployeeVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "部门员工信息接口")
@AOP_Controller_LOG
@RequestMapping("/deptEmployee")
public class TpmEmployeeController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TpmEmployeeService tpmEmployeeService;

	@Resource
	private TpmDeptService tpmDeptService;

	/**
	 * 查询员工详情
	 * 
	 * @param person_code
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据员工编号查询员工详情", notes = "根据员工编号查询员工信息详情", response = TpmEmployeeModel.class)
	@RequestMapping(value = "/getEmployeeByPersonCode", method = { RequestMethod.GET })
	public ResultVo getEmployeeByPersonCode(
			@ApiParam(value = "员工编号", required = true) @RequestParam(required = true) String person_code)
			throws Exception {
		if (StringUtil.isEmpty(person_code)) {
			log.info("person_code错误,为{}",person_code);
//			throw new ParamException(ResultVoUtils.paramException("参数为空", "person_code"));
			return ResultVo.getData(ResultVo.SUCCESS,null);
		}
		TpmEmployeeModel tpmEmployeeModel = new TpmEmployeeModel();
		tpmEmployeeModel.setPerson_code(person_code);
		return tpmEmployeeService.getTpmEmployee(tpmEmployeeModel);
	}

	/**
	 * 查询部门详情
	 * 
	 * @param org_code
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据部门编号查询部门详情", notes = "根据部门编号查询部门详情", response = TpmDeptModel.class)
	@RequestMapping(value = "/getDeptByOrgCode", method = { RequestMethod.GET })
	public ResultVo getDeptByOrgCode(
			@ApiParam(value = "员工编号", required = true) @RequestParam(required = true) String org_code)
			throws Exception {
		TpmDeptModel tpmDeptModel = new TpmDeptModel();
		tpmDeptModel.setOrg_code(org_code);
		if (StringUtil.isEmpty(tpmDeptModel.getOrg_code())) {
			log.info("org_code错误，为{}",org_code);
//			throw new ParamException(ResultVoUtils.paramException("参数为空", "org_code"));
			return ResultVo.getData(ResultVo.SUCCESS,null);
		}
		TpmDeptModel tdm = tpmDeptService.getTpmDept(tpmDeptModel);
		ResultVo resultVo = new ResultVo();
		resultVo.setData(tdm);
		resultVo.setCode(ResultVo.SUCCESS);
		return resultVo;
	}

	/**
	 * 查询员工信息列表
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询员工信息列表", notes = "查询员工信息列表", response = TpmEmployeeModel.class)
	@RequestMapping(value = "/getEmployeeList", method = { RequestMethod.POST })
	public ResultVo getEmployeeList(
			@ApiParam(value = "员工信息", required = false) @RequestBody TpmEmployeeVo tpmEmployeeVo) throws Exception {
		tpmEmployeeVo = (TpmEmployeeVo) CheckParamUtils.trimWithObjectField(tpmEmployeeVo);
		return tpmEmployeeService.getEmployeeList(tpmEmployeeVo);
	}

	/**
	 * 查询部门信息列表
	 * 
	 * @param tpmDeptVo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询部门信息列表", notes = "查询部门信息列表", response = TpmDeptModel.class)
	@RequestMapping(value = "/getDeptList", method = { RequestMethod.POST })
	public ResultVo getDeptList(@ApiParam(value = "部门信息", required = false) @RequestBody TpmDeptVo tpmDeptVo)
			throws Exception {
		tpmDeptVo = (TpmDeptVo) CheckParamUtils.trimWithObjectField(tpmDeptVo);
		return ResultVo.getData(ResultVo.SUCCESS, tpmDeptService.getDeptList(tpmDeptVo));
	}

	/**
	 * 查询员工信息，如果查到则更新，否则新增
	 * 
	 * @param USER_LIST
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "员工信息操作", notes = "根据员工编号查询员工信息，如果查到则更新，否则新增", response = TpmEmployeeModel.class)
	@ApiParam(name = "tpmEmployeeModel", value = "员工信息实体类参数")
	@RequestMapping(value = "/doTpmEmployee", method = { RequestMethod.POST })
	public FinancialResultVo doTpmEmployee(@RequestBody List<TpmEmployeeModel> USER_LIST) throws Exception {

		for (TpmEmployeeModel tpmEmployeeModel : USER_LIST) {
			TpmEmployeeModel tpmEmployee = tpmEmployeeService.getTpmEmployeeByTem(tpmEmployeeModel);
			if (tpmEmployee != null) {
				tpmEmployeeService.updateTpmEmployee(tpmEmployeeModel);
			} else {
				tpmEmployeeService.insertTpmEmployee(tpmEmployeeModel);
			}
		}
		return FinancialResultVo.get(FinancialResultVo.SUCCESS);
	}

	/**
	 * 查询部门信息，如果查到则更新，否则新增
	 * 
	 * @param DEPART_LIST
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "部门信息操作", notes = "根据部门编号查询部门信息，如果查到则更新，否则新增", response = TpmDeptModel.class)
	@ApiParam(name = "tpmDeptModel", value = "部门信息实体类参数")
	@RequestMapping(value = "/doTpmDept", method = { RequestMethod.POST })
	public FinancialResultVo doTpmDept(@RequestBody(required = true) List<TpmDeptModel> DEPART_LIST) throws Exception {
		for (TpmDeptModel tpmDeptModel : DEPART_LIST) {
			TpmDeptModel tpmDept = tpmDeptService.getTpmDept(tpmDeptModel);
			if (tpmDept != null) {
				tpmDeptService.updateTpmDept(tpmDeptModel);
			} else {
				tpmDeptService.insertTpmDept(tpmDeptModel);
			}
		}
		return FinancialResultVo.get(FinancialResultVo.SUCCESS);
	}
	
	/**
	 * 查询该部门下一级部门
	 * @param parentCode
	 * @return
	 */
	@ApiOperation(value = "查询直接子部门",notes = "根据部门编号查询直接子部门",response = TpmDeptModel.class)
	@RequestMapping(value = "/queryChildsDept", method = {RequestMethod.GET})
	public ResultVo queryChildsDept(@ApiParam(value = "部门编号", required = true) @RequestParam(required = true)String parentCode) {
		List<TpmDeptModel> deptList = tpmDeptService.queryChildsDept(parentCode);
		return ResultVo.getDataWithSuccess(deptList);
	}
	
}
