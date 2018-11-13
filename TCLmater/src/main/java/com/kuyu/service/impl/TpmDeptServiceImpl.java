package com.kuyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.TpmDeptMapper;
import com.kuyu.model.TpmBranchAdminModel;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmDeptService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.service.UserRoleInfoService;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.TpmDeptVo;

@Service
@Transactional
public class TpmDeptServiceImpl extends ServiceImpl<TpmDeptMapper, TpmDeptModel>
		implements TpmDeptService {

	@Autowired
	private UserRoleInfoService userRoleInfoService;
	
	@Autowired
	private TpmEmployeeService tpmEmployeeService;
	
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;
	
	@Override
	public TpmDeptModel getTpmDept(TpmDeptModel tpmDeptModel) {
		TpmDeptModel tpmDept = baseMapper.getTpmDept(tpmDeptModel);
		String person_name = userRoleInfoService.getPersonName(tpmDeptModel.getOrg_code());
		if(StringUtil.isNotNull(person_name)) {
			tpmDept.setPerson_name(person_name);
		}
		return tpmDept;
	}

	/**
	 * 新增
	 * @throws ParamException 
	 */
	@Override
	public void insertTpmDept(TpmDeptModel tpmDeptModel) throws ParamException {
		if (StringUtil.isNotNull(tpmDeptModel.getOrg_code())
				&& StringUtil.isNotNull(tpmDeptModel.getOrg_name())
				&& StringUtil.isNotNull(tpmDeptModel.getOrg_code_parent())
				&& StringUtil.isNotNull(tpmDeptModel.getOrg_name_parent())
				&& StringUtil.isNotNull(tpmDeptModel.getOrg_type())){
			String uuid = StringUtil.getUUID();
			tpmDeptModel.setUuid(uuid);
		    baseMapper.insertTpmDept(tpmDeptModel);
		}
	}

	/**
	 * 更新
	 */
	@Override
	public void updateTpmDept(TpmDeptModel tpmDeptModel) {
		if (StringUtil.isNotNull(tpmDeptModel.getOrg_code())) {
			baseMapper.updateTpmDept(tpmDeptModel);
		}
	}

	/**
	 * 查询部门信息列表
	 */
	@Override
	public Page<TpmDeptModel> getDeptList(TpmDeptVo tpmDeptVo) {
		Page<TpmDeptModel> page = new Page<>(tpmDeptVo.getCurrent(), tpmDeptVo.getSize());
		List<TpmDeptModel> DeptList = baseMapper.getDeptList(tpmDeptVo, page);
		for (TpmDeptModel tpmDeptModel : DeptList) {
			String person_name = userRoleInfoService.getPersonName(tpmDeptModel.getOrg_code());
			if(StringUtil.isNotNull(person_name)) {
				tpmDeptModel.setPerson_name(person_name);
			}
			TpmBranchAdminModel tbam = tpmBranchAdminService.selectOne(new EntityWrapper<TpmBranchAdminModel>().eq("org_code", tpmDeptModel.getOrg_code()));
			if(tbam != null && tbam.getPerson_code() != null) {
				TpmEmployeeModel tem = tpmEmployeeService.selectOne(new EntityWrapper<TpmEmployeeModel>().eq("person_code", tbam.getPerson_code()));
				if(tem != null && tem.getPerson_name() != null){
					tpmDeptModel.setBranchAdmin(tem.getPerson_name());
				}
			}
		}
		page.setRecords(DeptList);
		return page;
	}

	@Override
	public List<String> queryAllChilsDept(String parentCode) {
		return baseMapper.queryAllChilsDept(parentCode);
	}

	@Override
	public List<TpmDeptModel> queryChildsDept(String parentCode) {
		return baseMapper.queryChildsDept(parentCode);
	}
	
}
