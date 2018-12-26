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
            log.info("*****生成上个月的对账信息:{}*****", DateUtils.currentTime());
            e.printStackTrace();
        }
    }



}
