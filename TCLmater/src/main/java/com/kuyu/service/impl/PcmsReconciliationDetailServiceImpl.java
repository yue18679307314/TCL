package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsReconciliationDetailMapper;
import com.kuyu.model.pcms.PcmsReconciliationDetailModel;
import com.kuyu.service.PcmsReconciliationDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pc on 2018/12/26
 */
@Service
@Transactional
public class PcmsReconciliationDetailServiceImpl extends ServiceImpl<PcmsReconciliationDetailMapper, PcmsReconciliationDetailModel>
        implements PcmsReconciliationDetailService {
}
