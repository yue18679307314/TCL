package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.vo.PcmsSupplierQuert;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ResultVo;

import java.util.List;

/**
 * Created by pc on 2018/11/14
 */
public interface PcmsSupplierService extends IService<PcmsSupplierModel> {
    /**新增供应商*/
    public void insertPcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception;
    /**查询供应商*/
    public PcmsSupplierModel getPcmsSupplier(PcmsSupplierVo pcmsSupplierVo);
    /**同步推送更新供应商*/
    public void updatePcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception;

    /**更新供应商*/
    public void updatePcmsSupplierModel(PcmsSupplierModel pcmsSupplierModel) throws Exception;

    /**分页查询*/
    ResultVo findPcmsSupplierListByPage(LoginUserInfo userInfo, PcmsSupplierQuert query)throws Exception;


    String getPcmsSupplierUrl(List<String> list,String pdfOrxls) throws Exception;
}
