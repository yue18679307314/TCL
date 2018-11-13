package com.kuyu.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.UserRoleInfoModel;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.TpmDeptEmplyeeVo;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description
 */
@Component
public interface UserRoleInfoService extends IService<UserRoleInfoModel> {

	public void insertUserRoleInfo(UserRoleInfoModel userRoleInfoModel);
	
	List<UserRoleInfoModel> selectByPersonCode(String person_code);
	
	public boolean updateByPersonCode(UserRoleInfoModel userRoleInfoModel);
	
	public boolean deleteByPersonCode(String person_code) ;

	public boolean insertUserRole(List<UserRoleInfoModel> userRoleInfoModel);
	
	public boolean deleteByOrgCode(String orgCode);
	
	public String selectRoleByPersonCode(String personCode);
	
	public String getPersonName(String org_code);
	
	public String getPersonCodeByOrgCode(String orgCode);
	
	public ResultVo setFinancial(String personCode,String orgCode,LoginUserInfo userInfo)throws Exception;
	
	public ResultVo setBranchAdmin(String personCode,String orgCode,LoginUserInfo userInfo)throws Exception;
	
	public TpmDeptEmplyeeVo getUserInfo(String orgCode);
}
