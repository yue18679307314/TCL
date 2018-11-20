package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsSupplierCompanyMapper;
import com.kuyu.model.pcms.PcmsSupplierCompanyModel;
import com.kuyu.service.PcmsSupplierCompanyService;
import com.kuyu.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pc on 2018/11/20
 */
@Service
@Transactional
public class PcmsSupplierCompanyServiceImpl extends ServiceImpl<PcmsSupplierCompanyMapper, PcmsSupplierCompanyModel>
        implements PcmsSupplierCompanyService {
    @Override
    public void insertPcmsSupplierCompany(PcmsSupplierCompanyModel pcmsSupplierCompanyModel) throws Exception {
        if (StringUtil.isNotNull(pcmsSupplierCompanyModel.getCompany())
                && StringUtil.isNotNull(pcmsSupplierCompanyModel.getVendor_id())){
            baseMapper.insert(pcmsSupplierCompanyModel);
        }
    }
}
