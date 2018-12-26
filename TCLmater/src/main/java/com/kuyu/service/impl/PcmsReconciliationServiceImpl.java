package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsReconciliationMapper;
import com.kuyu.mapper.pcms.PcmsSettlementMapper;
import com.kuyu.model.pcms.PcmsReconciliationModel;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.util.DateUtils;
import com.kuyu.vo.pcms.PcmsSettlementVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pc on 2018/12/26
 */
@Service
@Transactional
public class PcmsReconciliationServiceImpl extends ServiceImpl<PcmsReconciliationMapper, PcmsReconciliationModel>
        implements PcmsReconciliationService {
    @Resource
    private PcmsSettlementMapper pcmsSettlementMapper;
    @Resource
    private PcmsReconciliationMapper pcmsReconciliationMapper;
    @Override
    public void selectByTime() {
        List<PcmsSettlementVo> list = pcmsSettlementMapper.selectByTime();
        list.forEach(pcmsSettlementVo -> {
            List<PcmsSettlementVo> settlementVoList = pcmsSettlementMapper.selectPaymentByVendorId(pcmsSettlementVo.getVendor_id());
            settlementVoList.forEach(pcmsSettlementVo1 -> {
                PcmsReconciliationModel pcmsReconciliationModel = new PcmsReconciliationModel();
                pcmsReconciliationModel.setVendor_id(pcmsSettlementVo.getVendor_id());
                pcmsReconciliationModel.setVendor_name(pcmsSettlementVo.getVendor_name());
                pcmsReconciliationModel.setState(0);
                pcmsReconciliationModel.setCreate_time(DateUtils.getPreviousMonthFirstDay());
                pcmsReconciliationModel.setMonth(getLastMonth());
                pcmsReconciliationMapper.insertReconciliation(pcmsReconciliationModel);
            });
        });
    }
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }


}
