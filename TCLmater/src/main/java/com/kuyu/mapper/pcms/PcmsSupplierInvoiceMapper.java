package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsSupplierInvoiceModel;

/**
 * Created by pc on 2018/11/28
 */
public interface PcmsSupplierInvoiceMapper extends BaseMapper<PcmsSupplierInvoiceModel> {

     int insertPcmsSupplierInvoice(PcmsSupplierInvoiceModel pcmsSupplierInvoiceModel)throws Exception;


}
