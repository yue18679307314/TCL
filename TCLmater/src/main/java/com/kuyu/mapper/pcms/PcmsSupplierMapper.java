package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsSupplier;
import com.kuyu.model.pcms.PcmsSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsSupplierMapper {
    int countByExample(PcmsSupplierExample example);

    int deleteByExample(PcmsSupplierExample example);

    int deleteByPrimaryKey(String vendorId);

    int insert(PcmsSupplier record);

    int insertSelective(PcmsSupplier record);

    List<PcmsSupplier> selectByExample(PcmsSupplierExample example);

    PcmsSupplier selectByPrimaryKey(String vendorId);

    int updateByExampleSelective(@Param("record") PcmsSupplier record, @Param("example") PcmsSupplierExample example);

    int updateByExample(@Param("record") PcmsSupplier record, @Param("example") PcmsSupplierExample example);

    int updateByPrimaryKeySelective(PcmsSupplier record);

    int updateByPrimaryKey(PcmsSupplier record);
}