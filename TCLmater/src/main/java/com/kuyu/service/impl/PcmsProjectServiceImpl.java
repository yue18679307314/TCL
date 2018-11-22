package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.kuyu.common.CommonConstants;
import com.kuyu.mapper.pcms.PcmsItemMapper;
import com.kuyu.mapper.pcms.PcmsItemRelationMapper;
import com.kuyu.mapper.pcms.PcmsMaterialMapper;
import com.kuyu.mapper.pcms.PcmsOthertmMapper;
import com.kuyu.mapper.pcms.PcmsProjectMapper;
import com.kuyu.mapper.pcms.PcmsShopMapper;
import com.kuyu.mapper.pcms.PcmsShowcaseMapper;
import com.kuyu.model.pcms.PcmsItem;
import com.kuyu.model.pcms.PcmsItemRelation;
import com.kuyu.model.pcms.PcmsItemRelationExample;
import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.model.pcms.PcmsMaterialExample;
import com.kuyu.model.pcms.PcmsOthertm;
import com.kuyu.model.pcms.PcmsOthertmExample;
import com.kuyu.model.pcms.PcmsProject;
import com.kuyu.model.pcms.PcmsProjectExample;
import com.kuyu.model.pcms.PcmsShop;
import com.kuyu.model.pcms.PcmsShowcase;
import com.kuyu.model.pcms.PcmsShowcaseExample;
import com.kuyu.service.PcmsProjectService;
import com.kuyu.util.PcmsProjectUtil;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.pcms.PcmsProjectVo;

@Service
public class PcmsProjectServiceImpl implements PcmsProjectService{

	@Autowired
	private PcmsProjectMapper pcmsProjectMapper;
	
	@Autowired
	private PcmsShopMapper pcmsShopMapper;
	
	@Autowired
	private PcmsShowcaseMapper pcmsShopcaseMapper;
	
	@Autowired
	private PcmsMaterialMapper pcmsMaterialMapper;
	
	@Autowired
	private PcmsOthertmMapper pcmsOthertmMapper;
	
	@Autowired
	private PcmsItemMapper pcmsItemMapper;
	
	@Autowired
	private PcmsItemRelationMapper pcmsItemRelationMapper;

