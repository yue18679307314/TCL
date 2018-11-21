package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.pcms.PcmsSupplierCompanyModel;
import com.kuyu.vo.PcmsSupplierVo;

/**
 * Created by pc on 2018/11/20
 */
public interface PcmsSupplierCompanyService extends IService<PcmsSupplierCompanyModel> {
    /**新增*/
    public void insertPcmsSupplierCompany(PcmsSupplierCompanyModel pcmsSupplierCompanyModel) throws Exception;
    /**根据供应商编码和公司编码查询*/
    public PcmsSupplierCompanyModel selectByVendorIdAndCompany(PcmsSupplierCompanyModel pcmsSupplierCompanyModel);

}
