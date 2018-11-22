package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.PcmsSupplierLogModel;
import com.kuyu.vo.query.PcmsSupplierLogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/21
 */
public interface PcmsSupplierLogMapper extends BaseMapper<PcmsSupplierLogModel> {

    /**分页查询*/
    List<PcmsSupplierLogModel> findSupplierLogList(@Param("params") PcmsSupplierLogQuery query, Page<PcmsSupplierLogModel> page);

}
