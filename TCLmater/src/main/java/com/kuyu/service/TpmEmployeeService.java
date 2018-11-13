package com.kuyu.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.TpmEmployeeVo;

public interface TpmEmployeeService extends IService<TpmEmployeeModel>{

	/**
	 * 查询员工信息
	 * @param tpmEmployeeModel
	 * @return
	 * @throws Exception 
	 */
	public ResultVo getTpmEmployee(TpmEmployeeModel tpmEmployee) throws Exception;
	
	public TpmEmployeeModel getTpmEmployeeByTem(TpmEmployeeModel tpmEmployee) throws Exception;

	
	public TpmEmployeeModel getTpmEmployeeByItcode(String itcode) throws Exception ;
	
	/**
	 * 查询员工信息列表
	 * @param
	 * @return
	 */
	public ResultVo getEmployeeList(TpmEmployeeVo tpmEmployeeVo) throws Exception;
	/**
	 * 新增员工信息
	 * @param tpmEmployeeModel
	 * @return
	 */
	public ResultVo insertTpmEmployee(TpmEmployeeModel tpmEmployeeModel) throws Exception;

	/**
	 * 更新员工信息
	 * @param tpmEmployeeModel
	 * @return
	 */
	public ResultVo updateTpmEmployee(TpmEmployeeModel tpmEmployeeModel) throws Exception;
	
	
	public List<String> queryOrgCodeList(List<String> list);
	
	public TpmEmployeeModel getTpmEmployeebyPersonCode(String personCode) throws Exception;
}
