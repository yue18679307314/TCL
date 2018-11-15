package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsSupplierUser;
import com.kuyu.model.pcms.PcmsSupplierUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsSupplierUserMapper {
    int countByExample(PcmsSupplierUserExample example);

    int deleteByExample(PcmsSupplierUserExample example);

    int deleteByPrimaryKey(Integer suid);

    int insert(PcmsSupplierUser record);

    int insertSelective(PcmsSupplierUser record);

    List<PcmsSupplierUser> selectByExample(PcmsSupplierUserExample example);

    PcmsSupplierUser selectByPrimaryKey(Integer suid);

    int updateByExampleSelective(@Param("record") PcmsSupplierUser record, @Param("example") PcmsSupplierUserExample example);

    int updateByExample(@Param("record") PcmsSupplierUser record, @Param("example") PcmsSupplierUserExample example);

    int updateByPrimaryKeySelective(PcmsSupplierUser record);

    int updateByPrimaryKey(PcmsSupplierUser record);
}