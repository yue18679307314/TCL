package com.kuyu.controller.pcms;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.model.pcms.PcmsOthertm;
import com.kuyu.model.pcms.PcmsShowcase;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.PcmsProjectVo;

import io.swagger.annotations.ApiOperation;

@AOP_Controller_LOG
@RequestMapping("/item")
public class ItemController extends BaseController{

	@Autowired
	private PcmsItemService pcmsItemService;
	
	
	/**
	 * 获取立项单列表
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation("获取立项单列表")
	@RequestMapping(value = "/list", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo itemList(HttpServletRequest request,
			@RequestParam(value = "searchKey",required=false)String searchKey,
			@RequestParam(value = "current",required=false)Integer current,
			@RequestParam(value = "size",required=false)Integer size,
			@RequestParam(value = "approvalStatrTime",required=false)String approvalStatrTime,
			@RequestParam(value = "approvalEndTime",required=false)String approvalEndTime,
			@RequestParam(value = "status",required=false)Integer status) throws Exception {
		
		if(current==null||current<=0){
			current=1;
		} 
		if(size==null||size<=0){
			 size=10;
		}
		
//		LoginUserInfo user=getUserInfo();

		
		
		//1 admin; 2  分公司财务负责人; 0  分公司管理员 ; 6 既是分公司管理员，也是分公司财务;
//		String userType=user.getUserType();
		String userType="1";
		//分公司代码和部门代码
//		TpmEmployeeModel emp=user.getEmployeeModel();
//		String companyCode=emp.getCompany();
//		String deptCode=emp.getOrg_code();
		String companyCode="";
		String deptCode="";
		
		Page<ItemResult> result =pcmsItemService.getItemListByParam(searchKey,current,size,companyCode,userType,deptCode,approvalStatrTime,approvalEndTime,status);
		
		return ResultVo.getData(ResultVo.SUCCESS, result); 
	}
	
	/**
	 * 获取立项单详情
	 * type 0 查询原始立项单详情  type 1 查询待验收立项单详情
	 * @param request
	 * @return
	 */
	@ApiOperation("获取立项单详情")
	@RequestMapping(value = "/detail", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo detail(HttpServletRequest request,
			@RequestParam(value = "itid",required=true)Integer itid,
			@RequestParam(value = "type",required=true)Integer type) {
		
		ItemDetail result =pcmsItemService.getItemDetailById(itid,type);
		
		return ResultVo.getData(ResultVo.SUCCESS, result); 
	}
	
	
	/**
	 * 更改立项单状态
	 * //0:未接单 1:已接单制作中 2待验收 3待结算 4已驳回 5结算中 6结算失败 7已结算 -1已作废
	 * @param request
	 * @return
	 */
	@ApiOperation("更改立项单状态")
	@RequestMapping(value = "/statusItem", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo statusItem(HttpServletRequest request,
			@RequestParam(value = "itid")Integer itid,
			@RequestParam(value = "status")Integer status,
			@RequestParam(value = "reason")String reason) {
											  
//		return pcmsItemService.changeItemStatus(itid,status,reason);
		return pcmsItemService.changeItemStatus(itid,status,getLoginUserInfo(),reason);
	}
	
	/**
	 * 批量结算
	 * @param request
	 * @return
	 */
	@ApiOperation("更改立项单状态")
	@RequestMapping(value = "/settlement", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo settlement(HttpServletRequest request,
			@RequestParam(value = "itid")String itids) {
			String itid[] =itids.split(",");
			for (String id : itid) {
//				pcmsItemService.changeItemStatus(Integer.valueOf(id),5,null);
				pcmsItemService.changeItemStatus(Integer.valueOf(id),5,getLoginUserInfo(),null);
			}
		
		return ResultVo.get(ResultVo.SUCCESS);
	}

//	/**
//	 * 导入物料单
//	 * @param request
//	 * @return
//	 * @throws IOException 
//	 * @throws IllegalStateException 
//	 */
//	@ApiOperation("导入物料单")
//	@RequestMapping(value = "/importExcel")
//	public @ResponseBody ResultVo importExcel(@RequestParam MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
//		
//		String abd="test";
//		
//		System.out.println("system:=============="+abd);
//		
//		
//		if(!file.isEmpty()){
//            String filePath = file.getOriginalFilename();
//            //windows
//            String savePath = request.getSession().getServletContext().getRealPath(filePath);
//
//            //linux
//            //String savePath = "/home/odcuser/webapps/file";
//
//            File targetFile = new File(savePath);
//
//            if(!targetFile.exists()){
//                targetFile.mkdirs();
//            }
//            file.transferTo(targetFile);
//            return ResultVo.get(ResultVo.SUCCESS);
//        }
//
//		return ResultVo.get(ResultVo.FILE_IS_NULL);
//	}
	
	
	
	
}
