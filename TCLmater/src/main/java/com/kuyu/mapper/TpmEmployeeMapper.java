package com.kuyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.vo.TpmEmployeeVo;
import com.kuyu.vo.pcms.RequestUserVo;

public interface TpmEmployeeMapper extends BaseMapper<TpmEmployeeModel>{

	public TpmEmployeeModel getTpmEmployee(TpmEmployeeModel tpmEmployeeModel);
	
	public TpmEmployeeModel getTpmEmployeeByItcode(@Param("itcode") String itcode) ;
	
	public List<TpmEmployeeModel> getEmployeeList(TpmEmployeeVo tpmEmployeeVo,Page<TpmEmployeeModel> page);
	
	public Boolean insertTpmEmployee(TpmEmployeeModel tpmEmployeeModel);
	
	public Boolean updateTpmEmployee(TpmEmployeeModel tpmEmployeeModel);
	
	public List<String>queryOrgCodeList(List<String> list);

	public TpmEmployeeModel getTpmEmployeebyPersonCode(String person_code);

	public List<RequestUserVo> getRequestNameList(@Param("orgCode") String orgCode,@Param("searchKey") String searchKey);

	List<TpmEmployeeModel> getEmployeeListByCompany(@Param("company") String company);
}
