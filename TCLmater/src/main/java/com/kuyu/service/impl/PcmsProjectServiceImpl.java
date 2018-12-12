package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kuyu.common.CommonConstants;
import com.kuyu.mapper.TpmEmployeeMapper;
import com.kuyu.mapper.pcms.PcmsItemMapper;
import com.kuyu.mapper.pcms.PcmsItemRelationMapper;
import com.kuyu.mapper.pcms.PcmsMaterialMapper;
import com.kuyu.mapper.pcms.PcmsMaterialSourceMapper;
import com.kuyu.mapper.pcms.PcmsOthertmMapper;
import com.kuyu.mapper.pcms.PcmsOthertmSourceMapper;
import com.kuyu.mapper.pcms.PcmsOutdoorsMapper;
import com.kuyu.mapper.pcms.PcmsProjectDeatilMapper;
import com.kuyu.mapper.pcms.PcmsProjectMapper;
import com.kuyu.mapper.pcms.PcmsShopMapper;
import com.kuyu.mapper.pcms.PcmsShowcaseMapper;
import com.kuyu.mapper.pcms.PcmsShowcaseSourceMapper;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.pcms.PcmsItem;
import com.kuyu.model.pcms.PcmsItemLog;
import com.kuyu.model.pcms.PcmsItemRelation;
import com.kuyu.model.pcms.PcmsItemRelationExample;
import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.model.pcms.PcmsMaterialExample;
import com.kuyu.model.pcms.PcmsMaterialSource;
import com.kuyu.model.pcms.PcmsOthertm;
import com.kuyu.model.pcms.PcmsOthertmExample;
import com.kuyu.model.pcms.PcmsOthertmSource;
import com.kuyu.model.pcms.PcmsOutdoors;
import com.kuyu.model.pcms.PcmsProject;
import com.kuyu.model.pcms.PcmsProjectDeatil;
import com.kuyu.model.pcms.PcmsProjectExample;
import com.kuyu.model.pcms.PcmsShop;
import com.kuyu.model.pcms.PcmsShowcase;
import com.kuyu.model.pcms.PcmsShowcaseExample;
import com.kuyu.model.pcms.PcmsShowcaseSource;
import com.kuyu.service.PcmsProjectService;
import com.kuyu.util.PcmsProjectUtil;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsProjectVo;
import com.kuyu.vo.pcms.PcmsProjectVo2;
import com.kuyu.vo.pcms.ProjectDetailVo;
import com.kuyu.vo.pcms.RequestUserVo;
import com.kuyu.vo.project.OtherFeeOriginalModelVo;
import com.kuyu.vo.project.ProjectDetialModelVo;
import com.kuyu.vo.project.TpmActivityOriginalModelVo;

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
	
	@Autowired
	private PcmsShowcaseSourceMapper pcmsShowcaseSourceMapper;
	
	@Autowired
	private PcmsMaterialSourceMapper pcmsMaterialSourceMapper;
	
	@Autowired
	private PcmsOthertmSourceMapper pcmsOthertmSourceMapper;
	
	@Autowired
	private TpmEmployeeMapper tpmEmployeeMapper;
	
	@Autowired
	private PcmsProjectDeatilMapper pcmsProjectDeatilMapper;
	
	@Autowired
	private PcmsOutdoorsMapper pcmsOutdoorsMapper;

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
		peojct.setStatus(0);
		pcmsProjectMapper.insertSelective(peojct);
		
		
		
		//判断是什么类型的立项单
		if(projectType==null){
			throw new RuntimeException("导入的立项单类型不能为空");
		}
		
		//展台展柜
		if(projectType==1){
			//创建门店信息
			createShop(projectvo);
			
			//写入展台展柜数据
			List<PcmsShowcase> showcaseList=projectvo.getShowcaseList();
			for (PcmsShowcase showcase : showcaseList) {
				showcase.setCreateTime(createTime);
				pcmsShopcaseMapper.insertSelective(showcase);
			}
			
			//拆单
			splitProjet(requestId,projectType);
			
		}
		
		//其他终端
		if(projectType==2){
			//创建门店信息
			createShop(projectvo);
			
			//写入其他终端数据
			List<PcmsOthertm> oTList=projectvo.getOtherTerminalList();
			for (PcmsOthertm oT : oTList) {
				oT.setCreateTime(createTime);
				pcmsOthertmMapper.insertSelective(oT);
			}
			
			//拆单
			splitProjet(requestId,projectType);
		}
		
		//物料--物料数据目前不走这里！！！！
		if(projectType==3){
			
			//写入物料数据
			List<PcmsMaterial> materialList=projectvo.getMaterialList();
			if(materialList!=null&&materialList.size()>0){
				for (PcmsMaterial material : materialList) {
					pcmsMaterialMapper.insertSelective(material);
				}
				//拆单
				splitProjet(requestId,projectType);
			}else{
				//数据不对
				peojct.setStatus(2);
			}
		}
		
		
		
		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));
	}
	
	
	
	
	private void splitProjet(String requestId,Integer projectType) {
		
		
		
		// 展台展柜
		if (projectType == 1) {
			
			//根据立项单ID查询展台展柜数据
			PcmsShowcaseExample scParam=new PcmsShowcaseExample();
			scParam.createCriteria().andResuestIdEqualTo(requestId);
			List<PcmsShowcase> showcaseList = pcmsShopcaseMapper.selectByExample(scParam);
			
			//根据供应商分类并且写入订单表
			if (showcaseList != null && showcaseList.size() > 0) {
				 List<PcmsShowcase> group = new ArrayList<PcmsShowcase>(); 
				 Double sumPrice=0d;//汇总价格
				 Map<Integer,String> itemNumberMap=new HashMap<Integer,String>(); 
				 String itNumber="";
			        for (PcmsShowcase ma : showcaseList) {
			        	//复制数据
		            	PcmsShowcaseSource soure=new PcmsShowcaseSource();
		            	BeanUtils.copyProperties(ma, soure);
		            	pcmsShowcaseSourceMapper.insertSelective(soure);
			        	
			        	
			        	
			            boolean state = false;  
			            
			            //写入对应关系
			            PcmsItemRelation itRe=new PcmsItemRelation();
			            
			            for (PcmsShowcase mas : group) {
			                if(ma.getVendorId().equals(mas.getVendorId())){  
			                	sumPrice+=Double.valueOf(ma.getChildren1Amount());
			                    state = true;  
			                }  
			            }  
			            if(!state){  
			            	//创建唯一订单号
			            	itNumber=PcmsProjectUtil.creatItemNumber();
			            	itemNumberMap.put(ma.getScid(), itNumber);
			            	group.add(ma);
			            } 
			            itRe.setType(itNumber);
			            itRe.setRelationId(ma.getScid());
			            itRe.setCreateTime(new Date());
			            pcmsItemRelationMapper.insertSelective(itRe);
			            
			           
			        }  
			        for (PcmsShowcase insert : group) {
			        	createItem(requestId,insert.getVendorId(),projectType,sumPrice,itemNumberMap.get(insert.getScid()));
			        }  
				
			} 
		}
		
		// 其他终端
		if (projectType == 2) {
			
			//根据立项单ID查询其他终端数据
			PcmsOthertmExample otParam=new PcmsOthertmExample();
			otParam.createCriteria().andRequestIdEqualTo(requestId);
			List<PcmsOthertm> othertmList = pcmsOthertmMapper.selectByExample(otParam);
			
			//根据供应商分类并且写入订单表
			if (othertmList != null && othertmList.size() > 0) {
				 List<PcmsOthertm> group = new ArrayList<PcmsOthertm>();  
				 Double sumPrice=0d;//汇总价格
				 Map<Integer,String> itemNumberMap=new HashMap<Integer,String>(); 
				 String itNumber="";
			        for (PcmsOthertm ma : othertmList) {
			         	//复制数据
		            	PcmsOthertmSource soure=new PcmsOthertmSource();
		            	BeanUtils.copyProperties(ma, soure);
		            	pcmsOthertmSourceMapper.insertSelective(soure);
			        	
			        	
			            boolean state = false;  
			            
			            //写入对应关系
			            PcmsItemRelation itRe=new PcmsItemRelation();
			            
			            for (PcmsOthertm mas : group) {  
			                if(ma.getVendorId().equals(mas.getVendorId())){  
			                	sumPrice+=Double.valueOf(ma.getChildren3Amount());
			                    state = true;  
			                }  
			            }  
			            if(!state){  
			            	//创建唯一订单号
			            	itNumber=PcmsProjectUtil.creatItemNumber();
			            	itemNumberMap.put(ma.getOtid(), itNumber);
			            	group.add(ma);
			            } 
			            itRe.setType(itNumber);
			            itRe.setRelationId(ma.getOtid());
			            itRe.setCreateTime(new Date());
			            pcmsItemRelationMapper.insertSelective(itRe);
			        }  
			        for (PcmsOthertm insert : group) {
			        	createItem(requestId,insert.getVendorId(),projectType,sumPrice,itemNumberMap.get(insert.getOtid()));
			        }  
				
			} 
		}
		
		// 物料
		if (projectType == 3) {
			
			//根据立项单ID查询物料数据
			PcmsMaterialExample maParam=new PcmsMaterialExample();
			maParam.createCriteria().andResuestIdEqualTo(requestId);
			List<PcmsMaterial> materialList = pcmsMaterialMapper.selectByExample(maParam);
			
			//根据供应商分类并且写入订单表
			if (materialList != null && materialList.size() > 0) {
				 List<PcmsMaterial> group = new ArrayList<PcmsMaterial>();  
				 Double sumPrice=0d;//汇总价格
				 Map<Integer,String> itemNumberMap=new HashMap<Integer,String>(); 
				 String itNumber="";
			        for (PcmsMaterial ma : materialList) {
			         	//复制数据
		            	PcmsMaterialSource soure=new PcmsMaterialSource();
		            	BeanUtils.copyProperties(ma, soure);
		            	pcmsMaterialSourceMapper.insertSelective(soure);
			        	
			        	
		            	boolean state = false;  
			        	
			        	//写入对应关系
			            PcmsItemRelation itRe=new PcmsItemRelation();
			        	
			            for (PcmsMaterial mas : group) {
			                if(ma.getVendorId().equals(mas.getVendorId())){
			                	sumPrice+=ma.getComparisonPrice()*ma.getNumber();//单价*数量
			                    state = true;  
			                }  
			            }  
			            if(!state){
			            	//创建唯一订单号
			            	itNumber=PcmsProjectUtil.creatItemNumber();
			            	itemNumberMap.put(ma.getMrid(), itNumber);
			            	group.add(ma); 
			            } 
			           
			            itRe.setType(itNumber);
			            itRe.setRelationId(ma.getMrid());
			            itRe.setCreateTime(new Date());
			            pcmsItemRelationMapper.insertSelective(itRe);
			        }  
			        
			        //遍历组信息，新建订单
			        for (PcmsMaterial insert : group) {
			        	createItem(requestId,insert.getVendorId(),projectType,sumPrice,itemNumberMap.get(insert.getMrid()));
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

	//创建立项单
	private Integer createItem(String requestId,String vendorId,Integer projectType,Double price,String itemNumber) {
		
		Date createTime=new Date();
		
		// 查询原始立项单
		PcmsProject project = pcmsProjectMapper.selectByPrimaryKey(requestId);
		
		// 截取公司代码
		String companyCodeAndName = project.getRequestCompanyCode();
		String companyCode = companyCodeAndName.substring(companyCodeAndName.length() - 5, companyCodeAndName.length())
				.replace(")", "").trim();
		
		//写入订单表
		PcmsItem item=new PcmsItem();
		item.setItemNumber(itemNumber);//生成订单唯一编号
		item.setCreateTime(createTime);
		item.setVendorId(vendorId);//设置供应商
		item.setRequestId(requestId);//写入原始立项单ID
		item.setItType(projectType);//设置订单类型
		item.setStatus(0);//设置订单状态为未接单
		item.setRequestCompanyCode(companyCode);//设置订单的分公司标识
		item.setItemPrice(price);//设置该单的价格
		pcmsItemMapper.insertSelective(item);
		
		updateItemRelation(item.getItid(),itemNumber);
		
		
		//更新原始立项单表，设置状态为拆单成功
		project.setStatus(0);
		PcmsProjectExample example2=new PcmsProjectExample();
		example2.createCriteria().andRequestIdEqualTo(requestId);
		pcmsProjectMapper.updateByExampleSelective(project, example2);
		
		
		return item.getItid();
	}

	//更新中间表
	private void updateItemRelation(Integer itid,String itemNumber){
		PcmsItemRelation record=new PcmsItemRelation();
		record.setItid(itid);
		PcmsItemRelationExample example=new PcmsItemRelationExample();
		example.createCriteria().andTypeEqualTo(itemNumber);
		pcmsItemRelationMapper.updateByExampleSelective(record, example);
	}
	
	

	@Override
	public PcmsProjectVo getProjectDeatil(String requestId) {
		
		PcmsProject detail=pcmsProjectMapper.selectByPrimaryKey(requestId);
		String json=detail.getRequestJson();
		
		
//		PcmsProjectVo result=pcmsProjectMapper.getProjectDeatil(requestId);
		
		 //使用JSONObject将原始JSON转换为Java对象
		PcmsProjectVo jsonBeat=JSONObject.parseObject(json, PcmsProjectVo.class);
//        System.out.println(jsonBeat);
		
//		String type=result.getType();
//		//展台展柜
//		if(type.equals("1")){
//			PcmsShowcaseExample scParam=new PcmsShowcaseExample();
//			scParam.createCriteria().andResuestIdEqualTo(requestId);
//			List<PcmsShowcase> showcaseList = pcmsShopcaseMapper.selectByExample(scParam);
//			result.setShowcaseList(showcaseList);
//		}
//		//其他终端
//		if(type.equals("2")){
//			PcmsOthertmExample otParam=new PcmsOthertmExample();
//			otParam.createCriteria().andRequestIdEqualTo(requestId);
//			List<PcmsOthertm> otList = pcmsOthertmMapper.selectByExample(otParam);
//			result.setOtherTerminalList(otList);
//		}
//		//物料
//		if(type.equals("3")){
//			PcmsMaterialExample maParam=new PcmsMaterialExample();
//			maParam.createCriteria().andResuestIdEqualTo(requestId);
//			List<PcmsMaterial> materialList = pcmsMaterialMapper.selectByExample(maParam);
//			result.setMaterialList(materialList);
//		}
		
		return jsonBeat;
	}




	@Override
	public List<RequestUserVo> getRequestNameList(String orgCode,String searchKey) {
		if(searchKey!=null&&!searchKey.equals("")){
			searchKey="%"+searchKey+"%";
		}else{
			searchKey=null;
		}
		return tpmEmployeeMapper.getRequestNameList(orgCode,searchKey);
	}




	@Override
	public ResultVo changeRequestName(String requestId,String personCode) {
		TpmEmployeeModel emp=tpmEmployeeMapper.getTpmEmployeebyPersonCode(personCode);
		
		PcmsProject record=new PcmsProject();
		record.setRequestUserName(emp.getPerson_name());
		PcmsProjectExample example=new PcmsProjectExample();
		example.createCriteria().andRequestIdEqualTo(requestId);
		int i=pcmsProjectMapper.updateByExampleSelective(record, example);
		if (i==1){
			return ResultVo.get(ResultVo.SUCCESS);
		}
		
		 return ResultVo.get(ResultVo.FAIL);
	}




	@Override
	public void importProjectMaterialDetail(ProjectDetialModelVo vo) {
		PcmsProjectVo projectvo=new PcmsProjectVo();
		String requestId=vo.getRequestId();
		projectvo.setRequestId(requestId);
		projectvo.setRequestTitle(vo.getRequestTitle());
		projectvo.setRequestCompanyCode(vo.getRequestCompanyCode());
		projectvo.setType("3");
		projectvo.setRequestCreateTime(vo.getRequestCreateTime());
		projectvo.setRequestEndTime(vo.getRequestEndTime());
		projectvo.setREQUEST_USER(vo.getRequestUser());
		
		
		List<PcmsMaterial> materialList=new ArrayList<>();
		
		List<TpmActivityOriginalModelVo> ActivityOriginalList=vo.getActivityOriginalList();
		for (TpmActivityOriginalModelVo allList : ActivityOriginalList) {
			List<OtherFeeOriginalModelVo> meList=allList.getOtherFeeOriginalModelList();
			for (OtherFeeOriginalModelVo material : meList) {
				PcmsMaterial info=new PcmsMaterial();
				info.setResuestId(requestId);
				info.setVendorId(material.getActivityVendor());
				info.setMrname(material.getMaterialCategory());
				info.setCost(allList.getFeeDetailType());
				info.setSpecifications(material.getSpecifications());
				info.setUnit(material.getUnit());
				info.setNumber(material.getAmount().intValue());
				info.setUnitPrice(material.getUnitPrice());
				
				materialList.add(info);
			}
		}
		projectvo.setMaterialList(materialList);
		
		//导入物料单
		this.importProjectDetail(projectvo);
	}




	@Override
	public String importProjectDetail2(PcmsProjectVo2 projectvo) {
		//获取申请单ID
		String requestId=projectvo.getRequestId();
		
		//查询该立项单是否存在
		PcmsProject query=pcmsProjectMapper.selectByPrimaryKey(requestId);
		if(query!=null){
			return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE, "立项单已存在"));
		}
		
		
		Date createTime=new Date();
		String requestDept=projectvo.getRequestDept();
		Integer itemType=0;
		
		//获取门店信息列表
		List<PcmsShop> shopList=projectvo.getShopList();
		
		//获取详情列表
		List<ProjectDetailVo> detailList=projectvo.getDETAIL_LIST();
		
		//获取外层数据
		PcmsProject project=new PcmsProject();
		BeanUtils.copyProperties(projectvo, project);
		
		//写入外层数据
		project.setRequestJson(JSON.toJSONString(projectvo));
		project.setCreatTime(createTime);
		project.setStatus(1);//未拆单
		pcmsProjectMapper.insertSelective(project);
		
		
		//写入门店信息
		for (PcmsShop shop : shopList) {
			PcmsShop target=new PcmsShop();
			BeanUtils.copyProperties(shop, target);
			shop.setRequestId(requestId);
			pcmsShopMapper.insertSelective(shop);
			
		}
		
		//写入详情信息
		for (ProjectDetailVo detail : detailList) {
			String vendorId=detail.getDetailVendor();
			String detailId=detail.getDetailId();
			PcmsProjectDeatil target=new PcmsProjectDeatil();
			BeanUtils.copyProperties(detail, target);
			target.setRequestId(requestId);
			pcmsProjectDeatilMapper.insertSelective(target);
		
			//展台展柜
			List<PcmsShowcase> scList=detail.getCHILDREN1_LIST();
			if(scList!=null&&scList.size()>0){
				for (PcmsShowcase sc : scList) {
					sc.setResuestId(requestId);
					sc.setVendorId(vendorId);
					pcmsShopcaseMapper.insertSelective(sc);
					itemType=1;
					
					//复制数据
	            	PcmsShowcaseSource soure=new PcmsShowcaseSource();
	            	BeanUtils.copyProperties(sc, soure);
	            	pcmsShowcaseSourceMapper.insertSelective(soure);
				}
			}
			
			//门头（数据未使用）
			List<PcmsOutdoors> odList=detail.getCHILDREN2_LIST();
			if(odList!=null&&odList.size()>0){
				for (PcmsOutdoors od : odList) {
					od.setRequestId(requestId);
					pcmsOutdoorsMapper.insertSelective(od);
					itemType=4;
				}
			}
			
			//其他终端
			List<PcmsOthertm> otList=detail.getCHILDREN3_LIST();
			if(otList!=null&&otList.size()>0){
				for (PcmsOthertm ot : otList) {
					ot.setRequestId(requestId);
					ot.setVendorId(vendorId);
					pcmsOthertmMapper.insertSelective(ot);
					itemType=2;
					
					//复制数据
	            	PcmsOthertmSource soure=new PcmsOthertmSource();
	            	BeanUtils.copyProperties(ot, soure);
	            	pcmsOthertmSourceMapper.insertSelective(soure);
				}
			}
			
			
			if(itemType!=4){
				PcmsItem item=new PcmsItem();
				item.setDetailId(detailId);
				item.setRequestCompanyCode(projectvo.getRequestCompanyCode());
				item.setDeptCode(requestDept.substring(requestDept.indexOf("(")+1, requestDept.indexOf(")")));
				item.setItemNumber(PcmsProjectUtil.creatItemNumber());
				item.setVendorId(vendorId);
				item.setRequestId(requestId);
				item.setItemPrice(Double.valueOf(detail.getDetailAmount()));
				item.setTitle(projectvo.getRequestTitle());
				item.setItType(itemType);
				item.setStatus(0);
				item.setCreateTime(createTime);
				
				pcmsItemMapper.insertSelective(item);
			}
			
		}
		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));
	}
	
	
	
	
}
