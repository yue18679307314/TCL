package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.TpmBranchAdminMapper;
import com.kuyu.model.TpmBranchAdminModel;
import com.kuyu.service.TpmBranchAdminService;

@Service
@Transactional
public class TpmBranchAdminServiceImpl extends ServiceImpl<TpmBranchAdminMapper, TpmBranchAdminModel> implements TpmBranchAdminService {

	@Override
	public List<String> branchDeptList(String personCode) {
		List<TpmBranchAdminModel> tbamList = baseMapper.selectList(new EntityWrapper<TpmBranchAdminModel>().eq("person_code", personCode));
		List<String> branchList = new ArrayList<>();
		if(tbamList != null && tbamList.size() > 0){
			for (TpmBranchAdminModel tbam : tbamList) {
				branchList.add(tbam.getOrg_code());
			}
		}
		return branchList;
	}

}
