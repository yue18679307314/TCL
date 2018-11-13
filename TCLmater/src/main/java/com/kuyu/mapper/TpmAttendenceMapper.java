package com.kuyu.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.TpmAttendenceModel;

public interface TpmAttendenceMapper extends BaseMapper<TpmAttendenceModel>{

	public String selectByAttendenceUuid(String uuid);
	
	public String selectUrlByAttendenceUuid(String uuid);
	
	public TpmAttendenceModel selectByTusUuid(String uuid);
	
	public List<TpmAttendenceModel>  selectByTusUuidList(List<String> list);
}
