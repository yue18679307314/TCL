/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.common.CommonConstants;
import com.kuyu.mapper.UserRoleInfoMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmBranchAdminModel;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.UserRoleInfoModel;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmDeptService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.service.UserRoleInfoService;
import com.kuyu.util.DateUtils;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.TpmDeptEmplyeeVo;

/**
 * @author zkw
 * @since 2017-09-19 11:09
 */
@Component
@Transactional
public class UserRoleInfoServiceImpl extends ServiceImpl<UserRoleInfoMapper,UserRoleInfoModel> implements UserRoleInfoService{

	@Autowired
	private UserRoleInfoService userRoleInfoService;

	@Autowired
	private TpmDeptService tpmDeptService;

	@Autowired
	private TpmEmployeeService tpmEmployeeService;
	
	@Autowired
	private TpmOptLogsService tpmOptLogsService;
	
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;

	
	@Override
	public List<UserRoleInfoModel> selectByPersonCode(String person_code) {
		return baseMapper.selectByPersonCode(person_code);
	}

	@Override
	public boolean updateByPersonCode(UserRoleInfoModel userRoleInfoModel) {
		return baseMapper.updateByPersonCode(userRoleInfoModel);
	}

	@Override
	public void insertUserRoleInfo(UserRoleInfoModel userRoleInfoModel) {
		userRoleInfoModel.insert();
	}
	
	@Override
	public boolean deleteByPersonCode(String person_code) {
		return baseMapper.deleteByPersonCode(person_code);
	}
	
	@Override
	public boolean insertUserRole(List<UserRoleInfoModel> userRoleInfoModel) {
		return baseMapper.insertUserRole(userRoleInfoModel);
	}

	@Override
	public boolean deleteByOrgCode(String orgCode) {
		return baseMapper.deleteByOrgCode(orgCode);
	}

	@Override
	public String selectRoleByPersonCode(String personCode) {
		return baseMapper.selectRoleByPersonCode(personCode);
	}

	@Override
	public String getPersonName(String org_code) {
		return baseMapper.getPersonName(org_code);
	}

	@Override
	public String getPersonCodeByOrgCode(String orgCode) {
		return baseMapper.getPersonCodeByOrgCode(orgCode);
	}

	@Override
	public ResultVo setFinancial(String person_code,String org_code, LoginUserInfo loginUserInfo) throws Exception {
		if ("1".equals(loginUserInfo.getUserRole()) || "0".equals(loginUserInfo.getUserRole()) ||"6".equals(loginUserInfo.getUserRole())) {
			// List<UserRoleInfoModel> userRoleInfoModelList =
			// userRoleInfoService.selectByPersonCode(person_code);
			// // String[] org_codeArr = org_codes.split(",");
			// if (userRoleInfoModelList != null && userRoleInfoModelList.size() > 0) {
			// userRoleInfoService.deleteByPersonCode(person_code);
			// }
			List<String> orgCodeList = tpmDeptService.queryAllChilsDept(org_code);
			List<String> orgList = tpmEmployeeService.queryOrgCodeList(orgCodeList);
			List<UserRoleInfoModel> userRoleList = new ArrayList<>();
			if (orgList != null && orgList.size() > 0) {
				for (String orgCode : orgList) {
					TpmDeptEmplyeeVo tdev = baseMapper.getUserInfo(orgCode);
					userRoleInfoService.deleteByOrgCode(orgCode);
					UserRoleInfoModel userRoleInfoModel = new UserRoleInfoModel();
					userRoleInfoModel.setPerson_code(person_code);
					userRoleInfoModel.setCreate_time(DateUtils.currentTime());
					userRoleInfoModel.setType("2");
					userRoleInfoModel.setOrg_code(orgCode);
					userRoleList.add(userRoleInfoModel);
					TpmDeptModel t = new TpmDeptModel();
					t.setOrg_code(orgCode);
					TpmDeptModel tdm = tpmDeptService.getTpmDept(t);
					String orgName = tdm.getOrg_name();
					String persoName = tpmEmployeeService.getTpmEmployeebyPersonCode(person_code).getPerson_name();
					TpmOptLogsModel tolm = new TpmOptLogsModel();
					tolm.setType(9);
					String opt_user = loginUserInfo.getEmployeeModel().getPerson_name()+"("+loginUserInfo.getEmployeeModel().getPerson_code()+")";
					tolm.setOptUser(opt_user);
					tolm.setOptUserDept(loginUserInfo.getEmployeeModel().getOrg_code());
					String content = orgName+"("+orgCode+")的财务负责人";
					if(tdev != null) {
						content += "由"+tdev.getPerson_name()+"("+tdev.getPerson_code()+")";
					}
					content += "调整为"+ persoName+"("+person_code+")";
					tolm.setContent(content.toString());
					tpmOptLogsService.insertOptLogs(tolm);
				}
				userRoleInfoService.insertUserRole(userRoleList);
			}else {
				return ResultVo.getByEnumCode(CommonConstants.PERSON_NULL_CODE);
			}
		} else {
			return ResultVo.get(ResultVo.NOT_AUTHORISED);
		}

		return ResultVo.get(ResultVo.SUCCESS);
	}

