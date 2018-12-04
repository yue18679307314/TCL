package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.vo.pcms.SupplierMaterialResultVo;
import com.kuyu.vo.pcms.SupplierMaterialVo;
import com.kuyu.vo.query.SupplierMaterialQuery;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PcmsSupplierMaterialMapper extends BaseMapper<PcmsSupplierMaterialModel> {

    /**分页查询*/
    List<SupplierMaterialQuery> findSupplierMaterialByPage(@Param("params") SupplierMaterialQuery query, Page<SupplierMaterialQuery> page);
    /**根据供应商和公司编码查询最新版本号*/
    List<PcmsSupplierMaterialModel> findSupplierMaterialByVendorAndCompany(@Param("vendor_id")String vendor_id,@Param("company")String company);

    /***/
    List<PcmsSupplierMaterialModel> querySupplierMaterialList(@Param("params") SupplierMaterialVo query);

    List<PcmsSupplierMaterialModel> queryBySupplierMaterialList(@Param("params") SupplierMaterialResultVo supplierMaterialResultVo);
    /**根据供应商ID导出物料清单*/
    List<PcmsSupplierMaterialModel> getSupplierMaterialUrl(@Param("vendor_id")String vendor_id);

    /**根据状态查询*/
    List<PcmsSupplierMaterialModel> getSupplierMaterialByState(@Param("vendor_id")String vendor_id,@Param("company")String company,@Param("state") Integer state);


}
