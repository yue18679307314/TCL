package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.vo.query.SupplierMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PcmsSupplierMaterialMapper extends BaseMapper<PcmsSupplierMaterialModel> {

    /**分页查询*/
    List<SupplierMaterialQuery> findSupplierMaterialByPage(@Param("params") SupplierMaterialQuery query, Page<SupplierMaterialQuery> page);
}
