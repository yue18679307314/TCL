package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.mapper.pcms.PcmsItemMapper;
import com.kuyu.mapper.pcms.PcmsMaterialMapper;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.MaterialResult;

@Service
//@Transactional
public class PcmsItemServiceImpl implements PcmsItemService{

	@Autowired
	private PcmsItemMapper pcmsItemMapper;
	
	@Autowired
	private PcmsMaterialMapper pcmsMaterialMapper;
	
	
	@Override
	public Page<ItemResult> getItemListByParam(String searchKey,Integer current,Integer size,
			String companyCode,String userType,String deptCode,String approvalStatrTime,
			String approvalEndTime,Integer status) {
		
		Integer linimt=(current-1)*size;
		
		//组装参数
		HashMap<String, Object> param=new HashMap<>();
		if(companyCode!=null&&!companyCode.equals("")){
			param.put("companyCode", companyCode);
		}
		param.put("deptCode", deptCode);
		param.put("userType", userType);
		if(searchKey!=null&&!searchKey.equals("")){
			param.put("searchKey", "%"+searchKey+"%");
		}
		param.put("linimt", linimt);
		param.put("size", size);
		param.put("approvalStatrTime", approvalStatrTime);
		param.put("approvalEndTime", approvalEndTime);
		param.put("status", status);
		
		//分页查询
		Page<ItemResult> page=new Page<>(current, size);
		List<ItemResult> result=pcmsItemMapper.getItemListByParam(param);
		Integer count=pcmsItemMapper.getItemListCountByParam(param);
		page.setRecords(result);
		page.setTotal(count);
		return page;
	}



	@Override
	public ItemDetail getItemItemDetailById(Integer itid) {
		ItemDetail result=pcmsItemMapper.getItemItemDetailById(itid);
		
		//pcmsMaterialMapper
		List<MaterialResult> mrList=new ArrayList<>();
		MaterialResult e=new MaterialResult();
		e.setMrname("密度板");
		e.setUnit("条");
		e.setNumber(11);
		e.setSpecifications("500*120mm");
		e.setPrice(100d);
		mrList.add(e);
		MaterialResult b=new MaterialResult();
		b.setMrname("密度板2");
		b.setUnit("印刷");
		b.setNumber(12);
		mrList.add(b);
		result.setMrList(mrList);
		return result;
	}

}
