package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.*;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsReconciliationDetailService;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.vo.ReconciliationVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.CurrentDetailModelVo;
import com.kuyu.vo.pcms.PcmsPaymentDetailVo;
import com.kuyu.vo.pcms.PcmsSettlementVo;
import com.kuyu.vo.query.ReconciliationQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Resource
    private PcmsIinitializationMapper pcmsIinitializationMapper;
    @Resource
    private PcmsPaymentDetailMapper pcmsPaymentDetailMapper;
    @Resource
    private PcmsCurrentDetailMapper pcmsCurrentDetailMapper;
    @Override
    public void selectByTime() {
        //查询上个月的付款记录
        List<PcmsSettlementVo> list = pcmsSettlementMapper.selectByTime();
        for(PcmsSettlementVo pcmsSettlementVo : list){
//            List<PcmsSettlementVo> settlementVoList = pcmsSettlementMapper.selectPaymentByVendorId(pcmsSettlementVo.getVendor_id());
            PcmsReconciliationModel pcmsReconciliationModel = new PcmsReconciliationModel();
            pcmsReconciliationModel.setVendor_id(pcmsSettlementVo.getVendor_id());
            pcmsReconciliationModel.setVendor_name(pcmsSettlementVo.getVendor_name());
            pcmsReconciliationModel.setState(0);
            pcmsReconciliationModel.setCreate_time(DateUtils.getPreviousMonthFirstDay());
            pcmsReconciliationModel.setMonth(getLastMonth());
            //生成对账记录
            pcmsReconciliationMapper.insertReconciliation(pcmsReconciliationModel);
            /*for(PcmsSettlementVo pcmsSettlementVo1 : settlementVoList){
                PcmsReconciliationDetailModel pcmsReconciliationDetail = new PcmsReconciliationDetailModel();
                pcmsReconciliationDetail.setPcms_reconciliation_id(pcmsReconciliationModel.getId());
                pcmsReconciliationDetail.setPmid(pcmsSettlementVo1.getPmid());
                //生成付款与对账的关联记录
                pcmsReconciliationDetailService.insert(pcmsReconciliationDetail);
            }*/
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
    @Override
    public ResultVo selectCurrentDetail(Integer id/*, LoginUserInfo userInfo*/) {
        List<CurrentDetailModelVo> list = new ArrayList<CurrentDetailModelVo>();
        list = pcmsReconciliationMapper.selectCurrent(id);
        if(list.size() >0){
            return ResultVo.getData(ResultVo.SUCCESS,list);
        }else{
            PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
            //TODO
            //判断是否每天同步了共享付款数据

            //查看上个月统计的期初期末余额
            PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByCompany(/*userInfo.getEmployeeModel().getCompany()*/"7601",pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());
            BigDecimal initialBalance = new BigDecimal(pcmsIinitializationModel.getInitial_balance());


            List<CurrentDetailModelVo> currentDetailModelVoList = pcmsReconciliationMapper.selectCurrentDetail(id);
            if(currentDetailModelVoList != null){
                for(CurrentDetailModelVo currentDetailModelVo : currentDetailModelVoList){
                    CurrentDetailModelVo currentDetailModelVo1 = new CurrentDetailModelVo();
                    BeanUtils.copyProperties(currentDetailModelVo,currentDetailModelVo1);
                    currentDetailModelVo1.setType(0);
                    if(currentDetailModelVo.getCreate_date() == null && currentDetailModelVo.getPr_time() == null){
                        currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getInitial_balance());
                        currentDetailModelVo1.setBalance(pcmsIinitializationModel.getInitial_balance());
                        currentDetailModelVo1.setIncrease_amount("0");
                        currentDetailModelVo1.setPay_amount("0");
                        currentDetailModelVo1.setCreate_date("--");
                    }
                    if(currentDetailModelVo.getCreate_date() == null && currentDetailModelVo.getPr_time() != null){
                        BigDecimal amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
                        currentDetailModelVo1.setBalance(initialBalance.subtract(amount).toString());
                        currentDetailModelVo1.setCreate_date(currentDetailModelVo.getPr_time());
                        currentDetailModelVo1.setIncrease_amount(amount.toString());
                        currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getInitial_balance());
                        currentDetailModelVo1.setPay_amount("0");
                        initialBalance = initialBalance.subtract(amount);
                    }
                    if(currentDetailModelVo.getCreate_date() != null && currentDetailModelVo.getPr_time() != null){
                        BigDecimal amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
                        BigDecimal payAmout = new BigDecimal(currentDetailModelVo.getPay_amount());
                        currentDetailModelVo1.setIncrease_amount(amount.toString());
                        currentDetailModelVo1.setPay_amount(payAmout.toString());
                        currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getInitial_balance());
                        currentDetailModelVo1.setBalance(initialBalance.add(payAmout).subtract(amount).toString());
                        PcmsPaymentDetailVo pcmsPaymentDetail = pcmsReconciliationMapper.selectByFssc(currentDetailModelVo.getFssc_bill());
                        currentDetailModelVo1.setCreate_date(pcmsPaymentDetail.getFinancialTime());
                        initialBalance = initialBalance.add(payAmout).subtract(amount);
                    }
                    if(currentDetailModelVo.getCreate_date() != null && currentDetailModelVo.getPr_time() == null){
                        BigDecimal payAmout = new BigDecimal(currentDetailModelVo.getPay_amount());
                        currentDetailModelVo1.setIncrease_amount("0");
                        currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getInitial_balance());
                        currentDetailModelVo1.setPay_amount(payAmout.toString());
                        currentDetailModelVo1.setBalance(initialBalance.add(payAmout).toString());
                        initialBalance = initialBalance.add(payAmout);
                    }
                    list.add(currentDetailModelVo1);
                }
            }
            for(CurrentDetailModelVo currentDetailModelVo:list){
                PcmsCurrentDetailModel pcmsCurrentDetailModel = new PcmsCurrentDetailModel();
                BeanUtils.copyProperties(currentDetailModelVo,pcmsCurrentDetailModel);
                pcmsCurrentDetailMapper.insert(pcmsCurrentDetailModel);
            }
            return ResultVo.getData(ResultVo.SUCCESS,list);
        }
    }

    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    public static String getLastTwoMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -2);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

}
