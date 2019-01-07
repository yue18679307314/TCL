package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsSupplierUser;
import com.kuyu.model.pcms.PcmsSupplierUserExample;
import java.util.List;

import com.kuyu.model.pcms.PcmsSupplierUserModel;
import com.kuyu.vo.pcms.PcmsSupplierUserVo;
import org.apache.ibatis.annotations.Param;

public interface PcmsSupplierUserMapper extends BaseMapper<PcmsSupplierUserModel> {
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

    public List<PcmsSupplierUserVo> findBySupplier(String vendor_id);

    PcmsSupplierUserModel findByOpenid(@Param("openid")String openid);
}