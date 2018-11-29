package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.mapper.pcms.PcmsItemMapper;
import com.kuyu.mapper.pcms.PcmsMaterialMapper;
import com.kuyu.mapper.pcms.PcmsOthertmMapper;
import com.kuyu.mapper.pcms.PcmsShowcaseMapper;
import com.kuyu.model.pcms.PcmsItem;
import com.kuyu.model.pcms.PcmsItemExample;
import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.model.pcms.PcmsMaterialExample;
import com.kuyu.model.pcms.PcmsShowcase;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.MaterialResult;
import com.kuyu.vo.pcms.OthertmResult;
import com.kuyu.vo.pcms.ShowcaseResult;

@Service
//@Transactional
public class PcmsItemServiceImpl implements PcmsItemService{

	@Autowired
	private PcmsItemMapper pcmsItemMapper;
	
	@Autowired
	private PcmsMaterialMapper pcmsMaterialMapper;
	
	@Autowired
	private PcmsOthertmMapper pcmsOthertmMapper;
	
	@Autowired
	private PcmsShowcaseMapper pcmsShowcaseMapper;
	
	
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
	public ItemDetail getItemDetailById(Integer itid) {
		ItemDetail result=pcmsItemMapper.getItemItemDetailById(itid);
		
		//类型1表示展台展柜，2表示其他终端，3表示广告物料
		Integer itType=result.getItType();
		if(itType==1){
			List<ShowcaseResult> scList=pcmsShowcaseMapper.selectByItid(itid);
			result.setScList(scList);
		}
		if(itType==2){
			List<OthertmResult> otList=pcmsOthertmMapper.selectByItid(itid);
			result.setOtList(otList);
		}
		if(itType==3){
			List<MaterialResult> mrList=pcmsMaterialMapper.selectByItid(itid);
			result.setMrList(mrList);
		}
		
		return result;
	}



	@Override
	public int editMaterial(PcmsMaterial metarial) {
		PcmsMaterialExample example=new PcmsMaterialExample();
		example.createCriteria().andMridEqualTo(metarial.getMrid());
		return pcmsMaterialMapper.updateByExampleSelective(metarial, example);
	}



	@Override
	public int abolishItem(Integer itid) {
		PcmsItemExample example=new PcmsItemExample();
		example.createCriteria().andItidEqualTo(itid);
		PcmsItem item=new PcmsItem();
		item.setStatus(-1);
		item.setUpdateTime(new Date());
		return pcmsItemMapper.updateByExampleSelective(item, example);
	}

}
