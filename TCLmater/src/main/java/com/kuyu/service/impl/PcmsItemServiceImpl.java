package com.kuyu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuyu.mapper.pcms.PcmsBillMapper;
import com.kuyu.model.pcms.PcmsBill;
import com.kuyu.service.PcmsItemService;

@Service
//@Transactional
public class PcmsItemServiceImpl implements PcmsItemService{

	@Autowired
	private PcmsBillMapper pcmsBillMapper;
	
	@Override
	public PcmsBill selectBillByid(Integer id) {
		return pcmsBillMapper.selectByPrimaryKey(id);
	}

}
