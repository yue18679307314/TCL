package com.kuyu.controller.pcms;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.model.pcms.PcmsBill;
import com.kuyu.service.PcmsItemService;

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
	@RequestMapping(value = "/testItem", produces = "application/json;charset=utf-8")
	public @ResponseBody String testItem(HttpServletRequest request,Integer id) {
		PcmsBill aaa = pcmsItemService.selectBillByid(id);
		System.out.println(aaa.toString());
		return aaa.getSpid();
//		System.out.println(1);
//		System.out.println(2);
//		System.out.println(3);
//		return "1111111";
	}
	
	
}
