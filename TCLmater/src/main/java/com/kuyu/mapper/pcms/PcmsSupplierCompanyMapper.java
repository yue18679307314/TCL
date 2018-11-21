package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsSupplierCompanyModel;

/**
 * Created by pc on 2018/11/20
 */
public interface PcmsSupplierCompanyMapper extends BaseMapper<PcmsSupplierCompanyModel> {

    public PcmsSupplierCompanyModel selectByVendorIdAndCompany(PcmsSupplierCompanyModel pcmsSupplierCompanyModel);
}
