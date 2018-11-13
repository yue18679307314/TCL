package com.kuyu.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.TpmBranchAdminModel;

public interface TpmBranchAdminService extends IService<TpmBranchAdminModel>{

	public List<String> branchDeptList(String personCode);
}
