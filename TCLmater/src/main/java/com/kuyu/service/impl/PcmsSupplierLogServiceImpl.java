package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsSupplierLogMapper;
import com.kuyu.model.pcms.PcmsSupplierLogModel;
import com.kuyu.service.PcmsSupplierLogService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.PcmsSupplierLogQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pc on 2018/11/21
 */
@Service
@Transactional
public class PcmsSupplierLogServiceImpl extends ServiceImpl<PcmsSupplierLogMapper, PcmsSupplierLogModel>
        implements PcmsSupplierLogService {
    @Override
    public ResultVo findSupplierLogList(PcmsSupplierLogQuery query) throws Exception {
        query = (PcmsSupplierLogQuery) CheckParamUtils.trimWithObjectField(query);
        Page<PcmsSupplierLogModel> page = new Page<>(query.getCurrent(),query.getSize());
        List<PcmsSupplierLogModel> pcmsSupplierLogList = baseMapper.findSupplierLogList(query,page);
        page.setRecords(pcmsSupplierLogList);
        return ResultVo.getDataWithSuccess(page);
    }
}
