package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.TpmAttendenceModel;

public interface TpmAttendenceService extends IService<TpmAttendenceModel>{

	public String selectUrlByAttendenceUuid(String uuid);
	
	public TpmAttendenceModel selectByTusUuid(String uuid);
}
