package com.kuyu.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.vo.TpmDeptVo;

public interface TpmDeptService extends IService<TpmDeptModel>{

	
	public TpmDeptModel getTpmDept(TpmDeptModel tpmDeptModel);
	
	public Page<TpmDeptModel> getDeptList(TpmDeptVo tpmDeptVo) throws Exception;

	public void insertTpmDept(TpmDeptModel tpmDeptModel) throws Exception;

	public void updateTpmDept(TpmDeptModel tpmDeptModel) throws Exception;
	
	List<TpmDeptModel> queryChildsDept(String parentCode);
	
	List<String> queryAllChilsDept(String parentCode);
}
