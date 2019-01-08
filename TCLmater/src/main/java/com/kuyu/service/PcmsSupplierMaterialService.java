package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.SupplierMaterialResultVo;
import com.kuyu.vo.pcms.SupplierMaterialVo;
import com.kuyu.vo.query.SupplierMaterialQuery;

public interface PcmsSupplierMaterialService extends IService<PcmsSupplierMaterialModel> {

    /**新增*/
    public void insertPcmsSupplierMaterial(PcmsSupplierMaterialModel pcmsSupplierMaterialModel, LoginUserInfo userInfo) throws Exception;
    /**删除*/
    public void deletePcmsSupplierMaterial(Integer id) throws Exception;
    /**分页查询*/
    ResultVo findSupplierMaterialByPage(LoginUserInfo userInfo, SupplierMaterialQuery query)throws Exception;

    /**分页查询*/
    ResultVo findBySupplierMaterialByPage(LoginUserInfo userInfo, SupplierMaterialQuery query)throws Exception;

    /**查询*/
    ResultVo querySupplierMaterialList(SupplierMaterialVo query)throws Exception;

    /**查询*/
    ResultVo queryBySupplierMaterialList(SupplierMaterialResultVo supplierMaterialResultVo,LoginUserInfo userInfo)throws Exception;

    /**导出物料*/
    String getSupplierMaterialUrl(String vendor_id, LoginUserInfo userInfo) throws Exception;

}
