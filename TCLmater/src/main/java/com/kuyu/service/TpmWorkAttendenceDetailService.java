package com.kuyu.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.TpmWorkAttendenceDetailModel;
import com.kuyu.vo.ResultVo;

public interface TpmWorkAttendenceDetailService extends IService<TpmWorkAttendenceDetailModel>{

	/**
	 * 打卡
	 * @param tpmWorkAttendenceDetailModel
	 * @return
	 * @throws Exception
	 */
	public ResultVo signIn(TpmWorkAttendenceDetailModel tpmWorkAttendenceDetailModel) throws Exception;
	
	/**
	 * 查询打卡记录
	 * @param map
	 * @return
	 */
	public List<TpmWorkAttendenceDetailModel> queryList(Map<String,Object> map);
}
