package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.pcms.PcmsSupplierLogModel;
import com.kuyu.vo.query.PcmsSupplierLogQuery;
import com.kuyu.vo.ResultVo;

/**
 * Created by pc on 2018/11/21
 */
public interface PcmsSupplierLogService extends IService<PcmsSupplierLogModel> {

    ResultVo findSupplierLogList(PcmsSupplierLogQuery query)throws Exception;
}
