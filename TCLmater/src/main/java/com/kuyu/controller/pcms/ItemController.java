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
	public @ResponseBody ResultVo itemList(HttpServletRequest request,String searchKey,
			Integer current,Integer size,String approvalStatrTime,String approvalEndTime,
			Integer status) throws Exception {
		
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
	 * @param request
	 * @return
	 */
	@ApiOperation("获取待立项单详情")
	@RequestMapping(value = "/detail", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo detail(HttpServletRequest request,Integer itid) {
		
		ItemDetail result =pcmsItemService.getItemDetailById(itid);
		
		return ResultVo.getData(ResultVo.SUCCESS, result); 
	}
	
	
	/**
	 * 作废立项单
	 * @param request
	 * @return
	 */
	@ApiOperation("作废立项单")
	@RequestMapping(value = "/abolishItem", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo abolishItem(HttpServletRequest request,
			Integer itid) {
		return pcmsItemService.abolishItem(itid);
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
