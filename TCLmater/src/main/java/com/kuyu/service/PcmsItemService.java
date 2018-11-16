package com.kuyu.service;

import java.util.List;

import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemListRequestParam;
import com.kuyu.vo.pcms.ItemResult;

public interface PcmsItemService  {


	List<ItemResult> getItemListByParam(String searchKey);

	ItemDetail getItemItemDetailById(Integer itid);

	

}
