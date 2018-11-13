package com.kuyu.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.TpmWorkAttendenceDetailModel;

public interface TpmWorkAttendenceDetailMapper extends BaseMapper<TpmWorkAttendenceDetailModel>{

	
	public Integer signIn(TpmWorkAttendenceDetailModel tpmWorkAttendenceDetailModel);

	public List<TpmWorkAttendenceDetailModel> queryList(Map<String, Object> map);
	
	public List<TpmWorkAttendenceDetailModel> queryTpmWorkAttendenceDetailModel(TpmWorkAttendenceDetailModel tadm);
}
