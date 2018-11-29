package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.vo.pcms.SupplierMaterialVo;
import com.kuyu.vo.query.SupplierMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PcmsSupplierMaterialMapper extends BaseMapper<PcmsSupplierMaterialModel> {

    /**分页查询*/
    List<SupplierMaterialQuery> findSupplierMaterialByPage(@Param("params") SupplierMaterialQuery query, Page<SupplierMaterialQuery> page);
    /**根据供应商和公司编码查询最新版本号*/
    PcmsSupplierMaterialModel findSupplierMaterialByVendorAndCompany(@Param("vendor_id")String vendor_id,@Param("company")String company);

    /***/
    List<PcmsSupplierMaterialModel> querySupplierMaterialList(@Param("params") SupplierMaterialVo query);

}
