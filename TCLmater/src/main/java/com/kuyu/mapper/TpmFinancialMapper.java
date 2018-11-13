package com.kuyu.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.TpmFinancialModel;

public interface TpmFinancialMapper extends BaseMapper<TpmFinancialModel>{

//	List<Object>queryChildDept(Map<String,Object> map);
	
//	public TpmFinancialModel queryFinancial(String person_code);
	
	public Integer financialAudit(Map<String,Object> map);
}
