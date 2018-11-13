package com.kuyu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.TpmAttendenceMapper;
import com.kuyu.model.TpmAttendenceModel;
import com.kuyu.service.TpmAttendenceService;

@Service
@Transactional
public class TpmAttendenceServiceImpl extends ServiceImpl<TpmAttendenceMapper, TpmAttendenceModel> implements TpmAttendenceService {

	@Override
	public String selectUrlByAttendenceUuid(String uuid) {
		return baseMapper.selectUrlByAttendenceUuid(uuid);
	}

	@Override
	public TpmAttendenceModel selectByTusUuid(String uuid) {
		return baseMapper.selectByTusUuid(uuid);
	}

}