	@Override
	public TpmDeptEmplyeeVo getUserInfo(String orgCode) {
		return baseMapper.getUserInfo(orgCode);
	}

	@Override
	public ResultVo setBranchAdmin(String person_code,String org_code, LoginUserInfo loginUserInfo) throws Exception {
		if ("1".equals(loginUserInfo.getUserRole())) {
		List<String> orgCodeList = tpmDeptService.queryAllChilsDept(org_code);
//		List<String> orgList = tpmEmployeeService.queryOrgCodeList(orgCodeList);
		List<TpmBranchAdminModel> userRoleList = new ArrayList<>();
			if (orgCodeList != null && orgCodeList.size() > 0) {
				for (String orgCode : orgCodeList) {
					TpmDeptEmplyeeVo tdev = new TpmDeptEmplyeeVo();
					TpmBranchAdminModel tbam = tpmBranchAdminService.selectOne(new EntityWrapper<TpmBranchAdminModel>().eq("org_code",orgCode));
					TpmDeptModel tdm = tpmDeptService.selectOne(new EntityWrapper<TpmDeptModel>().eq("org_code",orgCode));
					tdev.setOrg_code(org_code);
					tdev.setOrg_name(tdm.getOrg_name());
					if(tbam != null && tbam.getPerson_code() != null){
						TpmEmployeeModel tem = tpmEmployeeService.selectOne(new EntityWrapper<TpmEmployeeModel>().eq("person_code",tbam.getPerson_code()));
						tdev.setPerson_code(tem.getPerson_code());
						tdev.setPerson_name(tem.getPerson_name());
					}
					tpmBranchAdminService.delete(new EntityWrapper<TpmBranchAdminModel>().eq("org_code",orgCode));
					TpmBranchAdminModel tpmBranchAdminModel = new TpmBranchAdminModel();
					tpmBranchAdminModel.setPerson_code(person_code);
					tpmBranchAdminModel.setCreate_time(DateUtils.currentTime());
					tpmBranchAdminModel.setType("0");
					tpmBranchAdminModel.setOrg_code(orgCode);
					if(orgCode.equals(org_code)){
						tpmBranchAdminModel.setFlag("1");
					}
					userRoleList.add(tpmBranchAdminModel);
					String orgName = tdm.getOrg_name();
					String persoName = tpmEmployeeService.getTpmEmployeebyPersonCode(person_code).getPerson_name();
					TpmOptLogsModel tolm = new TpmOptLogsModel();
					tolm.setType(13);
					String opt_user = loginUserInfo.getEmployeeModel().getPerson_name()+"("+loginUserInfo.getEmployeeModel().getPerson_code()+")";
					tolm.setOptUser(opt_user);
					tolm.setOptUserDept(loginUserInfo.getEmployeeModel().getOrg_code());
					String content = orgName+"("+orgCode+")的管理员";
					if(tdev.getPerson_code() != null) {
						content += "由"+tdev.getPerson_name()+"("+tdev.getPerson_code()+")";
					}
					content += "调整为"+ persoName+"("+person_code+")";
					tolm.setContent(content.toString());
					tpmOptLogsService.insertOptLogs(tolm);
				}
				tpmBranchAdminService.insertBatch(userRoleList);
			}else {
				return ResultVo.getByEnumCode(CommonConstants.PERSON_NULL_CODE);
			}
		} else {
			return ResultVo.get(ResultVo.NOT_AUTHORISED);
		}

		return ResultVo.get(ResultVo.SUCCESS);
	}
}
