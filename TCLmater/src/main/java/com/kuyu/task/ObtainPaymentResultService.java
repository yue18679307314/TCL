package com.kuyu.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kuyu.mapper.TpmUserStatementMapper;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.util.HttpRequest;
import com.kuyu.vo.PaymentDaVo;
import com.kuyu.vo.PaymentDataResultVo;
import com.kuyu.vo.PaymentDataVo;

@Service
@Transactional
public class ObtainPaymentResultService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private TpmUserStatementMapper tpmUserStatementMapper;

	@Value("${tpm.sharePlatform.url}")
    private String tpmUrl;
	
	@Scheduled(cron = "${cron.getPaymentResult}")
	public void obtainPaymentResults(){
		List<PaymentDataVo> paymentList = new ArrayList<>();
		paymentList = tpmUserStatementMapper.getPaymentList();
		if(paymentList != null && paymentList.size() > 0){
			String content = JSON.toJSONString(paymentList);
			log.info("准备调用共享平台,参数为:{}",content);
			String param = "interfaceNo=3&requestParams=" + content;
			String message = HttpRequest.sendGet(tpmUrl, param);
			log.info("调用共享平台结束，返回的数据为：{}",message);
			PaymentDataResultVo paymentDataResultVo = JSON.parseObject(message, PaymentDataResultVo.class);
			if("9999".equals(paymentDataResultVo.getRetCode())){
				List<PaymentDaVo> pv = paymentDataResultVo.getRetList();
				for (PaymentDaVo paymentDaVo : pv) {
					List<TpmUserStatementModel> tusmList = tpmUserStatementMapper.selectList(
							new EntityWrapper<TpmUserStatementModel>().eq("payment_id", paymentDaVo.getPaymentId()));
					if("9999".equals(paymentDaVo.getResultCode())){
						for (TpmUserStatementModel tusm : tusmList) {
							if(tusm.getPay_state() != 2){
								tusm.setPay_state(2);
								tusm.updateById();
							}
						}
					}else if("4444".equals(paymentDaVo.getResultCode())){
						for (TpmUserStatementModel tusm : tusmList) {
							if(tusm.getPay_state() == 1){
								tusm.setPay_state(3);
								tusm.updateById();
							}
						}
					}
				}
			}
		}
	}
	
}
