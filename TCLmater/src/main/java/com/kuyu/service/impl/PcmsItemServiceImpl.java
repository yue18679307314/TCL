package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.mapper.pcms.*;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
//@Transactional
public class PcmsItemServiceImpl implements PcmsItemService{

	@Resource
	private PcmsProjectMapper pcmsProjectMapper;
	
	@Resource
	private PcmsItemMapper pcmsItemMapper;
	
	@Resource
	private PcmsMaterialMapper pcmsMaterialMapper;
	
	@Resource
	private PcmsOthertmMapper pcmsOthertmMapper;
	
	@Resource
	private PcmsShowcaseMapper pcmsShowcaseMapper;
	
	@Resource
	private PcmsShowcaseSourceMapper pcmsShowcaseSourceMapper;
	
	@Resource
	private PcmsMaterialSourceMapper pcmsMaterialSourceMapper;
	
	@Resource
	private PcmsOthertmSourceMapper pcmsOthertmSourceMapper;
	
	@Resource
	private PcmsItemLogMapper pcmsItemLogMapper;

	@Resource
	private PcmsTovoidItemMapper pcmsTovoidItemMapper;

	@Resource
	private PcmsRejectLogMapper pcmsRejectLogMapper;

	@Resource
	private PcmsPendingMaterialMapper pcmsPendingMaterialMapper;

	@Resource
	private PcmsMaterialImgMapper pcmsMaterialImgMapper;
	
	
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
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("itid",itid);
//		List<PcmsTovoidItemModel> pcmsTovoidItem = pcmsTovoidItemMapper.selectByMap(map);
		PcmsItemLogExample example=new PcmsItemLogExample();
		example.createCriteria().andItidEqualTo(itid);
		example.setOrderByClause("create_time DESC");
		List<PcmsItemLog> logList = pcmsItemLogMapper.selectByExample(example);
//		if(pcmsTovoidItem != null && pcmsTovoidItem.size()>0){
//			result.setContext(pcmsTovoidItem.get(0).getContext());
//		}
		result.setItemLog(logList);
//		if(logList != null && logList.size()>0){
//			result.setContext(logList.get(0).getNote());
//		}

		/**上传的图片信息*/
		List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
		List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
		if(list.size()>0){
			for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list){
				List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
				pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
				pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
			}
			result.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
		}
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
//	public ResultVo changeItemStatus(Integer itid,Integer status,String reason) {
	public ResultVo changeItemStatus(Integer itid, Integer status, LoginUserInfo userInfo,String context) {
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
	    	log.setNote("驳回原因:"+context);
	    	PcmsRejectLogModel pcmsRejectLogModel = new PcmsRejectLogModel();
	    	pcmsRejectLogModel.setContext(context);
	    	pcmsRejectLogModel.setCreate_time(new Date());
	    	pcmsRejectLogModel.setItid(itid);
	    	pcmsRejectLogModel.setOperator(userInfo.getEmployeeModel().getPerson_name());
			pcmsRejectLogMapper.insert(pcmsRejectLogModel);
	    }
	    if(status==3){
	    	log.setNote("物料已验收，费用待结算");
	    }
	    if(status==-1){
	    	log.setNote("作废原因:"+context);
			PcmsTovoidItemModel pcmsTovoidItemModel = new PcmsTovoidItemModel();
			pcmsTovoidItemModel.setContext(context);
			pcmsTovoidItemModel.setCreate_time(new Date());
			pcmsTovoidItemModel.setItid(itid);
			pcmsTovoidItemModel.setOperator(userInfo.getEmployeeModel().getPerson_name());
			pcmsTovoidItemMapper.insert(pcmsTovoidItemModel);
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
