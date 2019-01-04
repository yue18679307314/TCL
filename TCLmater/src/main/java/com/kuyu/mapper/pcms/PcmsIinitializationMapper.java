package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsIinitializationModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/12/28
 */
public interface PcmsIinitializationMapper extends BaseMapper<PcmsIinitializationModel> {

    PcmsIinitializationModel selectByCompany(@Param("company") String company,@Param("vendor_id") String vendor_id,@Param("month") String month);

    PcmsIinitializationModel selectByReconciliationId(@Param("reconciliationId") Integer reconciliationId);

    List<PcmsIinitializationModel> selectByMonth(@Param("month") String month);
}
