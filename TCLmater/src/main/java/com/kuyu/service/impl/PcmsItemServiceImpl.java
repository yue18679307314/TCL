package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.mapper.pcms.PcmsItemLogMapper;
import com.kuyu.mapper.pcms.PcmsItemMapper;
import com.kuyu.mapper.pcms.PcmsMaterialMapper;
import com.kuyu.mapper.pcms.PcmsMaterialSourceMapper;
import com.kuyu.mapper.pcms.PcmsOthertmMapper;
import com.kuyu.mapper.pcms.PcmsOthertmSourceMapper;
import com.kuyu.mapper.pcms.PcmsProjectMapper;
import com.kuyu.mapper.pcms.PcmsShowcaseMapper;
import com.kuyu.mapper.pcms.PcmsShowcaseSourceMapper;
import com.kuyu.model.pcms.PcmsItem;
import com.kuyu.model.pcms.PcmsItemExample;
import com.kuyu.model.pcms.PcmsItemLog;
import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.model.pcms.PcmsMaterialExample;
import com.kuyu.model.pcms.PcmsProject;
import com.kuyu.model.pcms.PcmsShowcase;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.MaterialResult;
import com.kuyu.vo.pcms.OthertmResult;
import com.kuyu.vo.pcms.PcmsProjectVo;
import com.kuyu.vo.pcms.ShowcaseResult;

@Service
//@Transactional
public class PcmsItemServiceImpl implements PcmsItemService{

	@Autowired
	private PcmsProjectMapper pcmsProjectMapper;
	
	@Autowired
	private PcmsItemMapper pcmsItemMapper;
	
	@Autowired
	private PcmsMaterialMapper pcmsMaterialMapper;
	
	@Autowired
	private PcmsOthertmMapper pcmsOthertmMapper;
	
	@Autowired
	private PcmsShowcaseMapper pcmsShowcaseMapper;
	
	@Autowired
	private PcmsShowcaseSourceMapper pcmsShowcaseSourceMapper;
	
	@Autowired
	private PcmsMaterialSourceMapper pcmsMaterialSourceMapper;
	
	@Autowired
	private PcmsOthertmSourceMapper pcmsOthertmSourceMapper;
	
	@Autowired
	private PcmsItemLogMapper pcmsItemLogMapper;
	
	
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
		if(approvalStatrTime!=null&&approvalEndTime!=null){
			param.put("approvalStatrTime", approvalStatrTime);
			param.put("approvalEndTime", approvalEndTime);
		}
		if(status!=null){
			param.put("status", status);
		}
		
		//分页查询
		Page<ItemResult> page=new Page<>(current, size);
		List<ItemResult> result=pcmsItemMapper.getItemListByParam(param);
		Integer count=pcmsItemMapper.getItemListCountByParam(param);
		page.setRecords(result);
		page.setTotal(count);
		return page;
	}



	@Override
	public ItemDetail getItemDetailById(Integer itid,Integer type) {
		ItemDetail result=pcmsItemMapper.getItemItemDetailById(itid);
		
		// 类型1表示展台展柜，2表示其他终端，3表示广告物料
		Integer itType = result.getItType();
		if(type==1){
			if (itType == 1) {
				List<ShowcaseResult> scList = pcmsShowcaseMapper.selectByItid(itid);
				result.setScList(scList);
			}
			if (itType == 2) {
				List<OthertmResult> otList = pcmsOthertmMapper.selectByItid(itid);
				result.setOtList(otList);
			}
			if (itType == 3) {
				List<MaterialResult> mrList = pcmsMaterialMapper.selectByItid(itid);
				result.setMrList(mrList);
			}
		}else{
			if (itType == 1) {
				List<ShowcaseResult> scList = pcmsShowcaseSourceMapper.selectByItid(itid);
				result.setScList(scList);
			}
			if (itType == 2) {
				List<OthertmResult> otList = pcmsOthertmSourceMapper.selectByItid(itid);
				result.setOtList(otList);
			}
			if (itType == 3) {
				List<MaterialResult> mrList = pcmsMaterialSourceMapper.selectByItid(itid);
				result.setMrList(mrList);
			}
		}
		return result;
	}



	@Override
	public int editMaterial(PcmsMaterial metarial) {
		PcmsMaterialExample example=new PcmsMaterialExample();
		example.createCriteria().andMridEqualTo(metarial.getMrid());
		return pcmsMaterialMapper.updateByExampleSelective(metarial, example);
	}


	
	
	//0:未接单 1:已接单制作中 2待验收 3待结算 4已驳回 5结算中 6结算失败 7已结算 -1已作废
	@Override
	public ResultVo changeItemStatus(Integer itid,Integer status,String reason) {
		//TODO
		//检测是否有权限操作
		
		
		if(status==5){
			//TODO
			//通知共享系统处理结算
		}
		
		 //增加立项单日志
	    PcmsItemLog log=new PcmsItemLog();
	    log.setItid(itid);
	    log.setStatus(status);
	    if(status==4){
	    	log.setNote("驳回原因:"+reason);
	    }
	    if(status==3){
	    	log.setNote("物料已验收，费用待结算");
	    }
	    if(status==-1){
	    	log.setNote("作废原因:"+reason);
	    }
	    log.setCreateTime(new Date());
	    pcmsItemLogMapper.insertSelective(log);
		
		
		
		//更新为对应状态
		PcmsItemExample example=new PcmsItemExample();
		example.createCriteria().andItidEqualTo(itid);
		PcmsItem item=new PcmsItem();
		item.setStatus(status);
		item.setUpdateTime(new Date());
		int i= pcmsItemMapper.updateByExampleSelective(item, example);
		if(i==1){
			return ResultVo.get(ResultVo.SUCCESS);
		}
		return ResultVo.get(ResultVo.FAIL);
	}

}
