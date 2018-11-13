package com.kuyu.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.TpmFinancialModel;
import com.kuyu.vo.ActivityDetailVo;
import com.kuyu.vo.ResultVo;

public interface TpmFinancialService extends IService<TpmFinancialModel>{

	/**
	 * 根据财务人员编号查询所负责的所有活动
	 * @param person_code
	 * @param size 
	 * @param current 
	 * @return
	 */
	Page<ActivityDetailVo>queryActivity(String person_code,String state, Integer current, Integer size);


	/**
	 *  财务人员审核结算单
	 * @param map
	 * @return
	 */
	public ResultVo financialAudit(Map<String,Object> map) throws Exception;
}
