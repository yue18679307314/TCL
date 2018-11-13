package com.kuyu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.TpmSingleUserMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.TpmProjectModel;
import com.kuyu.model.TpmSingleUserModel;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.service.TpmProjectService;
import com.kuyu.service.TpmSingleUserService;
import com.kuyu.util.DateUtils;
import com.kuyu.util.HttpRequest;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.PersonCodeVo;
import com.kuyu.vo.TpmSingleUserVo;

@Service
@Transactional
public class TpmSingleUserServiceImpl extends ServiceImpl<TpmSingleUserMapper, TpmSingleUserModel>
		implements TpmSingleUserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TpmProjectService tpmProjectService;

	@Autowired
	private TpmOptLogsService tpmOptLogsService;

	@Resource
	private TpmEmployeeService tpmEmployeeService;

	@Value("${tpm.sharePlatform.url}")
	private String tpmUrl;

	@Override
	public Integer insertSingleUser(String fsscBill, List<PersonCodeVo> personList, LoginUserInfo userInfo)
			throws Exception {
		TpmSingleUserModel tpmSingleUserModel = new TpmSingleUserModel();
		tpmSingleUserModel.setFssc_bill(fsscBill);
		tpmSingleUserModel.setOp_time(DateUtils.currentTime());
		TpmProjectModel tpmProjectModel = tpmProjectService.selectById(fsscBill);
		String requestUser = tpmProjectModel.getRequestUser();
		tpmSingleUserModel.setRequest_user_original(requestUser);
		String personCode = personList.get(0).getPerson_code();
		tpmProjectModel.setRequestUser(personCode);
		tpmProjectModel.updateById();
		for (PersonCodeVo person : personList) {
			tpmSingleUserModel.setPerson_code(person.getPerson_code());
			if (baseMapper.queryTpmSingleUserModel(tpmSingleUserModel) == null) {
				tpmSingleUserModel.setUuid(StringUtil.getUUID());
				baseMapper.insertSingleUser(tpmSingleUserModel);
			}
		}
		TpmOptLogsModel tolm = new TpmOptLogsModel();
		tolm.setType(6);
		String opt_user = userInfo.getEmployeeModel().getPerson_name() + "("
				+ userInfo.getEmployeeModel().getPerson_code() + ")";
		tolm.setOptUser(opt_user);
		tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
		String content = "立项单" + fsscBill + "可使用人由"
				+ tpmEmployeeService.getTpmEmployeebyPersonCode(requestUser).getPerson_name() + "(" + requestUser + ")变更为"
				+ tpmEmployeeService.getTpmEmployeebyPersonCode(personCode).getPerson_name() + "(" + personCode + ")";
		tolm.setContent(content);
		tpmOptLogsService.insertOptLogs(tolm);
		return 0;
	}

	public FinancialResultVo post(TpmSingleUserVo tpmSingleUserVo) throws Exception {
		String content = JSON.toJSONString(tpmSingleUserVo);
		String param = "interfaceNo=2&requestParams=" + content;
		log.info("调用财务接口，开始创建立项单可使用人，出入数据为：", param);
		String message = HttpRequest.sendGet(tpmUrl, param);
		log.info("调用财务接口，创建立项单可使用人结束，返回数据为：", message);
		JSONObject json = new JSONObject(message);
		String code = json.getString("RET_CODE");
		String meg = json.getString("RET_MSG");
		// System.out.println("param:"+param);
		// System.out.println("code:"+code);
		// System.out.println("meg："+meg);
		return new FinancialResultVo(code, meg);
	}

}
