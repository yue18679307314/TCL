package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsReconciliationMapper;
import com.kuyu.model.pcms.PcmsReconciliationModel;
import com.kuyu.service.PcmsReconciliationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pc on 2018/12/26
 */
@Service
@Transactional
public class PcmsReconciliationServiceImpl extends ServiceImpl<PcmsReconciliationMapper, PcmsReconciliationModel>
        implements PcmsReconciliationService {
}
