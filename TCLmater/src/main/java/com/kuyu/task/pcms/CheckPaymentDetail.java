package com.kuyu.task.pcms;

import static com.kuyu.util.DateUtils.DATE_FORMAT_DATEONLY;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kuyu.service.PcmsItemService;
import com.kuyu.util.DateUtils;

@Component
public class CheckPaymentDetail {

	private static final Logger log = LoggerFactory.getLogger(CheckPaymentDetail.class);
	
	@Resource
    private PcmsItemService pcmsItemService;
	
	@Scheduled(cron = "${cron.checkPaymentDetail}")//凌晨01:00执行一次
    public void updateItemStatus() {
        Date date = new Date();
        String synDate = DateUtils.toString(date,DATE_FORMAT_DATEONLY);
        log.info("校验当天的付款信息", synDate);
        try {
        	pcmsItemService.checkPaymentDetail(synDate,0);
        } catch (Exception e) {
            log.info("*****校验当天的付款信息出错*****", DateUtils.currentTime());
            e.printStackTrace();
        }
	}
}
