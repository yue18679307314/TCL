package com.kuyu.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.vo.TpmDeptVo;

public interface TpmDeptMapper extends BaseMapper<TpmDeptModel>{

	public TpmDeptModel getTpmDept(TpmDeptModel tpmDeptModel);
	
	public List<TpmDeptModel> getDeptList(TpmDeptVo tpmDeptVo,Page<TpmDeptModel> page);
	
	public Boolean insertTpmDept(TpmDeptModel tpmDeptModel);
	
	public Boolean updateTpmDept(TpmDeptModel tpmDeptModel);

	/**
	 * 	查询部门编号及所有子部门
	 * @param parentCode
	 * @return
	 */
	List<String> queryAllChilsDept(String parentCode);
	
	List<TpmDeptModel> queryChildsDept(String parentCode);

}
