package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kuyu.mapper.pcms.PcmsItemMapper;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.MaterialResult;

@Service
//@Transactional
public class PcmsItemServiceImpl implements PcmsItemService{

	@Autowired
	private PcmsItemMapper pcmsItemMapper;
	

	@Override
	public List<ItemResult> getItemListByParam(String searchKey) {
		return pcmsItemMapper.getItemListByParam(searchKey);
	}



	@Override
	public ItemDetail getItemItemDetailById(Integer itid) {
		ItemDetail result=pcmsItemMapper.getItemItemDetailById(itid);
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
