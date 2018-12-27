package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsReconciliationMapper;
import com.kuyu.mapper.pcms.PcmsSettlementMapper;
import com.kuyu.model.pcms.PcmsReconciliationDetailModel;
import com.kuyu.model.pcms.PcmsReconciliationModel;
import com.kuyu.service.PcmsReconciliationDetailService;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.vo.ReconciliationVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsSettlementVo;
import com.kuyu.vo.query.ReconciliationQuery;
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
    @Resource
    private PcmsReconciliationDetailService pcmsReconciliationDetailService;
    @Override
    public void selectByTime() {
        //查询上个月的付款记录
        List<PcmsSettlementVo> list = pcmsSettlementMapper.selectByTime();
        for(PcmsSettlementVo pcmsSettlementVo : list){
            List<PcmsSettlementVo> settlementVoList = pcmsSettlementMapper.selectPaymentByVendorId(pcmsSettlementVo.getVendor_id());
            PcmsReconciliationModel pcmsReconciliationModel = new PcmsReconciliationModel();
            pcmsReconciliationModel.setVendor_id(pcmsSettlementVo.getVendor_id());
            pcmsReconciliationModel.setVendor_name(pcmsSettlementVo.getVendor_name());
            pcmsReconciliationModel.setState(0);
            pcmsReconciliationModel.setCreate_time(DateUtils.getPreviousMonthFirstDay());
            pcmsReconciliationModel.setMonth(getLastMonth());
            //生成对账记录
            pcmsReconciliationMapper.insertReconciliation(pcmsReconciliationModel);
            for(PcmsSettlementVo pcmsSettlementVo1 : settlementVoList){
                PcmsReconciliationDetailModel pcmsReconciliationDetail = new PcmsReconciliationDetailModel();
                pcmsReconciliationDetail.setPcms_reconciliation_id(pcmsReconciliationModel.getId());
                pcmsReconciliationDetail.setPmid(pcmsSettlementVo1.getPmid());
                //生成付款与对账的关联记录
                pcmsReconciliationDetailService.insert(pcmsReconciliationDetail);
            }
        }
    }

    @Override
    public ResultVo findReconciliationList(/*LoginUserInfo userInfo,*/ ReconciliationQuery query) throws Exception {
//        query.setCompany(userInfo.getEmployeeModel().getCompany());
        query = (ReconciliationQuery) CheckParamUtils.trimWithObjectField(query);
        Page<ReconciliationVo> page = new Page<>(query.getCurrent(),query.getSize());
        List<ReconciliationVo> pcmsSupplierList = baseMapper.findListPage(query,page);
        page.setRecords(pcmsSupplierList);
        return ResultVo.getDataWithSuccess(page);
    }

    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

}
