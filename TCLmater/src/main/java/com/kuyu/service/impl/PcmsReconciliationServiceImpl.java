package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.*;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.PcmsProjectUtil;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ReconciliationVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.*;
import com.kuyu.vo.query.ReconciliationQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private PcmsIinitializationMapper pcmsIinitializationMapper;
    @Resource
    private PcmsCurrentDetailMapper pcmsCurrentDetailMapper;
    @Resource
    private PcmsPtatisticsMapper pcmsPtatisticsMapper;
    @Resource
    private PcmsPaymentCheckMapper pcmsPaymentCheckMapper;
    @Resource
    private PcmsSupplierMapper pcmsSupplierMapper;
    @Resource
    private PcmsMessageMapper pcmsMessageMapper;
    @Resource
    private TpmEmployeeService tpmEmployeeService;
    @Resource
    private UnspecifiedDetailsMapper unspecifiedDetailsMapper;
    @Override
    public void selectByTime() {
        //查询上个月的付款记录
        List<PcmsSettlementVo> list = pcmsSettlementMapper.selectByTime(/*getLastTwoMonth()*/);
        for(PcmsSettlementVo pcmsSettlementVo : list){
//            List<PcmsSettlementVo> settlementVoList = pcmsSettlementMapper.selectPaymentByVendorId(pcmsSettlementVo.getVendor_id());
            PcmsReconciliationModel pcmsReconciliationModel = new PcmsReconciliationModel();
            pcmsReconciliationModel.setVendor_id(pcmsSettlementVo.getVendor_id());
            pcmsReconciliationModel.setVendor_name(pcmsSettlementVo.getVendor_name());
            pcmsReconciliationModel.setState(0);
            pcmsReconciliationModel.setCreate_time(DateUtils.getPreviousMonthFirstDay());
            pcmsReconciliationModel.setMonth(getLastMonth());
            pcmsReconciliationModel.setCompany(pcmsSettlementVo.getCompany());
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
    public ResultVo findReconciliationList(LoginUserInfo userInfo, ReconciliationQuery query) throws Exception {
        query.setCompany(userInfo.getEmployeeModel().getCompany());
        query.setMonth(getLastMonth());
        query = (ReconciliationQuery) CheckParamUtils.trimWithObjectField(query);
        Page<ReconciliationVo> page = new Page<>(query.getCurrent(),query.getSize());
        List<ReconciliationVo> pcmsSupplierList = baseMapper.findListPage(query,page);
        page.setRecords(pcmsSupplierList);
        return ResultVo.getDataWithSuccess(page);
    }
    @Override
    public ResultVo selectCurrentDetail(Integer id, LoginUserInfo userInfo) {
        CurrentDetailVo currentDetailVo = new CurrentDetailVo();
        List<CurrentDetailModelVo> list = new ArrayList<CurrentDetailModelVo>();
        list = pcmsReconciliationMapper.selectCurrent(id);
        if(list.size() >0){
            PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
            String month = pcmsReconciliationModel.getMonth()+"-01";
            //上个月最后一天
            String endDate = getMonthEndDay(month);
            //上个月第一天
            String firstDate = getMonthFirstDay(month);
            //查看上个月统计的期初期末余额
            PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByCompany(userInfo.getEmployeeModel().getCompany(),pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());
            BigDecimal initialBalance = new BigDecimal(pcmsIinitializationModel.getInitial_balance());
            for(CurrentDetailModelVo currentDetailModelVo : list){
                //本期增加金额
                BigDecimal increase_amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
                //本期已付金额
                BigDecimal pay_amount = new BigDecimal(currentDetailModelVo.getPay_amount());

                BigDecimal balance = initialBalance.add(pay_amount).subtract(increase_amount);
                initialBalance = balance;
                currentDetailModelVo.setBalance(balance.toString());
            }
            currentDetailVo.setDate(firstDate+"~"+endDate);
            currentDetailVo.setVendor_name(pcmsReconciliationModel.getVendor_name());
            currentDetailVo.setList(list);
            currentDetailVo.setInitial_balance(pcmsIinitializationModel.getInitial_balance());
            return ResultVo.getData(ResultVo.SUCCESS,currentDetailVo);
        }else{
            PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
            //判断是否每天同步了共享付款数据
            /*int num = getMonthSum(getLastMonth());
            int sum = pcmsPaymentCheckMapper.selectForMonthSum(getLastMonth());
            if(num != sum){
                throw new ParamException("请确认上个月是否都同步过共享的信息");
            }*/

            //查看上个月统计的期初期末余额
            PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByCompany(userInfo.getEmployeeModel().getCompany(),pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());
            BigDecimal initialBalance = new BigDecimal(pcmsIinitializationModel.getInitial_balance());

            //组装往来数据
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
                        currentDetailModelVo1.setCreate_date(pcmsPaymentDetail.getFinancialTime().substring(0,10));
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
                    currentDetailModelVo1.setBalance(initialBalance.toString());
                    list.add(currentDetailModelVo1);
                }
            }
            for(CurrentDetailModelVo currentDetailModelVo:list){
                PcmsCurrentDetailModel pcmsCurrentDetailModel = new PcmsCurrentDetailModel();
                BeanUtils.copyProperties(currentDetailModelVo,pcmsCurrentDetailModel);
                pcmsCurrentDetailModel.setBalance("0");
                pcmsCurrentDetailMapper.insert(pcmsCurrentDetailModel);
            }
            String month = pcmsReconciliationModel.getMonth()+"-01";
            //上个月最后一天
            String endDate = getMonthEndDay(month);
            //上个月第一天
            String firstDate = getMonthFirstDay(month);
            currentDetailVo.setDate(firstDate+"~"+endDate);
            currentDetailVo.setVendor_name(pcmsReconciliationModel.getVendor_name());
            currentDetailVo.setList(list);
            currentDetailVo.setInitial_balance(pcmsIinitializationModel.getInitial_balance());
            return ResultVo.getData(ResultVo.SUCCESS,currentDetailVo);
        }
    }
    @Override
    public ResultVo addCurrentDetail(PcmsCurrentDetailModel pcmsCurrentDetailModel) {
        pcmsCurrentDetailMapper.insert(pcmsCurrentDetailModel);
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }
    @Override
    public ResultVo addIinitialization(PcmsIinitializationModel query, LoginUserInfo userInfo) {
        query.setCompany(userInfo.getEmployeeModel().getCompany());
        //更新对账表
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(query.getPcms_reconciliation_id());
        pcmsReconciliationModel.setReconciliation_id(PcmsProjectUtil.creatReconciliationId());
        pcmsReconciliationMapper.updateById(pcmsReconciliationModel);
        //新增统计表数据
        query.setMonth(pcmsReconciliationModel.getMonth());
        query.setVendor_id(pcmsReconciliationModel.getVendor_id());
        pcmsIinitializationMapper.insert(query);
        //新增管理操作记录
        PcmsPtatisticsModel pcmsPtatisticsModel = new PcmsPtatisticsModel();
        pcmsPtatisticsModel.setCreate_time(new Date());
        pcmsPtatisticsModel.setPcms_reconciliation_id(pcmsReconciliationModel.getId());
        pcmsPtatisticsModel.setVendor_id(pcmsReconciliationModel.getVendor_id());
        //0表示未发送
        pcmsPtatisticsModel.setType(0);
        TpmEmployeeModel employeeModel = new TpmEmployeeModel();
        try {
            employeeModel = tpmEmployeeService.getTpmEmployeeByItcode(userInfo.getEmployeeModel().getItcode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pcmsPtatisticsModel.setMobile(employeeModel.getMobile());
        pcmsPtatisticsModel.setPerson_name(userInfo.getEmployeeModel().getPerson_name());
        pcmsPtatisticsMapper.insert(pcmsPtatisticsModel);
        //发送消息通知供应商
        /*PcmsMessageModel pcmsMessageModel = new PcmsMessageModel();
        pcmsMessageModel.setState(0);
        pcmsMessageModel.setCreate_time(new Date());
        pcmsMessageModel.setOther_id(String.valueOf(pcmsReconciliationModel.getId()));
        pcmsMessageModel.setVendor_id(pcmsReconciliationModel.getVendor_id());
        pcmsMessageModel.setContext(pcmsReconciliationModel.getMonth()+"对账单和对账函");
        pcmsMessageModel.setType(2);
        pcmsMessageMapper.insert(pcmsMessageModel);*/
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }
    @Override
    public ResultVo selectReconciliationList(String[] ids) {
        List<PcmsReconciliationVo> list = new ArrayList<PcmsReconciliationVo>();
        for(String id : ids){
            PcmsReconciliationVo pcmsReconciliationVo = pcmsReconciliationMapper.selectByReconciliationId(Integer.valueOf(id));
           /* PcmsPtatisticsModel pcmsPtatisticsModel = pcmsPtatisticsMapper.selectByReconciliationId(pcmsReconciliationModel.getId());
            pcmsPtatisticsModel.setType(1);
            pcmsPtatisticsMapper.updateById(pcmsPtatisticsModel);*/
            list.add(pcmsReconciliationVo);
        }
        /*for(PcmsReconciliationVo pcmsReconciliationVo : list){
            PcmsMessageModel pcmsMessageModel = new PcmsMessageModel();
            PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(pcmsReconciliationVo.getPcms_reconciliation_id());
            //发送消息通知供应商
            pcmsMessageModel.setVendor_id(pcmsReconciliationModel.getVendor_id());
            pcmsMessageModel.setOther_id(pcmsReconciliationModel.getId().toString());
            pcmsMessageModel.setType(2);
            pcmsMessageModel.setState(0);
            pcmsMessageModel.setCreate_time(new Date());
            pcmsMessageModel.setContext(pcmsReconciliationModel.getMonth()+"对账单和对账函");
            pcmsMessageMapper.insert(pcmsMessageModel);
            //修改对账单状态
            pcmsReconciliationModel.setState(1);
            pcmsReconciliationMapper.updateById(pcmsReconciliationModel);
        }*/
        return ResultVo.getData(ResultVo.SUCCESS,list);
    }
    @Override
    public ResultVo sureReconciliation(List<PcmsReconciliationVo> list) {
        for(PcmsReconciliationVo reconciliationVo : list){
            //更新对账列表状态
            PcmsMessageModel pcmsMessageModel = new PcmsMessageModel();
            PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(reconciliationVo.getPcms_reconciliation_id());
            //发送消息通知供应商
            pcmsMessageModel.setVendor_id(pcmsReconciliationModel.getVendor_id());
            pcmsMessageModel.setOther_id(pcmsReconciliationModel.getId().toString());
            pcmsMessageModel.setType(2);
            pcmsMessageModel.setState(0);
            pcmsMessageModel.setCreate_time(new Date());
            pcmsMessageModel.setContext(pcmsReconciliationModel.getMonth()+"对账单和对账函");
            pcmsMessageMapper.insert(pcmsMessageModel);
            //修改对账单状态
            pcmsReconciliationModel.setState(1);
            pcmsReconciliationMapper.updateById(pcmsReconciliationModel);
        }
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }
    @Override
    public ResultVo getAccountStatement(Integer id,LoginUserInfo userInfo) {
        AccountStatementVo accountStatementVo = new AccountStatementVo();
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
        String month = pcmsReconciliationModel.getMonth()+"-01";
        //上个月最后一天
        String endDate = getMonthDate(month);
        //上个月第一天
        String firstDate = getMonthFirst(month);
        //获取操作人信息
        PcmsPtatisticsModel pcmsPtatisticsModel = pcmsPtatisticsMapper.selectByReconciliationId(id);
        //获取统计数据信息
        PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByReconciliationId(id);
        //查看上个月统计的期初期末余额
        PcmsIinitializationModel pcmsIinitializationModel1 = pcmsIinitializationMapper.selectByCompany(userInfo.getEmployeeModel().getCompany(),pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());

        //获取往来数据信息
        List<CurrentDetailModelVo> list = pcmsReconciliationMapper.selectCurrent(id);
        //获取供应商信息
        PcmsSupplierVo pcmsSupplierVo = new PcmsSupplierVo();
        pcmsSupplierVo.setVendor_id(pcmsReconciliationModel.getVendor_id());
        PcmsSupplierModel pcmsSupplierModel = pcmsSupplierMapper.getPcmsSupplier(pcmsSupplierVo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(pcmsReconciliationModel.getState() == 2 || pcmsReconciliationModel.getState() == 3){
            accountStatementVo.setType(pcmsPtatisticsModel.getType());
            accountStatementVo.setPerson_name(pcmsSupplierModel.getLegal_person());
            accountStatementVo.setVendor_mobile(pcmsSupplierModel.getMobile());
            accountStatementVo.setVendor_name(pcmsReconciliationModel.getVendor_name());
            accountStatementVo.setVendor_time(sdf.format(pcmsPtatisticsModel.getUpdate_time()));
            accountStatementVo.setAmount(pcmsPtatisticsModel.getAmount());
            accountStatementVo.setRemark(pcmsPtatisticsModel.getRemark());
        }
        accountStatementVo.setAccountStatementDate(firstDate+"~"+endDate);
        accountStatementVo.setCompany_date(endDate);
        accountStatementVo.setCompany_mobile(pcmsPtatisticsModel.getMobile());
        accountStatementVo.setCompany_name(pcmsPtatisticsModel.getPerson_name());
        accountStatementVo.setList(list);
        accountStatementVo.setPcmsIinitializationModel(pcmsIinitializationModel);
        accountStatementVo.setReconciliation_id(pcmsReconciliationModel.getReconciliation_id());
        accountStatementVo.setId(pcmsReconciliationModel.getId());
        accountStatementVo.setStatisticsId(pcmsPtatisticsModel.getId());
        accountStatementVo.setReconciliation_id(pcmsReconciliationModel.getReconciliation_id());
        accountStatementVo.setVendor_name(pcmsReconciliationModel.getVendor_name());
        accountStatementVo.setInitial_balance(pcmsIinitializationModel1.getInitial_balance());
        accountStatementVo.setState(pcmsReconciliationModel.getState());
        return ResultVo.getDataWithSuccess(accountStatementVo);
    }
    @Override
    public ResultVo confirmReconciliation(Integer id) {
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
        pcmsReconciliationModel.setState(3);
        pcmsReconciliationMapper.updateById(pcmsReconciliationModel);
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo sendDetail(PcmsMessageModel pcmsMessageModel) {
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(pcmsMessageModel.getOther_id());
        pcmsMessageModel.setState(0);
        pcmsMessageModel.setContext(pcmsReconciliationModel.getMonth()+"未结明细");
        pcmsMessageModel.setCreate_time(new Date());
        pcmsMessageMapper.insert(pcmsMessageModel);
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo selectByVendorId(String openid) {
        List<PcmsMessageModel> list = pcmsMessageMapper.selectByVendorId(openid);
        return ResultVo.getDataWithSuccess(list);
    }

    @Override
    public ResultVo selectMessageDetail(Integer id,LoginUserInfo userInfo) {
        PcmsMessageModel pcmsMessageModel = pcmsMessageMapper.selectById(id);
        pcmsMessageModel.setState(1);
        pcmsMessageMapper.updateById(pcmsMessageModel);
        if(pcmsMessageModel.getType() == 1){
            return null;
        }else if(pcmsMessageModel.getType() == 2){
            return getAccountStatement(Integer.valueOf(pcmsMessageModel.getOther_id()),userInfo);
        }
        return null;
    }

    @Override
    public ResultVo replyMessage(ReplyMessageVo replyMessageVo) {
        PcmsMessageModel pcmsMessageModel = pcmsMessageMapper.selectById(replyMessageVo.getStatisticsId());
        PcmsPtatisticsModel pcmsPtatisticsModel = pcmsPtatisticsMapper.selectByReconciliationId(Integer.valueOf(pcmsMessageModel.getOther_id()));
        if(replyMessageVo.getType() == 1){
            pcmsPtatisticsModel.setType(1);
            pcmsPtatisticsModel.setUpdate_time(new Date());
            pcmsPtatisticsMapper.updateById(pcmsPtatisticsModel);
        }else if(replyMessageVo.getType() == 2){
            pcmsPtatisticsModel.setType(2);
            pcmsPtatisticsModel.setAmount(replyMessageVo.getPrice());
            pcmsPtatisticsModel.setRemark(replyMessageVo.getContext());
            pcmsPtatisticsModel.setUpdate_time(new Date());
            pcmsPtatisticsMapper.updateById(pcmsPtatisticsModel);
        }
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(replyMessageVo.getPcms_reconciliation_id());
        pcmsReconciliationModel.setState(2);
        pcmsReconciliationMapper.updateById(pcmsReconciliationModel);
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo selectDetailList(Integer id) {
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
        List<DetailListVo> list = unspecifiedDetailsMapper.selectByAllAndId(id);
        String month = pcmsReconciliationModel.getMonth()+"-01";
        //上个月最后一天
        String endDate = getMonthEndDay(month);
        //上个月第一天
        String firstDate = getMonthFirstDay(month);
        DetailVo detailVo = new DetailVo();
        detailVo.setReconciliation_id(pcmsReconciliationModel.getReconciliation_id());
        detailVo.setVendor_name(pcmsReconciliationModel.getVendor_name());
        detailVo.setDetailListVo(list);
        detailVo.setMonth(firstDate+"~"+endDate);
        return ResultVo.getDataWithSuccess(detailVo);
    }

    @Override
    public ResultVo selectPendingMaterial(Integer id) {
        RequestPendingMaterialDetailVo requestPendingMaterialDetailVo = new RequestPendingMaterialDetailVo();
        List<PendingMaterialDetailVo> list = pcmsReconciliationMapper.selectPendingMaterial(id);
        List<PendingMaterialDetailVo> list1 = pcmsReconciliationMapper.selectPendingMaterialList(id);
        List<String> stringList = new ArrayList<>();
        for(PendingMaterialDetailVo pendingMaterialDetailVo : list1){
            String requestId = pendingMaterialDetailVo.getRequest_id();
            stringList.add(requestId);
        }
        requestPendingMaterialDetailVo.setList(list);
        requestPendingMaterialDetailVo.setStringList(stringList);
        return ResultVo.getDataWithSuccess(requestPendingMaterialDetailVo);
    }

    @Override
    public void automaticReconciliation() {
        List<CurrentDetailModelVo> list = new ArrayList<CurrentDetailModelVo>();
        List<PcmsReconciliationModel> list1 = pcmsReconciliationMapper.selectByState();
        if(list1.size()>0){
            for(PcmsReconciliationModel pcmsReconciliationModel : list1){
                //查看上个月统计的期初期末余额
                PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByCompany(/*userInfo.getEmployeeModel().getCompany()*/"7601",pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());
                //期初余额
                BigDecimal initialBalance = new BigDecimal(pcmsIinitializationModel.getInitial_balance());
                //余额
                BigDecimal balance = BigDecimal.ZERO;
                //本期金额
                BigDecimal pay_amount = BigDecimal.ZERO;
                //本期已付金额
                BigDecimal financial_money = BigDecimal.ZERO;

                //组装往来数据
                List<CurrentDetailModelVo> currentDetailModelVoList = pcmsReconciliationMapper.selectCurrentDetail(pcmsReconciliationModel.getId());
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
                    //余额
                    balance = initialBalance.add(new BigDecimal(currentDetailModelVo.getPay_amount())).subtract(new BigDecimal(currentDetailModelVo.getIncrease_amount()));
                    //本期金额
                    pay_amount = pay_amount.add(new BigDecimal(currentDetailModelVo.getIncrease_amount()));
                    //本期已付金额
                    financial_money = financial_money.add(new BigDecimal(currentDetailModelVo.getPay_amount()));
                    PcmsCurrentDetailModel pcmsCurrentDetailModel = new PcmsCurrentDetailModel();
                    BeanUtils.copyProperties(currentDetailModelVo,pcmsCurrentDetailModel);
                    pcmsCurrentDetailMapper.insert(pcmsCurrentDetailModel);
                }
                PcmsIinitializationModel pcmsIinitializationModel1 = new PcmsIinitializationModel();
                pcmsIinitializationModel1.setMonth(getLastTwoMonth());
                pcmsIinitializationModel1.setVendor_id(pcmsReconciliationModel.getVendor_id());
                pcmsIinitializationModel1.setInitial_balance(initialBalance.toString());
                pcmsIinitializationModel1.setBalance(balance.toString());
                pcmsIinitializationModel1.setPay_amount(pay_amount.toString());
                pcmsIinitializationModel1.setFinancial_money(financial_money.toString());
                pcmsIinitializationModel1.setCreate_time(new Date());
                pcmsIinitializationModel1.setPcms_reconciliation_id(pcmsReconciliationModel.getId());
                pcmsIinitializationModel1.setCompany(pcmsReconciliationModel.getCompany());
                pcmsIinitializationMapper.insert(pcmsIinitializationModel1);
            }
        }
    }

    @Override
    public void automaticStatistics() {
        String month = getMonthDate(getLastTwoMonth()+"-01").substring(0,7);
        List<PcmsIinitializationModel> list = pcmsIinitializationMapper.selectByMonth(month);
        for(PcmsIinitializationModel pcmsIinitializationModel : list){
            PcmsIinitializationModel pcmsIinitializationModel1 = new PcmsIinitializationModel();
            pcmsIinitializationModel1.setCompany(pcmsIinitializationModel.getCompany());
            pcmsIinitializationModel1.setVendor_id(pcmsIinitializationModel.getVendor_id());
            pcmsIinitializationModel1.setBalance("0");
            pcmsIinitializationModel1.setFinancial_money("0");
            pcmsIinitializationModel1.setPay_amount("0");
            pcmsIinitializationModel1.setInitial_balance("0");
            pcmsIinitializationModel1.setMonth(getLastMonth());
            pcmsIinitializationMapper.insert(pcmsIinitializationModel1);
        }
    }

    @Override
    public void automaticDetailList() {
        //查询上个月对账单
        List<ReconciliationVo> list = pcmsReconciliationMapper.selectByMonth(getLastMonth());
        if(list.size()>0){
            for(ReconciliationVo reconciliationVo : list){
                List<DetailListVo> detailList = pcmsReconciliationMapper.selectDetailList(reconciliationVo.getId());
                for(DetailListVo detailListVo : detailList){
                    UnspecifiedDetailsModel unspecifiedDetailsModel = new UnspecifiedDetailsModel();
                    BeanUtils.copyProperties(detailListVo,unspecifiedDetailsModel);
                    unspecifiedDetailsModel.setCreate_time(new Date());
                    unspecifiedDetailsMapper.insert(unspecifiedDetailsModel);
                }
            }
        }
    }

    /**
     * 获取上个月
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 获取上两个月
     * @return
     */
    public static String getLastTwoMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -2);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 获取上个月天数
     * @param month
     * @return
     */
    public static int getMonthSum(String month){
        Calendar   cal   =   new GregorianCalendar();
        SimpleDateFormat oSdf = new SimpleDateFormat ("yyyy-MM");
        try {
            cal.setTime(oSdf.parse(month));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int num = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return num;
    }

    /**
     * 获取上月的最后一天
     * @return
     */
    public static String getMonthDate(String month){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        String lastDay = format.format(calendar.getTime());
        return lastDay;
    }

    /**
     * 获取上月的第一天
     * @return
     */
    public static String getMonthFirst(String month){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = format.format(calendar.getTime());
        return firstDay;
    }

    public static String getMonthFirstDay(String month){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String first = format.format(calendar.getTime());
        return first;
    }

    public static String getMonthEndDay(String month){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(calendar.getTime());
        return last;
    }
}
