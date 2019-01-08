package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsSupplierInvoiceModel;
import com.kuyu.vo.pcms.SupplierInvoiceImageVo;
import com.kuyu.vo.pcms.SupplierInvoiceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/28
 */
public interface PcmsSupplierInvoiceMapper extends BaseMapper<PcmsSupplierInvoiceModel> {

     int insertPcmsSupplierInvoice(PcmsSupplierInvoiceModel pcmsSupplierInvoiceModel)throws Exception;

     SupplierInvoiceVo selectByItid(@Param("itid")Integer itid);

     List<SupplierInvoiceImageVo> selectByInvoiceId(@Param("invoice_id")Integer invoice_id);


}