	@Override
	public String importProjectDetail(PcmsProjectVo projectvo) {
		
		//当前时间
		Date createTime=new Date();
		
		//立项单ID
		String  requestId=projectvo.getRequestId();
		
		//查询该立项单是否存在
		PcmsProject query=pcmsProjectMapper.selectByPrimaryKey(requestId);
		if(query!=null){
			throw new RuntimeException("该立项单已存在");
		}
		
		
		//类型1表示展台展柜，2表示其他终端，3表示广告物料
		Integer projectType=Integer.valueOf(projectvo.getType());
		
		//写入原始立项单数据
		PcmsProject peojct=new PcmsProject();
		BeanUtils.copyProperties(projectvo, peojct);
		peojct.setRequestJson(JSON.toJSONString(projectvo));
		peojct.setCreatTime(createTime);
		peojct.setStatus(1);
		pcmsProjectMapper.insertSelective(peojct);
		
		
		//判断是什么类型的立项单
		if(projectType==null){
			throw new RuntimeException("导入的立项单类型不能为空");
		}
		//展台展柜
		if(projectType==1){
			
			createShop(projectvo);
			
			List<PcmsShowcase> showcaseList=projectvo.getShowcaseList();
			for (PcmsShowcase showcase : showcaseList) {
				showcase.setCreateTime(createTime);
				pcmsShopcaseMapper.insertSelective(showcase);
			}
			//生成结算单
			createItem(requestId,projectType);
			
		}
		//其他终端
		if(projectType==2){
			createShop(projectvo);
			List<PcmsOthertm> oTList=projectvo.getOtherTerminalList();
			for (PcmsOthertm oT : oTList) {
				oT.setCreateTime(createTime);
				pcmsOthertmMapper.insertSelective(oT);
			}
			
			//生成结算单
			createItem(requestId,projectType);
		}
		//物料--物料数据目前不走这里！！！！
		if(projectType==3){
			List<PcmsMaterial> materialList=projectvo.getMaterialList();
			if(materialList!=null&&materialList.size()>0){
				for (PcmsMaterial material : materialList) {
					pcmsMaterialMapper.insertSelective(material);
				}
				//生成结算单
				createItem(requestId,projectType);
			}else{
				//数据不对
				peojct.setStatus(2);
			}
		}
		
		
		
		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));
	}
	
	
	
	
	private void createItem(String requestId,Integer projectType) {
		
		//查询原始立项单
		PcmsProject project=pcmsProjectMapper.selectByPrimaryKey(requestId);
		//截取公司代码
		String companyCodeAndName=project.getRequestCompanyCode();
		String companyCode=companyCodeAndName
				.substring(companyCodeAndName.length()-5, companyCodeAndName.length())
				.replace(")", "").trim();
		
		// 展台展柜
		if (projectType == 1) {
			PcmsShowcaseExample scParam=new PcmsShowcaseExample();
			scParam.createCriteria().andResuestIdEqualTo(requestId);
			List<PcmsShowcase> showcaseList = pcmsShopcaseMapper.selectByExample(scParam);
			
			if (showcaseList != null && showcaseList.size() > 0) {
				 List<PcmsShowcase> group = new ArrayList<PcmsShowcase>();  
			        for (PcmsShowcase ma : showcaseList) {  
			            boolean state = false;  
			            for (PcmsShowcase mas : group) {  
			                if(ma.getVendorId().equals(mas.getVendorId())){  
			                	
			                    state = true;  
			                }  
			            }  
			            if(!state){  
			            	group.add(ma);  
			            } 
			            
			            //写入对应关系
			            PcmsItemRelation itRe=new PcmsItemRelation();
			            itRe.setType(requestId);
			            itRe.setRelationId(ma.getScid());
			            itRe.setCreateTime(new Date());
			            pcmsItemRelationMapper.insertSelective(itRe);
			        }  
			        for (PcmsShowcase insert : group) {
			        	PcmsItem item=new PcmsItem();
			        	//生成立项单唯一编号
			    		item.setItemNumber(PcmsProjectUtil.creatItemNumber());
			    		item.setCreateTime(new Date());
			    		item.setVendorId(insert.getVendorId());
			    		item.setRequestId(requestId);
//			    		item.setItemPrice(99d);
			    		item.setItType(1);
			    		item.setStatus(0);
			    		//设置立项单的分公司标识
			    		item.setRequestCompanyCode(companyCode);
			    		pcmsItemMapper.insertSelective(item);
			    		
			    		//更新中间表
			    		PcmsItemRelation record=new PcmsItemRelation();
			    		record.setItid(item.getItid());
			    		PcmsItemRelationExample example=new PcmsItemRelationExample();
			    		example.createCriteria().andTypeEqualTo(requestId);
			    		pcmsItemRelationMapper.updateByExampleSelective(record, example);
			    		
			    		//拆单成功
			    		project.setStatus(0);
			    		PcmsProjectExample example2=new PcmsProjectExample();
			    		example2.createCriteria().andRequestIdEqualTo(requestId);
			    		pcmsProjectMapper.updateByExampleSelective(project, example2);
			        }  
				
			} 
		}
		// 其他终端
		if (projectType == 2) {
			PcmsOthertmExample otParam=new PcmsOthertmExample();
			otParam.createCriteria().andRequestIdEqualTo(requestId);
			List<PcmsOthertm> othertmList = pcmsOthertmMapper.selectByExample(otParam);
			
			if (othertmList != null && othertmList.size() > 0) {
				 List<PcmsOthertm> group = new ArrayList<PcmsOthertm>();  
			        for (PcmsOthertm ma : othertmList) {  
			            boolean state = false;  
			            for (PcmsOthertm mas : group) {  
			                if(ma.getVendorId().equals(mas.getVendorId())){  
			                	
			                    state = true;  
			                }  
			            }  
			            if(!state){  
			            	group.add(ma);  
			            } 
			            
			            //写入对应关系
			            PcmsItemRelation itRe=new PcmsItemRelation();
			            itRe.setType(requestId);
			            itRe.setRelationId(ma.getOtid());
			            itRe.setCreateTime(new Date());
			            pcmsItemRelationMapper.insertSelective(itRe);
			        }  
			        for (PcmsOthertm insert : group) {
			        	PcmsItem item=new PcmsItem();
			        	//生成立项单唯一编号
			    		item.setItemNumber(PcmsProjectUtil.creatItemNumber());
			    		item.setCreateTime(new Date());
			    		item.setVendorId(insert.getVendorId());
			    		item.setRequestId(requestId);
//			    		item.setItemPrice(99d);
			    		item.setItType(2);
			    		item.setStatus(0);
			    		//设置立项单的分公司标识
			    		item.setRequestCompanyCode(companyCode);
			    		pcmsItemMapper.insertSelective(item);
			    		
			    		//更新中间表
			    		PcmsItemRelation record=new PcmsItemRelation();
			    		record.setItid(item.getItid());
			    		PcmsItemRelationExample example=new PcmsItemRelationExample();
			    		example.createCriteria().andTypeEqualTo(requestId);
			    		pcmsItemRelationMapper.updateByExampleSelective(record, example);
			    		
			    		//拆单成功
			    		project.setStatus(0);
			    		PcmsProjectExample example2=new PcmsProjectExample();
			    		example2.createCriteria().andRequestIdEqualTo(requestId);
			    		pcmsProjectMapper.updateByExampleSelective(project, example2);
			        }  
				
			} 
		}
		// 物料
		if (projectType == 3) {
			PcmsMaterialExample maParam=new PcmsMaterialExample();
			maParam.createCriteria().andResuestIdEqualTo(requestId);
			List<PcmsMaterial> materialList = pcmsMaterialMapper.selectByExample(maParam);
			if (materialList != null && materialList.size() > 0) {
				 List<PcmsMaterial> group = new ArrayList<PcmsMaterial>();  
			        for (PcmsMaterial ma : materialList) {  
			            boolean state = false;  
			            for (PcmsMaterial mas : group) {  
			                if(ma.getVendorId().equals(mas.getVendorId())){  
			                	
			                    state = true;  
			                }  
			            }  
			            if(!state){  
			            	group.add(ma);  
			            } 
			            
			            //写入对应关系
			            PcmsItemRelation itRe=new PcmsItemRelation();
			            itRe.setType(requestId);
			            itRe.setRelationId(ma.getMrid());
			            itRe.setCreateTime(new Date());
			            pcmsItemRelationMapper.insertSelective(itRe);
			        }  
			        for (PcmsMaterial insert : group) {
			        	PcmsItem item=new PcmsItem();
			    		item.setItemNumber("JS1234");
			    		item.setCreateTime(new Date());
			    		item.setVendorId(insert.getVendorId());
			    		item.setRequestId(requestId);
//			    		item.setItemPrice(999d);
			    		item.setItType(3);
			    		item.setStatus(0);
			    		pcmsItemMapper.insertSelective(item);
			    		
			    		//更新中间表
			    		PcmsItemRelation record=new PcmsItemRelation();
			    		record.setItid(item.getItid());
			    		PcmsItemRelationExample example=new PcmsItemRelationExample();
			    		example.createCriteria().andTypeEqualTo(requestId);
			    		pcmsItemRelationMapper.updateByExampleSelective(record, example);
			    		
			    		//拆单成功
			    		project.setStatus(0);
			    		PcmsProjectExample example2=new PcmsProjectExample();
			    		example2.createCriteria().andRequestIdEqualTo(requestId);
			    		pcmsProjectMapper.updateByExampleSelective(project, example2);
			        }  
				
			} 
		}

	}
	
	//创建门店信息
	private void createShop(PcmsProjectVo projectvo) {
		PcmsShop shop=new PcmsShop();
		BeanUtils.copyProperties(projectvo, shop);
		shop.setCreateTime(new Date());
		pcmsShopMapper.insertSelective(shop);
//		if(i==1){
//			return true;
//		}
//		return false;
	}




	@Override
	public PcmsProjectVo getProjectDeatil(String requestId) {
		PcmsProjectVo result=pcmsProjectMapper.getProjectDeatil(requestId);
		String type=result.getType();
		//展台展柜
		if(type.equals("1")){
			PcmsShowcaseExample scParam=new PcmsShowcaseExample();
			scParam.createCriteria().andResuestIdEqualTo(requestId);
			List<PcmsShowcase> showcaseList = pcmsShopcaseMapper.selectByExample(scParam);
			result.setShowcaseList(showcaseList);
		}
		//其他终端
		if(type.equals("2")){
			PcmsOthertmExample otParam=new PcmsOthertmExample();
			otParam.createCriteria().andRequestIdEqualTo(requestId);
			List<PcmsOthertm> otList = pcmsOthertmMapper.selectByExample(otParam);
			result.setOtherTerminalList(otList);
		}
		//物料
		if(type.equals("3")){
			PcmsMaterialExample maParam=new PcmsMaterialExample();
			maParam.createCriteria().andResuestIdEqualTo(requestId);
			List<PcmsMaterial> materialList = pcmsMaterialMapper.selectByExample(maParam);
			result.setMaterialList(materialList);
		}
		
		return result;
	}
	
	
}
