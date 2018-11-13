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

package com.kuyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmBranchAdminModel;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmDeptService;
import com.kuyu.service.UserRoleInfoService;
import com.kuyu.vo.ResultVo;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@AOP_Controller_LOG
@Api(tags = "设置分公司财务负责人和管理员")
@RequestMapping("/user")
public class UserInfoController extends BaseController {

	@Autowired
	private UserRoleInfoService userRoleInfoService;
	
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;
	
	@Autowired
	private TpmDeptService tpmDeptService;

	@RequestMapping(value = "/setFinancial", method = RequestMethod.GET)
//	@ResponseBody
	@ApiOperation("设置分公司财务负责人")
	public ResultVo setFinancial(@ApiParam("员工工号") @RequestParam("person_code") String person_code,
			@ApiParam("部门编号") @RequestParam("org_code") String org_code, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginUserInfo loginUserInfo = this.getLoginUserInfo();
		return userRoleInfoService.setFinancial(person_code,org_code, loginUserInfo);
	}
	
	@RequestMapping(value = "/setBranchAdmin", method = RequestMethod.GET)
	@ApiOperation("设置分公司管理员")
	public ResultVo setBranchAdmin(@ApiParam("员工工号") @RequestParam("person_code") String person_code,
			@ApiParam("部门编号") @RequestParam("org_code") String org_code, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginUserInfo loginUserInfo = this.getLoginUserInfo();
		return userRoleInfoService.setBranchAdmin(person_code,org_code, loginUserInfo);
	}
	

	@ApiOperation(value = "查询一级部门",notes = "根据登录人角色查询一级部门",response = TpmDeptModel.class)
	@RequestMapping(value = "/queryDeptByRole", method = {RequestMethod.GET})
	public ResultVo queryDeptByRole(){
		LoginUserInfo loginUserInfo = this.getLoginUserInfo();
		String role = loginUserInfo.getUserRole();
		String personCode = loginUserInfo.getEmployeeModel().getPerson_code();
		List<String> deptList = new ArrayList<>();
		List<TpmDeptModel> tdmList = new ArrayList<>();
		if("1".equals(role)){
			String dept = "90003975";
			deptList.add(dept);
		}else if("0".equals(role) || "6".equals(role)){
			List<TpmBranchAdminModel> tbamList = tpmBranchAdminService.selectList(new EntityWrapper<TpmBranchAdminModel>().eq("person_code",personCode).eq("flag", "1"));
			if(tbamList != null && tbamList.size() > 0){
				for (TpmBranchAdminModel tbam : tbamList) {
					String dept = tbam.getOrg_code();
					deptList.add(dept);
				}
			}
		}
		for (String dept : deptList) {
			TpmDeptModel tdm = tpmDeptService.selectOne(new EntityWrapper<TpmDeptModel>().eq("org_code", dept));
			if(tdm != null){
				tdmList.add(tdm);
			}
		}
		return ResultVo.getData(ResultVo.SUCCESS, tdmList);
	}
}
