package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsSupplierInvoiceModel;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.PcmsSupplierQuery;

/**
 * Created by pc on 2018/11/14
 */
public interface PcmsSupplierService extends IService<PcmsSupplierModel> {
    /**新增供应商*/
    public void insertPcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception;
    /**查询供应商*/
    public PcmsSupplierModel getPcmsSupplier(PcmsSupplierVo pcmsSupplierVo);

    /**绑定供应商*/
    public ResultVo getPcmsSupplierFor(PcmsSupplierModel pcmsSupplierModel, String openid)throws Exception;
    /**同步推送更新供应商*/
    public void updatePcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception;

    /**更新供应商*/
    public void updatePcmsSupplierModel(PcmsSupplierModel pcmsSupplierModel,LoginUserInfo userInfo) throws Exception;

    /**分页查询*/
    ResultVo findPcmsSupplierListByPage(LoginUserInfo userInfo, PcmsSupplierQuery query)throws Exception;

    /**导出供应商*/
    String getPcmsSupplierUrl(String pdfOrxls, LoginUserInfo userInfo) throws Exception;
    /**添加发票信息*/
    public ResultVo insertSupplierInvoice(PcmsSupplierInvoiceModel pcmsSupplierInvoiceModel) throws Exception;

    /**同步或者更新供应商*/
    public void synch(String synDate)throws Exception;

}
