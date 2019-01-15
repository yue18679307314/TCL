package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.TpmDeptMapper;
import com.kuyu.mapper.TpmEmployeeMapper;
import com.kuyu.mapper.pcms.*;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.service.PcmsSupplierCompanyService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.PcmsProjectUtil;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ReconciliationVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.*;
import com.kuyu.vo.query.ReconciliationQuery;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
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
    private TpmEmployeeMapper tpmEmployeeMapper;
    @Resource
    private UnspecifiedDetailsMapper unspecifiedDetailsMapper;
    @Resource
    private PcmsSupplierUserMapper pcmsSupplierUserMapper;
    @Resource
    private PcmsInitializationLogMapper pcmsInitializationLogMapper;
    @Resource
    private PcmsSupplierCompanyService pcmsSupplierCompanyService;
    @Resource
    private PcmsProfitCenterMapper pcmsProfitCenterMapper;

    @Value("${excel.path}")
    private String filePath;
    @Value("${excel.url}")
    private String fileUrl;
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
            pcmsReconciliationModel.setCreate_time(new Date());
            pcmsReconciliationModel.setMonth(getLastMonth());
            pcmsReconciliationModel.setCompany(pcmsSettlementVo.getCompany());
            pcmsReconciliationModel.setType(0);
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
        TpmDeptModel tpmDeptModel = selectTpmDept(userInfo.getEmployeeModel().getOrg_code());
        query.setCompany(tpmDeptModel.getOrg_code());
        if(query.getMonth() == null){
            query.setMonth(getLastMonth());
        }
        query = (ReconciliationQuery) CheckParamUtils.trimWithObjectField(query);
        Page<ReconciliationVo> page = new Page<>(query.getCurrent(),query.getSize());
        List<ReconciliationVo> pcmsSupplierList = baseMapper.findListPage(query,page);
        page.setRecords(pcmsSupplierList);
        return ResultVo.getDataWithSuccess(page);
    }
    @Override
    public ResultVo selectCurrentDetail(Integer id, LoginUserInfo userInfo)throws Exception {
        TpmDeptModel tpmDeptModel = selectTpmDept(userInfo.getEmployeeModel().getOrg_code());
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
            PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByCompany(tpmDeptModel.getOrg_code(),pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());
            if(null == pcmsIinitializationModel){
                throw new ParamException("该供应商未初始化期初余额");
            }
            BigDecimal initialBalance = new BigDecimal(pcmsIinitializationModel.getBalance());
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
            currentDetailVo.setInitial_balance(pcmsIinitializationModel.getBalance());
            return ResultVo.getDataWithSuccess(currentDetailVo);
        }else{
            PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
            //判断是否每天同步了共享付款数据
            /*int num = getMonthSum(getLastMonth());
            int sum = pcmsPaymentCheckMapper.selectForMonthSum(getLastMonth());
            if(num != sum){
                throw new ParamException("请确认上个月是否都同步过共享的信息");
            }*/

            //查看上个月统计的期初期末余额
            PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByCompany(tpmDeptModel.getOrg_code(),pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());
            if(null == pcmsIinitializationModel){
                throw new ParamException("该供应商未初始化期初余额");
            }
            BigDecimal initialBalance = new BigDecimal(pcmsIinitializationModel.getBalance());

            //组装往来数据
            List<CurrentDetailModelVo> currentDetailModelVoList = pcmsReconciliationMapper.selectCurrentDetail(id);
            if(currentDetailModelVoList != null){
                for(CurrentDetailModelVo currentDetailModelVo : currentDetailModelVoList){
                    CurrentDetailModelVo currentDetailModelVo1 = new CurrentDetailModelVo();
                    BeanUtils.copyProperties(currentDetailModelVo,currentDetailModelVo1);
                    currentDetailModelVo1.setType(0);
                    if(currentDetailModelVo.getCreate_date() == null && currentDetailModelVo.getPr_time() == null){
                        currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getBalance());
                        currentDetailModelVo1.setBalance(pcmsIinitializationModel.getBalance());
                        currentDetailModelVo1.setIncrease_amount("0");
                        currentDetailModelVo1.setPay_amount("0");
                        currentDetailModelVo1.setCreate_date("--");
                    }
                    if(currentDetailModelVo.getCreate_date() == null && currentDetailModelVo.getPr_time() != null){
                        BigDecimal amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
                        currentDetailModelVo1.setBalance(initialBalance.subtract(amount).toString());
                        currentDetailModelVo1.setCreate_date(currentDetailModelVo.getPr_time());
                        currentDetailModelVo1.setIncrease_amount(amount.toString());
                        currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getBalance());
                        currentDetailModelVo1.setPay_amount("0");
                        initialBalance = initialBalance.subtract(amount);
                    }
                    if(currentDetailModelVo.getCreate_date() != null && currentDetailModelVo.getPr_time() != null){
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        BigDecimal payAmout = new BigDecimal(currentDetailModelVo.getPay_amount());
                        Date dt1 = df.parse(pcmsReconciliationModel.getMonth()+"-01");
                        Date dt2 = df.parse(currentDetailModelVo.getPr_time());
                        BigDecimal amount = new BigDecimal(0);
                        if(dt1.getTime() > dt2.getTime()){
                            amount = new BigDecimal(0);
                        }else{
                            amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
                        }
                        currentDetailModelVo1.setIncrease_amount(amount.toString());
                        currentDetailModelVo1.setPay_amount(payAmout.toString());
                        currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getBalance());
                        currentDetailModelVo1.setBalance(initialBalance.add(payAmout).subtract(amount).toString());
                        PcmsPaymentDetailVo pcmsPaymentDetail = pcmsReconciliationMapper.selectByFssc(currentDetailModelVo.getFssc_bill());
                        currentDetailModelVo1.setCreate_date(pcmsPaymentDetail.getFinancialTime().substring(0,10));
                        initialBalance = initialBalance.add(payAmout).subtract(amount);
                    }
                    if(currentDetailModelVo.getCreate_date() != null && currentDetailModelVo.getPr_time() == null){
                        BigDecimal payAmout = new BigDecimal(currentDetailModelVo.getPay_amount());
                        currentDetailModelVo1.setIncrease_amount("0");
                        currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getBalance());
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
            list.sort((CurrentDetailModelVo h1, CurrentDetailModelVo h2) -> h1.getCreate_date().compareTo(h2.getCreate_date()));
            BigDecimal initialBalance1 = new BigDecimal(pcmsIinitializationModel.getBalance());
            for(CurrentDetailModelVo currentDetailModelVo : list){
                //本期增加金额
                BigDecimal increase_amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
                //本期已付金额
                BigDecimal pay_amount = new BigDecimal(currentDetailModelVo.getPay_amount());
                BigDecimal balance = initialBalance1.add(pay_amount).subtract(increase_amount);
                initialBalance1 = balance;
                currentDetailModelVo.setBalance(balance.toString());
            }
            String month = pcmsReconciliationModel.getMonth()+"-01";
            //上个月最后一天
            String endDate = getMonthEndDay(month);
            //上个月第一天
            String firstDate = getMonthFirstDay(month);
            currentDetailVo.setDate(firstDate+"~"+endDate);
            currentDetailVo.setVendor_name(pcmsReconciliationModel.getVendor_name());
            currentDetailVo.setList(list);
            currentDetailVo.setInitial_balance(pcmsIinitializationModel.getBalance());
            return ResultVo.getData(ResultVo.SUCCESS,currentDetailVo);
        }
    }
    @Override
    public ResultVo addCurrentDetail(PcmsCurrentDetailModel pcmsCurrentDetailModel) {
        pcmsCurrentDetailMapper.insert(pcmsCurrentDetailModel);
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }
    @Override
    public ResultVo addIinitialization(PcmsIinitializationModel query, LoginUserInfo userInfo){
        TpmDeptModel tpmDeptModel = selectTpmDept(userInfo.getEmployeeModel().getOrg_code());
        query.setCompany(tpmDeptModel.getOrg_code());
        //更新对账表
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(query.getPcms_reconciliation_id());
        pcmsReconciliationModel.setReconciliation_id(PcmsProjectUtil.creatReconciliationId());
        pcmsReconciliationModel.setType(1);
        pcmsReconciliationModel.setCreate_time(new Date());
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
            employeeModel = tpmEmployeeMapper.getTpmEmployeeByItcode(userInfo.getEmployeeModel().getItcode());
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
            pcmsMessageModel.setCompany(pcmsReconciliationModel.getCompany());
            pcmsMessageMapper.insert(pcmsMessageModel);
            //修改对账单状态
            pcmsReconciliationModel.setState(1);
            pcmsReconciliationMapper.updateById(pcmsReconciliationModel);
        }
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }
    @Override
    public ResultVo getAccountStatement(Integer id,LoginUserInfo userInfo) {
        TpmDeptModel tpmDeptModel = null;
        if(null != userInfo.getEmployeeModel().getOrg_code()){
            tpmDeptModel = selectTpmDept(userInfo.getEmployeeModel().getOrg_code());
        }else{
            tpmDeptModel = selectTpmDept(userInfo.getEmployeeModel().getCompany());
        }

        AccountStatementVo accountStatementVo = new AccountStatementVo();
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
        String month = pcmsReconciliationModel.getMonth()+"-01";
        //上个月最后一天
        String endDate = getMonthEndDay(month);
        //上个月第一天
        String firstDate = getMonthFirstDay(month);
        //获取操作人信息
        PcmsPtatisticsModel pcmsPtatisticsModel = pcmsPtatisticsMapper.selectByReconciliationId(id);
        //获取统计数据信息
        PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByReconciliationId(id);
        //查看上个月统计的期初期末余额
        PcmsIinitializationModel pcmsIinitializationModel1 = pcmsIinitializationMapper.selectByCompany(tpmDeptModel.getOrg_code(),pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());
        if(null == pcmsIinitializationModel1){
            throw new ParamException("该供应商未初始化期初余额");
        }
        //获取入账法人名称
        TpmEmployeeModel employeeModel = null;
        try {
            PcmsInitializationLogModel pcmsInitializationLogModel = new PcmsInitializationLogModel();
            pcmsInitializationLogModel.setVendor_id(pcmsReconciliationModel.getVendor_id());
            pcmsInitializationLogModel.setOrg_code(pcmsReconciliationModel.getCompany());
            PcmsInitializationLogModel pcmsInitializationLogModel1 = pcmsInitializationLogMapper.selectOne(pcmsInitializationLogModel);
            List<TpmEmployeeModel> employeeList = tpmEmployeeMapper.getEmployeeListByCompany(pcmsInitializationLogModel1.getCompany());
            employeeModel = employeeList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取往来数据信息
        List<CurrentDetailModelVo> list = pcmsReconciliationMapper.selectCurrent(id);
        BigDecimal initialBalance = new BigDecimal(pcmsIinitializationModel1.getBalance());
        for(CurrentDetailModelVo currentDetailModelVo : list){
            //本期增加金额
            BigDecimal increase_amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
            //本期已付金额
            BigDecimal pay_amount = new BigDecimal(currentDetailModelVo.getPay_amount());

            BigDecimal balance = initialBalance.add(pay_amount).subtract(increase_amount);
            initialBalance = balance;
            currentDetailModelVo.setBalance(balance.toString());
        }
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
        accountStatementVo.setIncorporated_person(employeeModel.getOrg_name());
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
        accountStatementVo.setInitial_balance(pcmsIinitializationModel1.getBalance());
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
        pcmsMessageModel.setType(1);
        pcmsMessageModel.setVendor_id(pcmsReconciliationModel.getVendor_id());
        pcmsMessageModel.setCompany(pcmsReconciliationModel.getCompany());
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
    public ResultVo selectByState(String openid,Integer state) {
        List<PcmsMessageModel> list = pcmsMessageMapper.selectByState(openid,state);
        return ResultVo.getDataWithSuccess(list);
    }

    @Override
    public ResultVo updateBank(String openid,String opening_bank,String opening_account)throws Exception {
        PcmsSupplierUserModel pcmsUserModel =  pcmsSupplierUserMapper.findByOpenid(openid);
        PcmsSupplierVo pcmsSupplierVo = new PcmsSupplierVo();
        pcmsSupplierVo.setVendor_id(pcmsUserModel.getVendor_id());
        PcmsSupplierModel pcmsSupplierModel = pcmsSupplierMapper.getPcmsSupplier(pcmsSupplierVo);
        pcmsSupplierModel.setOpening_bank(opening_bank);
        pcmsSupplierModel.setOpening_account(opening_account);
        pcmsSupplierMapper.updatePcmsSupplierModel(pcmsSupplierModel);
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo selectBankInfo(String openid) {
        PcmsSupplierUserModel pcmsUserModel =  pcmsSupplierUserMapper.findByOpenid(openid);
        PcmsSupplierVo pcmsSupplierVo = new PcmsSupplierVo();
        pcmsSupplierVo.setVendor_id(pcmsUserModel.getVendor_id());
        PcmsSupplierModel pcmsSupplierModel = pcmsSupplierMapper.getPcmsSupplier(pcmsSupplierVo);
        return ResultVo.getDataWithSuccess(pcmsSupplierModel);
    }

    @Override
    public ResultVo selectMessageDetail(Integer id,String company) {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        TpmEmployeeModel tpmEmployeeModel = new TpmEmployeeModel();
        tpmEmployeeModel.setCompany(company);
        loginUserInfo.setEmployeeModel(tpmEmployeeModel);
        PcmsMessageModel pcmsMessageModel = pcmsMessageMapper.selectById(id);
        pcmsMessageModel.setState(1);
        pcmsMessageMapper.updateById(pcmsMessageModel);
        if(pcmsMessageModel.getType() == 1){
            return selectDetailList(id);
        }else if(pcmsMessageModel.getType() == 2){
            return getAccountStatement(Integer.valueOf(pcmsMessageModel.getOther_id()),loginUserInfo);
        }
        return null;
    }

    @Override
    public ResultVo replyMessage(ReplyMessageVo replyMessageVo) {
        PcmsMessageModel pcmsMessageModel = pcmsMessageMapper.selectById(replyMessageVo.getStatisticsId());
        PcmsPtatisticsModel pcmsPtatisticsModel = pcmsPtatisticsMapper.selectByReconciliationId(Integer.parseInt(pcmsMessageModel.getOther_id()));
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
        pcmsMessageModel.setState(2);
        pcmsMessageMapper.updateById(pcmsMessageModel);
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo selectDetailList(Integer id) {
        PcmsReconciliationModel pcmsReconciliationModel = pcmsReconciliationMapper.selectById(id);
        List<DetailListVo> list = new ArrayList<>();
        list = unspecifiedDetailsMapper.selectByAllAndId(id);
        if(list.size()==0){
            List<DetailListVo> detailList = pcmsReconciliationMapper.selectDetailList(id);
            for(DetailListVo detailListVo : detailList){
                UnspecifiedDetailsModel unspecifiedDetailsModel = new UnspecifiedDetailsModel();
                BeanUtils.copyProperties(detailListVo,unspecifiedDetailsModel);
                unspecifiedDetailsModel.setCreate_time(new Date());
                unspecifiedDetailsMapper.insert(unspecifiedDetailsModel);
                list.add(detailListVo);
            }
        }
        //获取入账法人名称
        TpmEmployeeModel employeeModel = null;
        try {
            PcmsInitializationLogModel pcmsInitializationLogModel = new PcmsInitializationLogModel();
            pcmsInitializationLogModel.setVendor_id(pcmsReconciliationModel.getVendor_id());
            pcmsInitializationLogModel.setOrg_code(pcmsReconciliationModel.getCompany());
            PcmsInitializationLogModel pcmsInitializationLogModel1 = pcmsInitializationLogMapper.selectOne(pcmsInitializationLogModel);
            List<TpmEmployeeModel> employeeList = tpmEmployeeMapper.getEmployeeListByCompany(pcmsInitializationLogModel1.getCompany());
            employeeModel = employeeList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        detailVo.setIncorporated_person(employeeModel.getOrg_name());
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
    public ResultVo synchronousBalance(MultipartFile file, LoginUserInfo userInfo) throws Exception {
        String xls = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        String paths = StringUtil.getUUID();
        String nameXls = paths+xls;
        String finalPath = filePath+ "/"+nameXls;
//        String suffix = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
        File tempfile = new File(filePath);
        if(!tempfile.exists()){
            tempfile.mkdirs();
        }
        File newFile = new File(finalPath);
        file.transferTo(newFile);
        Map<String, String> map  = readExcel(finalPath);
        if(map.size() == 0 || null == map){
            newFile.delete();
            throw new ParamException("数据为空");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            PcmsSupplierMaterialModel tdm = new PcmsSupplierMaterialModel();
            String[] psm = value.split(",");
            for (int i = 0; i < psm.length; i++) {
                if(psm[i].equals("NULL")) {
                    psm[i] = null;
                }
            }
            PcmsInitializationLogModel pcmsInitializationLog = new PcmsInitializationLogModel();
            pcmsInitializationLog.setOrg_code(psm[0]);
            pcmsInitializationLog.setAccounting_company(psm[1]);
            pcmsInitializationLog.setCompany(psm[3]);
            pcmsInitializationLog.setCompany_name(psm[4]);
            pcmsInitializationLog.setSubject(psm[6]);
            pcmsInitializationLog.setSubject_name(psm[7]);
            pcmsInitializationLog.setInitial_balance(psm[8]);
            pcmsInitializationLog.setVendor_id(psm[9]);
            pcmsInitializationLog.setVendor_name(psm[10]);
            pcmsInitializationLog.setCreate_time(new Date());
            pcmsInitializationLog.setProfit_center(psm[5]);
            pcmsInitializationLog.setCompanyId(psm[2]);


            PcmsIinitializationModel pcmsIinitialization = new PcmsIinitializationModel();
            pcmsIinitialization.setVendor_id(psm[9]);
            pcmsIinitialization.setCompany(psm[0]);
            pcmsIinitialization.setBalance(psm[8]);
            pcmsIinitialization.setInitial_balance("0");
            pcmsIinitialization.setCreate_time(new Date());
            pcmsIinitialization.setFinancial_money("0");
            pcmsIinitialization.setPay_amount("0");
            pcmsIinitialization.setMonth(getLastTwoMonth());


            PcmsSupplierCompanyModel pcmsSupplierCompanyModel = new PcmsSupplierCompanyModel();
            pcmsSupplierCompanyModel.setCompany(psm[0]);
            pcmsSupplierCompanyModel.setVendor_id(psm[9]);
            pcmsSupplierCompanyModel.setCreate_time(DateUtils.getLongDateStr());


            PcmsSupplierVo pcmsSupplier = new PcmsSupplierVo();
            pcmsSupplier.setVendor_id(psm[9]);
            pcmsSupplier.setVendor_name(psm[10]);
            pcmsSupplier.setDel_comp("n");
            pcmsSupplier.setDel_flag("n");

            if(null != psm[7]){
                pcmsInitializationLogMapper.insert(pcmsInitializationLog);
                pcmsIinitializationMapper.insert(pcmsIinitialization);
                pcmsSupplierCompanyService.insertPcmsSupplierCompany(pcmsSupplierCompanyModel);
                pcmsSupplierMapper.insertPcmsSupplier(pcmsSupplier);
            }
        }
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo importProfitCenter(MultipartFile file) throws Exception {
        String xls = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        String paths = StringUtil.getUUID();
        String nameXls = paths+xls;
        String finalPath = filePath+ "/"+nameXls;
//        String suffix = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
        File tempfile = new File(filePath);
        if(!tempfile.exists()){
            tempfile.mkdirs();
        }
        File newFile = new File(finalPath);
        file.transferTo(newFile);
        Map<String, String> map  = readExcel(finalPath);
        if(map.size() == 0 || null == map){
            newFile.delete();
            throw new ParamException("数据为空");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            PcmsSupplierMaterialModel tdm = new PcmsSupplierMaterialModel();
            String[] psm = value.split(",");
            for (int i = 0; i < psm.length; i++) {
                if (psm[i].equals("NULL")) {
                    psm[i] = null;
                }
            }

            PcmsProfitCenterModel pcmsProfitCenterModel = new PcmsProfitCenterModel();
            pcmsProfitCenterModel.setCompany(psm[0]);
            pcmsProfitCenterModel.setCompany_name(psm[7]);
            pcmsProfitCenterModel.setCompanyId(psm[5]);
            pcmsProfitCenterModel.setLong_name(psm[2]);
            pcmsProfitCenterModel.setName(psm[3]);
            pcmsProfitCenterModel.setOrg_code(psm[6]);
            pcmsProfitCenterModel.setProfit_center_code(psm[1]);
            pcmsProfitCenterModel.setRematk(psm[4]);
            pcmsProfitCenterMapper.insert(pcmsProfitCenterModel);
        }
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    public static Map<String, String> readExcel(String filePath) {
        Map<String, String> map = new HashMap<String, String>();
        Workbook workbook = null;
        // 创建对Excel工作簿文件的引用
        try {
            // filePath是文件地址。
            boolean isExcel2003 = filePath.toLowerCase().endsWith("xls") ? true : false;
            if (isExcel2003) {
                workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
            } else {
                // workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
                FileInputStream input = new FileInputStream(new File(filePath)); // 读取的文件路径
                workbook = new XSSFWorkbook(new BufferedInputStream(input));
            }

            Sheet sheet = workbook.getSheetAt(0); // 创建对工作表的引用

            // 获取到Excel文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();
            int max_cells = 0;
            // 获取最长的列，在实践中发现如果列中间有空值的话，那么读到空值的地方就停止了。所以我们需要取得最长的列。
            //如果每个列正好都有一个空值得话，通过这种方式获得的列长会比真实的列要少一列。所以我自己会在将要倒入数据库中的EXCEL表加一个表头
            // //防止列少了，而插入数据库中报错。
            for (int i = 0; i < rows; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int cells = row.getPhysicalNumberOfCells();
                    if (max_cells < cells) {
                        max_cells = cells;
                    }

                }
            }
            // 遍历行
            for (int i = 1; i < rows; i++) {//从1开始循环，去掉表头
                // 读取左上端单元格
                Row row = sheet.getRow(i);
                // 行不为空
                if (row != null) {
                    String value = "";
                    // 遍历列
                    String b_id = null;
                    for (int j = 0; j < max_cells; j++) {
                        // 获取到列的值
                        Cell cell = row.getCell(j);// 把所有是空值的都换成NULL
                        if (cell == null) {
                            value += "NULL,";
                        } else {
                            switch (cell.getCellType()) {// 如果是公式的话，就读取得出的值
                                case HSSFCell.CELL_TYPE_FORMULA:
                                    try {
                                        value += "'" + String.valueOf(cell.getNumericCellValue()).replaceAll("'", "")
                                                + "',";
                                    } catch (IllegalStateException e) {
                                        value += "'" + String.valueOf(cell.getRichStringCellValue()).replaceAll("'", "")
                                                + "',";
                                    }
                                    break;
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    // 如果有日期的话，那么就读出日期格式
                                    // 如果是数字的话，就写出数字格式
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date2 = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                                        String date1 = dff.format(date2);
                                        value += "'" + date1.replaceAll("'", "") + "',";

                                    } else {
                                        value += "'" + cell.getNumericCellValue() + "',";
                                    }
                                    break;
                                case HSSFCell.CELL_TYPE_STRING:
                                    String ss = cell.getStringCellValue().replaceAll("'", "");// 如果文本有空值的话，就把它写成null
                                    if (ss == null || "".equals(ss)) {
                                        value += "NULL,";
                                    } else {
                                        value += "'" + cell.getStringCellValue().replaceAll("'", "") + "',";
                                    }

                                    break;
                                default:
                                    value += "NULL,";
                                    break;
                            }
                        }

                        if (j == 0) {

                            b_id = value.substring(1, value.length() - 2);
                        }

                    }

                    String valueresult = value.substring(0, value.length() - 1);
                    valueresult = valueresult.replaceAll("'", "");
                    map.put(StringUtil.getUUID(), valueresult);
                }
            }
        } catch (Exception e) {
            File newFile = new File(filePath);
            newFile.delete();
            e.printStackTrace();
        }
        return map;
    }
    @Override
    public void automaticReconciliation() {
        List<CurrentDetailModelVo> list = new ArrayList<CurrentDetailModelVo>();
        List<PcmsReconciliationModel> list1 = pcmsReconciliationMapper.selectByState(getLastMonth());
        if(list1.size()>0){
            for(PcmsReconciliationModel pcmsReconciliationModel : list1){
                //查看上个月统计的期初期末余额
                PcmsIinitializationModel pcmsIinitializationModel = pcmsIinitializationMapper.selectByCompany(pcmsReconciliationModel.getCompany(),pcmsReconciliationModel.getVendor_id(),getLastTwoMonth());
                if(null == pcmsIinitializationModel){
                    throw new ParamException("该供应商未初始化期初余额");
                }
                //期初余额
                BigDecimal initialBalance = new BigDecimal(pcmsIinitializationModel.getBalance());
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
                            currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getBalance());
                            currentDetailModelVo1.setBalance(pcmsIinitializationModel.getBalance());
                            currentDetailModelVo1.setIncrease_amount("0");
                            currentDetailModelVo1.setPay_amount("0");
                            currentDetailModelVo1.setCreate_date("--");
                        }
                        if(currentDetailModelVo.getCreate_date() == null && currentDetailModelVo.getPr_time() != null){
                            BigDecimal amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
                            currentDetailModelVo1.setBalance(initialBalance.subtract(amount).toString());
                            currentDetailModelVo1.setCreate_date(currentDetailModelVo.getPr_time());
                            currentDetailModelVo1.setIncrease_amount(amount.toString());
                            currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getBalance());
                            currentDetailModelVo1.setPay_amount("0");
                            initialBalance = initialBalance.subtract(amount);
                        }
                        if(currentDetailModelVo.getCreate_date() != null && currentDetailModelVo.getPr_time() != null){
                            BigDecimal amount = new BigDecimal(currentDetailModelVo.getIncrease_amount());
                            BigDecimal payAmout = new BigDecimal(currentDetailModelVo.getPay_amount());
                            currentDetailModelVo1.setIncrease_amount(amount.toString());
                            currentDetailModelVo1.setPay_amount(payAmout.toString());
                            currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getBalance());
                            currentDetailModelVo1.setBalance(initialBalance.add(payAmout).subtract(amount).toString());
                            PcmsPaymentDetailVo pcmsPaymentDetail = pcmsReconciliationMapper.selectByFssc(currentDetailModelVo.getFssc_bill());
                            currentDetailModelVo1.setCreate_date(pcmsPaymentDetail.getFinancialTime().substring(0,10));
                            initialBalance = initialBalance.add(payAmout).subtract(amount);
                        }
                        if(currentDetailModelVo.getCreate_date() != null && currentDetailModelVo.getPr_time() == null){
                            BigDecimal payAmout = new BigDecimal(currentDetailModelVo.getPay_amount());
                            currentDetailModelVo1.setIncrease_amount("0");
                            currentDetailModelVo1.setInitial_balance(pcmsIinitializationModel.getBalance());
                            currentDetailModelVo1.setPay_amount(payAmout.toString());
                            currentDetailModelVo1.setBalance(initialBalance.add(payAmout).toString());
                            initialBalance = initialBalance.add(payAmout);
                        }
                        currentDetailModelVo1.setBalance(initialBalance.toString());
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
                pcmsIinitializationModel1.setMonth(getLastMonth());
                pcmsIinitializationModel1.setVendor_id(pcmsReconciliationModel.getVendor_id());
                pcmsIinitializationModel1.setInitial_balance(pcmsIinitializationModel.getBalance());
                pcmsIinitializationModel1.setBalance(initialBalance.toString());
                pcmsIinitializationModel1.setPay_amount(pay_amount.toString());
                pcmsIinitializationModel1.setFinancial_money(financial_money.toString());
                pcmsIinitializationModel1.setCreate_time(new Date());
                pcmsIinitializationModel1.setPcms_reconciliation_id(pcmsReconciliationModel.getId());
                pcmsIinitializationModel1.setCompany(pcmsReconciliationModel.getCompany());
                pcmsIinitializationMapper.insert(pcmsIinitializationModel1);
                pcmsReconciliationModel.setType(1);
                pcmsReconciliationMapper.updateById(pcmsReconciliationModel);
            }
        }
    }

    @Override
    public void automaticStatistics() {
//        String month = getMonthDate(getLastTwoMonth()+"-01").substring(0,7);
        List<PcmsIinitializationModel> list = pcmsIinitializationMapper.selectByMonth(getLastMonth(),getLastTwoMonth());
        for(PcmsIinitializationModel pcmsIinitializationModel : list){
            PcmsIinitializationModel pcmsIinitializationModel1 = new PcmsIinitializationModel();
            BeanUtils.copyProperties(pcmsIinitializationModel,pcmsIinitializationModel1);
            /*cmsIinitializationModel1.setCompany(pcmsIinitializationModel.getCompany());
            pcmsIinitializationModel1.setVendor_id(pcmsIinitializationModel.getVendor_id());
            pcmsIinitializationModel1.setBalance("0");
            pcmsIinitializationModel1.setFinancial_money("0");
            pcmsIinitializationModel1.setPay_amount("0");
            pcmsIinitializationModel1.setInitial_balance("0");*/
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
                List<DetailListVo> list1 =  unspecifiedDetailsMapper.selectByAllAndId(reconciliationVo.getId());
                if(list1.size() == 0 || null == list1){
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
    }

    @Override
    public TpmDeptModel selectTpmDept(String org_code) {
        TpmDeptModel tpmDeptModel = pcmsReconciliationMapper.selectTpmDept(org_code);
        if(tpmDeptModel!=null){
        	if(tpmDeptModel.getOrg_code_parent().equals("90003975")){
        		return tpmDeptModel;
        	}else{
        		return selectTpmDept(tpmDeptModel.getOrg_code_parent());
        	}
        }
        return null;
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
