package com.kuyu.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmSingleUserModel;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.PersonCodeVo;
import com.kuyu.vo.TpmSingleUserVo;

public interface TpmSingleUserService extends IService<TpmSingleUserModel>{
	
	public Integer insertSingleUser(String fsscBill,List<PersonCodeVo> personCode,LoginUserInfo userInfo) throws Exception;
	
	public FinancialResultVo post(TpmSingleUserVo tpmSingleUserVo) throws Exception;
}
