package com.kuyu.task.pcms;

import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

import static com.kuyu.util.DateUtils.DATE_FORMAT_DATEONLY;

/**
 * Created by pc on 2018/12/14
 */
@Component
public class UpdateSupplierToken {
    private static final Logger log = LoggerFactory.getLogger(UpdateSupplierToken.class);
    @Resource
    private PcmsSupplierService pcmsSupplierService;
    @Resource
    private PcmsReconciliationService pcmsReconciliationService;

    /**
     * 同步供应商信息
     */
    @Scheduled(cron = "${cron.updateSupplierToken}")//凌晨03:00执行一次
    public void updateSupplierToken() {
        Date date = DateUtils.subDays(1);
        log.info("*****正在获取,执行时间:{}*****", date);
        String synDate = DateUtils.toString(date,DATE_FORMAT_DATEONLY);
        try {
            pcmsSupplierService.synch(synDate);
        } catch (Exception e) {
            log.info("*****同步供应商信息出错:{}*****", DateUtils.currentTime());
            e.printStackTrace();
        }
    }

    /**
     * 生成上个月的对账信息
     */
    @Scheduled(cron = "${cron.selectByTimeToken}")//每月凌晨01:00执行一次
    public void selectByTime() {
        Date date = DateUtils.subDays(1);
        log.info("*****正在获取,执行时间:{}*****", date);
        try {
            pcmsReconciliationService.selectByTime();
        } catch (Exception e) {
            log.info("*****生成上个月的对账信息出错:{}*****", DateUtils.currentTime());
            e.printStackTrace();
        }
    }

    /**
     * 有付款记录,但没有点击对账的,自动对账
     */
    public void automaticReconciliation(){
        Date date = DateUtils.subDays(1);
        log.info("*****正在获取,执行时间:{}*****", date);
        try {
            pcmsReconciliationService.automaticReconciliation();
        } catch (Exception e) {
            log.info("*****自动对账出错*****", DateUtils.currentTime());
            e.printStackTrace();
        }
    }

    /**
     * 自动统计表上上个月余额为0的
     */
    public void automaticStatistics(){
        Date date = DateUtils.subDays(1);
        log.info("*****正在获取,执行时间:{}*****", date);
        try {
            pcmsReconciliationService.automaticStatistics();
        } catch (Exception e) {
            log.info("*****自动统计出错*****", DateUtils.currentTime());
            e.printStackTrace();
        }
    }
    /**
     * 自动生成未结明细记录
     */
    public void automaticDetailList(){
        Date date = DateUtils.subDays(1);
        log.info("*****正在获取,执行时间:{}*****", date);
        try {
            pcmsReconciliationService.automaticDetailList();
        } catch (Exception e) {
            log.info("*****自动生成未结明细记录出错*****", DateUtils.currentTime());
            e.printStackTrace();
        }
    }
}
