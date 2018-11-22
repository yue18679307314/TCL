package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsItem;
import com.kuyu.model.pcms.PcmsItemExample;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PcmsItemMapper {
    int countByExample(PcmsItemExample example);

    int deleteByExample(PcmsItemExample example);

    int deleteByPrimaryKey(Integer itid);

    int insert(PcmsItem record);

    int insertSelective(PcmsItem record);

    List<PcmsItem> selectByExample(PcmsItemExample example);

    PcmsItem selectByPrimaryKey(Integer itid);

    int updateByExampleSelective(@Param("record") PcmsItem record, @Param("example") PcmsItemExample example);

    int updateByExample(@Param("record") PcmsItem record, @Param("example") PcmsItemExample example);

    int updateByPrimaryKeySelective(PcmsItem record);

    int updateByPrimaryKey(PcmsItem record);

	List<ItemResult> getItemListByParam(Map<String, Object> param);

	Integer getItemListCountByParam(Map<String, Object> param);

	ItemDetail getItemItemDetailById(Integer itid);
}