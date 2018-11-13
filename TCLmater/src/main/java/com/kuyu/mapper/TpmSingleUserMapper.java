package com.kuyu.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.TpmSingleUserModel;

public interface TpmSingleUserMapper extends BaseMapper<TpmSingleUserModel>{
	
	public TpmSingleUserModel queryTpmSingleUserModel(TpmSingleUserModel tpmSingleUserModel);
	
	public Integer insertSingleUser(TpmSingleUserModel tpmSingleUserModel);
	
}
