package com.kuyu.controller.pcms;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemListRequestParam;
import com.kuyu.vo.pcms.ItemResult;
import io.swagger.annotations.ApiOperation;

@AOP_Controller_LOG
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private PcmsItemService pcmsItemService;
	
	
	/**
	 * 获取立项单列表
	 * @param request
	 * @return
	 */
	@ApiOperation("获取立项单列表")
	@RequestMapping(value = "/list", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo itemList(HttpServletRequest request,String searchKey) {
		
		List<ItemResult> result =pcmsItemService.getItemListByParam(searchKey);
		
		return ResultVo.getData(ResultVo.SUCCESS, result); 
	}
	
	/**
	 * 获取立项单详情
	 * @param request
	 * @return
	 */
	@ApiOperation("获取立项单详情")
	@RequestMapping(value = "/detail", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo detail(HttpServletRequest request,Integer itid) {
		
		ItemDetail result =pcmsItemService.getItemItemDetailById(itid);
		
		return ResultVo.getData(ResultVo.SUCCESS, result); 
	}
}
