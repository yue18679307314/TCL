package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsShop;
import com.kuyu.model.pcms.PcmsShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsShopMapper {
    int countByExample(PcmsShopExample example);

    int deleteByExample(PcmsShopExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(PcmsShop record);

    int insertSelective(PcmsShop record);

    List<PcmsShop> selectByExample(PcmsShopExample example);

    PcmsShop selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") PcmsShop record, @Param("example") PcmsShopExample example);

    int updateByExample(@Param("record") PcmsShop record, @Param("example") PcmsShopExample example);

    int updateByPrimaryKeySelective(PcmsShop record);

    int updateByPrimaryKey(PcmsShop record);
}