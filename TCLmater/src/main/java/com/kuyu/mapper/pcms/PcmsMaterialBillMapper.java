package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsMaterialBill;
import com.kuyu.model.pcms.PcmsMaterialBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsMaterialBillMapper {
    int countByExample(PcmsMaterialBillExample example);

    int deleteByExample(PcmsMaterialBillExample example);

    int deleteByPrimaryKey(Integer mbid);

    int insert(PcmsMaterialBill record);

    int insertSelective(PcmsMaterialBill record);

    List<PcmsMaterialBill> selectByExample(PcmsMaterialBillExample example);

    PcmsMaterialBill selectByPrimaryKey(Integer mbid);

    int updateByExampleSelective(@Param("record") PcmsMaterialBill record, @Param("example") PcmsMaterialBillExample example);

    int updateByExample(@Param("record") PcmsMaterialBill record, @Param("example") PcmsMaterialBillExample example);

    int updateByPrimaryKeySelective(PcmsMaterialBill record);

    int updateByPrimaryKey(PcmsMaterialBill record);
}