package com.kuyu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kuyu.mapper.pcms.PcmsProjectMapper;
import com.kuyu.service.PcmsProjectService;

@Service
public class PcmsProjectServiceImpl implements PcmsProjectService{

	@Autowired
	private PcmsProjectMapper pcmsProjectMapper;
	
}
