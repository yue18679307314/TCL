package com.kuyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.UserRoleInfoModel;
import com.kuyu.vo.TpmDeptEmplyeeVo;


public interface UserRoleInfoMapper extends BaseMapper<UserRoleInfoModel>{

	List<UserRoleInfoModel> selectByPersonCode(@Param("person_code") String person_code);
	
	public boolean updateByPersonCode(UserRoleInfoModel userRoleInfoModel);

	List<String> findFinanceOrgCodeList(String person_code);
	
	public boolean deleteByPersonCode(@Param("person_code") String person_code);
	
	public boolean insertUserRole(List<UserRoleInfoModel> userRoleInfoModel);
	
	public boolean deleteByOrgCode(String orgCode);
	
	public String selectRoleByPersonCode(String personCode);
	
	public String getPersonName(String org_code);
	
	public String getPersonCodeByOrgCode(String orgCode);
	
	public TpmDeptEmplyeeVo getUserInfo(String orgCode);
}