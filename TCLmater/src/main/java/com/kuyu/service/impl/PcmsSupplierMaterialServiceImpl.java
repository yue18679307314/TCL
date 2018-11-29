package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.PcmsSupplierMaterialMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.service.PcmsSupplierMaterialService;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.SupplierMaterialVo;
import com.kuyu.vo.query.SupplierMaterialQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PcmsSupplierMaterialServiceImpl extends ServiceImpl<PcmsSupplierMaterialMapper, PcmsSupplierMaterialModel>
        implements PcmsSupplierMaterialService {
    @Autowired
    private PcmsSupplierService pcmsSupplierService;

    @Override
    public void insertPcmsSupplierMaterial(PcmsSupplierMaterialModel pcmsSupplierMaterialModel, LoginUserInfo userInfo) throws Exception {
        PcmsSupplierVo supplier = new PcmsSupplierVo();
        supplier.setVendor_id(pcmsSupplierMaterialModel.getVendor_id());
        PcmsSupplierModel pcmsSupplierModel = pcmsSupplierService.getPcmsSupplier(supplier);
        if(null == pcmsSupplierModel){
            throw new ParamException("供应商不存在");
        }
        PcmsSupplierMaterialModel psm = baseMapper.findSupplierMaterialByVendorAndCompany(pcmsSupplierMaterialModel.getVendor_id(),userInfo.getEmployeeModel().getCompany());
        if(null == psm){
            pcmsSupplierMaterialModel.setCreate_time(new Date());
            pcmsSupplierMaterialModel.setVersion(10000);
            pcmsSupplierMaterialModel.setCompany(userInfo.getEmployeeModel().getCompany());
            baseMapper.insert(pcmsSupplierMaterialModel);
        }else{
            pcmsSupplierMaterialModel.setCreate_time(new Date());
            pcmsSupplierMaterialModel.setVersion(psm.getVersion()+1);
            pcmsSupplierMaterialModel.setCompany(userInfo.getEmployeeModel().getCompany());
            baseMapper.insert(pcmsSupplierMaterialModel);
        }

    }

    @Override
    public void deletePcmsSupplierMaterial(Integer id) throws Exception {
        PcmsSupplierMaterialModel pcmsSupplierMaterialModel = baseMapper.selectById(id);
        if(null == pcmsSupplierMaterialModel){
            throw new ParamException("该物料不存在");
        }
        baseMapper.deleteById(id);
    }

    @Override
    public ResultVo findSupplierMaterialByPage(LoginUserInfo userInfo, SupplierMaterialQuery query) throws Exception {
        query.setCompany(userInfo.getEmployeeModel().getCompany());
        query = (SupplierMaterialQuery) CheckParamUtils.trimWithObjectField(query);
        Page<SupplierMaterialQuery> page = new Page<>(query.getCurrent(),query.getSize());
        List<SupplierMaterialQuery> list = baseMapper.findSupplierMaterialByPage(query,page);
        page.setRecords(list);
        return ResultVo.getDataWithSuccess(page);
    }

    @Override
    public ResultVo querySupplierMaterialList(SupplierMaterialVo query) throws Exception {
        List<PcmsSupplierMaterialModel> list = baseMapper.querySupplierMaterialList(query);
        return ResultVo.getDataWithSuccess(list);
    }
}
