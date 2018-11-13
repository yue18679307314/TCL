package com.kuyu.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kuyu.mapper.TpmUserBaseInfoMapper;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.service.TpmEmployeeService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kuyu.common.CommonConstants;
import com.kuyu.mapper.TpmUserAccountInfoMapper;
import com.kuyu.mapper.UserRoleInfoMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.service.TpmUserAccountInfoService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.HttpRequest;
import com.kuyu.util.RSAUtils;
import com.kuyu.util.SendWeiXinTemplateMSGUtil;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.AccountVo;
import com.kuyu.vo.BankInfoVo;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.TpmAccountVo;
import com.kuyu.vo.VerifyBankAccountVo;

/**
 * Created by tang_zhen 2017/9/21.
 */
@Service
@Transactional

public class TpmUserAccountInfoServiceImpl extends ServiceImpl<TpmUserAccountInfoMapper, TpmUserBaseInfoModel>
		implements TpmUserAccountInfoService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${bank_info_path}")
    private String bankInfoPath;
	
	@Value("${bank_info_url}")
	private String bankInfoUrl;
	
	@Autowired
	private TpmUserAccountInfoMapper tpmUserAccountInfoMapper;
	@Autowired
	private TpmOptLogsService tpmOptLogsService;
	@Autowired
	private TpmEmployeeService tpmEmployeeService;
	@Autowired
	private SendWeiXinTemplateMSGUtil sendWeiXinTemplateMSGUtil;
	@Autowired
	private UserRoleInfoMapper userRoleInfoMapper;
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;
	@Autowired
	private TpmUserBaseInfoMapper tpmUserBaseInfoMapper;
	@Value("${tpm.sharePlatform.url}")
	private String tpmUrl;

	@Value("${userAccount.verify.success}")
	private String successHead;

	@Value("${userAccount.verify.fail}")
	private String failHead;
	@Value("${userAccount.verify.templateId}")
	private String templateId;
	@Value("${userAccount.flag}")
	private String userAccountFlag;

	/**
	 * 插入或者更新银行卡信息
	 *
	 * @param tpmUserBaseInfoModel
	 * @return ResultVo
	 * @throws Exception
	 */
	@Override
	public ResultVo updateAccountData(Integer personType, TpmUserBaseInfoModel tpmUserBaseInfoModel) throws Exception {
		// ResultVo resultVo = new ResultVo();
		String accountValue = tpmUserBaseInfoModel.getAccount_value();
		accountValue = URLDecoder.decode(accountValue, "UTF-8");
		byte[] decodedPwds2 = RSAUtils.decryptByPrivateKey(accountValue, RSAUtils.PRIVATE_KEY);
		accountValue = new String(decodedPwds2);
		tpmUserBaseInfoModel.setAccount_value(accountValue);
		// 判断参数非空
		if (StringUtil.isEmpty(tpmUserBaseInfoModel.getOpenid())
				|| StringUtil.isEmpty(tpmUserBaseInfoModel.getAccount_bank_name())
				|| StringUtil.isEmpty(tpmUserBaseInfoModel.getAccount_value())
				|| StringUtil.isEmpty(tpmUserBaseInfoModel.getOpen_branch())
				|| StringUtil.isEmpty(tpmUserBaseInfoModel.getOpen_city())
				|| StringUtil.isEmpty(tpmUserBaseInfoModel.getOpen_province())
				|| StringUtil.isEmpty(tpmUserBaseInfoModel.getAccount_name())) {
			return ResultVo.getByEnumCode(CommonConstants.PARAM_IS_ERROR_CODE);
		}
		// 判断银行卡位数
		if (tpmUserBaseInfoModel.getAccount_value().length() < 16) {
			return ResultVo.getByEnumCode(CommonConstants.PARAM_IS_ERROR_CODE);
		}

		// 银行卡数据存在*号
		if (tpmUserBaseInfoModel.getAccount_value().contains("*")) {
			return ResultVo.getByEnumCode(CommonConstants.PARAM_IS_ERROR_CODE);
		}

		if (tpmUserAccountInfoMapper.selectAccountInfo(tpmUserBaseInfoModel.getOpenid()) == ""
				|| tpmUserAccountInfoMapper.selectAccountInfo(tpmUserBaseInfoModel.getOpenid()) == null) {
			return ResultVo.getByEnumCode(CommonConstants.FAIL_CODE);
		}
		//开户名和姓名不一致
		/*if (!tpmUserBaseInfoModel.getAccount_name()
				.equals(tpmUserAccountInfoMapper.getNameByOpenid(tpmUserBaseInfoModel.getOpenid()))) {
			return ResultVo.getByEnumCode(CommonConstants.ACCOUNTNAME_NOT_EQUAL_NAME_CODE);
		}*/
		//开户支行太长
		if(tpmUserBaseInfoModel.getOpen_branch().length()>30)
		{
			return ResultVo.getByEnumCode(CommonConstants.OPEN_BRANCH_TOO_LONG_CODE);
		}
		if(tpmUserBaseInfoModel.getOpen_city().length()>20)
		{
			return ResultVo.getByEnumCode(CommonConstants.OPEN_CITY_TOO_LONG_CODE);
		}

		tpmUserBaseInfoModel = accountInfoCleanXss(tpmUserBaseInfoModel);
		// 数据库中为“暂未填写”，表明第一次填入账号信息，设置修改时间为创建时间
		if (tpmUserAccountInfoMapper.selectAccountInfo(tpmUserBaseInfoModel.getOpenid()).equals("暂未填写")) {
			tpmUserBaseInfoModel
					.setModify_time(tpmUserAccountInfoMapper.selectCreateTime(tpmUserBaseInfoModel.getOpenid()));
			if (userAccountFlag.equals("0"))// 不调财务共享系统的接口
			{
				tpmUserAccountInfoMapper.updateAccountInfo(tpmUserBaseInfoModel);
				return ResultVo.getDataWithSuccess(null);
			} else {
				String reqBillNo = putAccountIntoFinance(tpmUserBaseInfoModel);// 财务共享
				if (reqBillNo != null) {
					tpmUserBaseInfoModel.setReq_fssc_bill(reqBillNo);//
					tpmUserBaseInfoModel.setBank_account_verify("2");// 第一次填入信息，审核状态设为2
					tpmUserAccountInfoMapper.updateAccountInfo(tpmUserBaseInfoModel);
					return ResultVo.getDataWithSuccess(null);
				} else {
					return ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
				}
			}
		} else {// 数据库中不是“暂未填写”，表明正在修改账号信息，设置修改时间为当前时间
			tpmUserBaseInfoModel.setModify_time(DateUtils.getSysDateTimeString());

			/*
			 * TpmUserBaseInfoModel preTpmUserBaseInfoModel =
			 * tpmUserAccountInfoMapper.selectAllAccountInfo(tpmUserBaseInfoModel.getOpenid(
			 * )); tpmUserAccountInfoMapper.updateAccountInfo(tpmUserBaseInfoModel);
			 * logs(tpmUserBaseInfoModel,preTpmUserBaseInfoModel); return
			 * ResultVo.get(ResultVo.INSERT_SUCCESS);
			 */

			if (userAccountFlag.equals("0")) {//不调财务共享接口
				TpmUserBaseInfoModel preTpmUserBaseInfoModel = tpmUserAccountInfoMapper
						.selectAllAccountInfo(tpmUserBaseInfoModel.getOpenid());
				tpmUserAccountInfoMapper.updateAccountInfo(tpmUserBaseInfoModel);
				logs(tpmUserBaseInfoModel, preTpmUserBaseInfoModel);
				return ResultVo.getDataWithSuccess(null);
			} else {
				TpmUserBaseInfoModel preTpmUserBaseInfoModel = tpmUserAccountInfoMapper
						.selectAllAccountInfo(tpmUserBaseInfoModel.getOpenid());
				if (accountIsChangedOrNot(tpmUserBaseInfoModel, preTpmUserBaseInfoModel) == 0)// 表明银行卡信息并未改动
				{
					return ResultVo.getDataWithSuccess(null);
				}

				String reqBillNo = putAccountIntoFinance(tpmUserBaseInfoModel);
				if (reqBillNo != null) {
					tpmUserBaseInfoModel.setReq_fssc_bill(reqBillNo);
					tpmUserBaseInfoModel.setBank_account_verify("2");// 设置状态为审核中
					tpmUserAccountInfoMapper.updateAccountInfo(tpmUserBaseInfoModel);
					logs(tpmUserBaseInfoModel, preTpmUserBaseInfoModel);
					return ResultVo.getDataWithSuccess(null);
				} else {
					return ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
				}
			}
			// tpmUserAccountInfoMapper.updateAccountInfo(tpmUserBaseInfoModel);
			// 导入财务共享，返回流水单号
			// putAccountIntoFinance(tpmUserBaseInfoModel);
		}
	}

	private TpmUserBaseInfoModel accountInfoCleanXss(TpmUserBaseInfoModel tpmUserBaseInfoModel) {
		tpmUserBaseInfoModel.setAccount_value(StringUtil.cleanXSS(tpmUserBaseInfoModel.getAccount_value()));
		tpmUserBaseInfoModel.setAccount_bank_name(StringUtil.cleanXSS(tpmUserBaseInfoModel.getAccount_bank_name()));
		tpmUserBaseInfoModel.setAccount_name(StringUtil.cleanXSS(tpmUserBaseInfoModel.getAccount_name()));
		tpmUserBaseInfoModel.setOpen_city(StringUtil.cleanXSS(tpmUserBaseInfoModel.getOpen_city()));
		tpmUserBaseInfoModel.setOpen_province(StringUtil.cleanXSS(tpmUserBaseInfoModel.getOpen_province()));
		tpmUserBaseInfoModel.setOpen_branch(StringUtil.cleanXSS(tpmUserBaseInfoModel.getOpen_branch()));
		return tpmUserBaseInfoModel;
	}

	/**
	 * 将银行卡信息导入到财务共享系统
	 *
	 * @param tpmUserBaseInfoModel
	 */
	public String putAccountIntoFinance(TpmUserBaseInfoModel tpmUserBaseInfoModel) throws Exception {
		AccountVo accountVo = new AccountVo();
		List<TpmAccountVo> list = new ArrayList<>();
		TpmAccountVo tpmAccountVo = new TpmAccountVo();
		// 银行卡信息放入Vo中
		tpmAccountVo.setACCOUNT_BANK_NAME(tpmUserBaseInfoModel.getAccount_bank_name());
		tpmAccountVo.setACCOUNT_NAME(tpmUserBaseInfoModel.getAccount_name());
		tpmAccountVo.setACCOUNT_VALUE(tpmUserBaseInfoModel.getAccount_value());
		tpmAccountVo.setOPEN_BRANCH(tpmUserBaseInfoModel.getOpen_branch());
		tpmAccountVo.setOPEN_CITY(tpmUserBaseInfoModel.getOpen_city());
		tpmAccountVo.setOPEN_PROVINCE(tpmUserBaseInfoModel.getOpen_province());
		// 银行卡信息的Vo放入list中
		list.add(tpmAccountVo);
		accountVo.setDETAIL_LIST(list);
		accountVo.setMEMO(tpmUserAccountInfoMapper.getTitle() + "" + DateUtils.getReqDate());

		accountVo.setREQUEST_USER(tpmUserAccountInfoMapper.getUserCode());
		TpmEmployeeModel tpmEmployeeModel = tpmEmployeeService.getTpmEmployeebyPersonCode(tpmUserAccountInfoMapper.getUserCode());
		accountVo.setREQUEST_DEPART(tpmEmployeeModel.getOrg_code());

		// 将Vo转为json再转为字符串
		String content = JSON.toJSONString(accountVo);
		String param = "interfaceNo=0&requestParams=" + content;
		log.info("传入财务共享的银行账号信息:{}", param);
		String message = HttpRequest.sendGet(tpmUrl, param);
		log.info("财务共享系统回传的全部信息:{}", message);
		JSONObject jsonObject = new JSONObject(message);
		String ret_code = jsonObject.getString("RET_CODE");
		if (Integer.valueOf(ret_code) == 9999) {
			String reqBillNo = jsonObject.getString("RET_BILL_NO");
			return reqBillNo;
		} else {
			return null;
		}
	}

	/**
	 *
	 * @return
	 * @param openid
	 */
	public ResultVo getTheBankAccountVerify(String openid) {
		String bankAccountVerify = tpmUserAccountInfoMapper.getBankAccountVerifyInfo(openid);
		if (bankAccountVerify == null) {
			bankAccountVerify = "3";
		}
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		rs.setData(bankAccountVerify);
		return rs;
	}

	/**
	 * 财务共享调用接口，传银行卡的初步审核的结果信息
	 *
	 * @param verifyBankAccountVo
	 * @return FinancialResultVo
	 * @throws Exception
	 */
	@Override
	public FinancialResultVo insertBankAccountVerify(VerifyBankAccountVo verifyBankAccountVo) throws Exception {
		// tpmUserBaseInfoMapper.insertBankAccountVerify(verifyBankAccountVo);
		// System.out.println(verifyBankAccountVo.getBILL_DEAL_CODE()+"----"+verifyBankAccountVo.getBILL_DEAL_MAG()+"---"+verifyBankAccountVo.getREQ_FSSC_BILL());
		if (Integer.valueOf(verifyBankAccountVo.getBill_deal_code()) == 9999) {
			// 调用微信接口，通知用户银行卡信息通过初步审核
			verifyBankAccountVo.setBank_account_verify("1");
			if (tpmUserAccountInfoMapper.updateBankAccountVerify(verifyBankAccountVo) == 1) {
				informWxfUser(verifyBankAccountVo);
				// 将结算单中支付状态从“支付失败更改为“-1未付款”
//				tpmUserAccountInfoMapper.updateUserStatementPaystate(
//						tpmUserAccountInfoMapper.getUserBaseInfoByReqfsscbill(verifyBankAccountVo).getOpenid());
			} else {
				FinancialResultVo.get(FinancialResultVo.SUCCESS);
			}
			FinancialResultVo.get(FinancialResultVo.SUCCESS);
		} else {
			// 调用微信接口，通知用户银行卡信息未通过初步审核
			verifyBankAccountVo.setBank_account_verify("0");
			if (tpmUserAccountInfoMapper.updateBankAccountVerify(verifyBankAccountVo) == 1) {
				informWxfUser(verifyBankAccountVo);
			} else {

				FinancialResultVo.get(FinancialResultVo.SUCCESS);
			}
		}
		return FinancialResultVo.get(FinancialResultVo.SUCCESS);
	}

	/**
	 * 通知微信用户的模板消息
	 *
	 * @param verifyBankAccountVo
	 */
	public void informWxfUser(VerifyBankAccountVo verifyBankAccountVo) {
		String title = "信息审核提醒";

		TpmUserBaseInfoModel tpmUserBaseInfoModel = tpmUserAccountInfoMapper
				.getUserBaseInfoByReqfsscbill(verifyBankAccountVo);
		if (tpmUserBaseInfoModel == null) {
			return;
		}
		String accountValue = tpmUserBaseInfoModel.getAccount_value();
		String lastFourAccountValue = accountValue.substring(accountValue.length() - 4, accountValue.length());
		// String realSuccessHead = successHead.replace("{0}", lastFourAccountValue);
		// String realFailHead = failHead.replace("{0}", lastFourAccountValue);

		String openid = tpmUserBaseInfoModel.getOpenid();
		String[] baseInfo = new String[3];
		baseInfo[0] = tpmUserBaseInfoModel.getName();
		baseInfo[1] = tpmUserBaseInfoModel.getAccount_bank_name();
		baseInfo[2] = lastFourAccountValue;
		String URL = null;
		failHead = failHead+"失败原因："+verifyBankAccountVo.getBill_deal_mag();
		if (verifyBankAccountVo.getBill_deal_code().equals("9999")) {
			sendWeiXinTemplateMSGUtil.sendMSG(templateId, openid, successHead + "\n", baseInfo, "");
		} else {
			sendWeiXinTemplateMSGUtil.sendMSG(templateId, openid, failHead + "\n", baseInfo, "");
		}

	}

	/**
	 * 返回银行列表
	 *
	 * @param
	 */
	public List<String> getBankList() {
		List<String> bankList = tpmUserAccountInfoMapper.getBankList();
		return bankList;
	}

	/**
	 * 判断银行卡账号信息是否有变动,0 是未改动，1 是改动
	 * 
	 * @param tpmUserBaseInfoModel
	 * @param preTpmUserBaseInfoModel
	 * @return
	 */
	public int accountIsChangedOrNot(TpmUserBaseInfoModel tpmUserBaseInfoModel,
			TpmUserBaseInfoModel preTpmUserBaseInfoModel) {
		if (preTpmUserBaseInfoModel.getAccount_bank_name().equals(tpmUserBaseInfoModel.getAccount_bank_name())
				&& preTpmUserBaseInfoModel.getAccount_name().equals(tpmUserBaseInfoModel.getAccount_name())
				&& preTpmUserBaseInfoModel.getAccount_value().equals(tpmUserBaseInfoModel.getAccount_value())
				&& preTpmUserBaseInfoModel.getOpen_branch().equals(tpmUserBaseInfoModel.getOpen_branch())
				&& preTpmUserBaseInfoModel.getOpen_city().equals(tpmUserBaseInfoModel.getOpen_city())
				&& preTpmUserBaseInfoModel.getOpen_province().equals(tpmUserBaseInfoModel.getOpen_province())) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * 调用日志接口
	 *
	 * @param tpmUserBaseInfoModel
	 */
	public int logs(TpmUserBaseInfoModel tpmUserBaseInfoModel, TpmUserBaseInfoModel preTpmUserBaseInfoModel)
			throws Exception {
		TpmOptLogsModel tpmOptLogsModel = new TpmOptLogsModel();

		String content2 = "";
		int flag = 0;
		if (!preTpmUserBaseInfoModel.getAccount_bank_name().equals(tpmUserBaseInfoModel.getAccount_bank_name())) {
			content2 = "原银行名为:" + preTpmUserBaseInfoModel.getAccount_bank_name() + ",银行名改为:"
					+ tpmUserBaseInfoModel.getAccount_bank_name() + "；"+"姓名改为"+tpmUserBaseInfoModel.getAccount_bank_name();
			flag = 1;
		}
		if (!preTpmUserBaseInfoModel.getAccount_name().equals(tpmUserBaseInfoModel.getAccount_name())) {
			content2 = content2 + "原账户名：" + preTpmUserBaseInfoModel.getAccount_name() + ",账户名改为："
					+ tpmUserBaseInfoModel.getAccount_name() + "；";
			flag = 1;
		}
		if (!preTpmUserBaseInfoModel.getAccount_value().equals(tpmUserBaseInfoModel.getAccount_value())) {
			content2 = content2 + "原账号为：" + preTpmUserBaseInfoModel.getAccount_value() + ",账号改为"
					+ tpmUserBaseInfoModel.getAccount_value() + "；";
			flag = 1;
		}
		if (!preTpmUserBaseInfoModel.getOpen_branch().equals(tpmUserBaseInfoModel.getOpen_branch())) {
			content2 = content2 + "原开户支行为：" + preTpmUserBaseInfoModel.getOpen_branch() + ",开户支行改为："
					+ tpmUserBaseInfoModel.getOpen_branch() + "；";
			flag = 1;
		}
		if (!preTpmUserBaseInfoModel.getOpen_city().equals(tpmUserBaseInfoModel.getOpen_city())) {
			content2 = content2 + "原开户市为：" + preTpmUserBaseInfoModel.getOpen_city() + ",开户市改为："
					+ tpmUserBaseInfoModel.getOpen_city() + "；";
			flag = 1;
		}
		if (!preTpmUserBaseInfoModel.getOpen_province().equals(tpmUserBaseInfoModel.getOpen_province())) {
			content2 = content2 + "原开户省为：" + preTpmUserBaseInfoModel.getOpen_province() + ",开户省改为："
					+ tpmUserBaseInfoModel.getOpen_province() + "；";
			flag = 1;
		}
		if (flag == 0) {
			tpmOptLogsModel.setContent("未修改任何信息");
			return flag;
		} else {
			tpmOptLogsModel.setContent(content2);
			tpmOptLogsModel.setType(1);
			tpmOptLogsModel.setOptUser(preTpmUserBaseInfoModel.getName());
			tpmOptLogsModel.setOptUserDept(getUserDept(tpmUserBaseInfoModel.getOpenid()));
			tpmOptLogsService.insertOptLogs(tpmOptLogsModel);
			return flag;
		}

	}

	private String getUserDept(String openid) {
		List<String> depts = tpmUserBaseInfoMapper.getUserDept(openid);
		if (depts.size()!=0)
		{
			return depts.get(0);
		}else {
			return "";
		}
	}
	@Override
	public ResultVo outputBankInfo(BankInfoVo bankInfoVo,LoginUserInfo userInfo) throws Exception {
//		File tempfile = new File(bankInfoPath);
//		if(!tempfile.exists()){
//    		tempfile.mkdirs();
//    	}
//		String file = bankInfoPath + StringUtil.getUUID() + ".pdf";
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		List<String> childsDeptList = new ArrayList<>();
		if (personType == -1) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
			return ResultVo.get(ResultVo.SUCCESS);
		}
		if (personType == 2) {
			childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if (childsDeptList == null || childsDeptList.size() == 0) {
				log.info("部门列表错误，为{}", childsDeptList);
				return ResultVo.get(ResultVo.SUCCESS);
			}
			bankInfoVo.setList(childsDeptList);
		}
		if(personType == 0 || personType == 6){
		  	List<String> branchDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
			if(branchDeptList == null || branchDeptList.size() == 0) {
			log.info("部门列表branchDeptList错误，为{}",branchDeptList);
			return ResultVo.get(ResultVo.SUCCESS);
			}
			bankInfoVo.setList(branchDeptList);
		}
		bankInfoVo.setPersonCode(userInfo.getEmployeeModel().getPerson_code());
		bankInfoVo.setPersonType(personType);
		Page<TpmUserBaseInfoModel> page = new Page<TpmUserBaseInfoModel>(bankInfoVo.getCurrent(), bankInfoVo.getSize());
		List<TpmUserBaseInfoModel> tubimList = tpmUserAccountInfoMapper.outputBankInfo(bankInfoVo,page);
		if(tubimList != null && tubimList.size() > 0) {
			for (TpmUserBaseInfoModel tubim : tubimList) {
				String mobile = tubim.getMobile();
				if (StringUtil.isNotNull(mobile)) {
					byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(mobile, RSAUtils.PUBLIC_KEY);
					BASE64Encoder enc = new BASE64Encoder();
					mobile = enc.encode(decodedPwds2);
					tubim.setMobile(mobile);
				}
				
				String idCard = tubim.getId_card();
				if(idCard != null) {
					byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(idCard, RSAUtils.PUBLIC_KEY);
					BASE64Encoder enc = new BASE64Encoder();
					idCard = enc.encode(decodedPwds2);
					tubim.setId_card(idCard);
				}
				
				String  accountValue = tubim.getAccount_value();
				if(accountValue != null) {
					byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(accountValue, RSAUtils.PUBLIC_KEY);
					BASE64Encoder enc = new BASE64Encoder();
					accountValue = enc.encode(decodedPwds2);
					tubim.setAccount_value(accountValue);
				}
			}
		}
//		createBankInfoPdf(tubimList,file);
		page.setRecords(tubimList);
		return ResultVo.getData(ResultVo.SUCCESS,page);
	}
	
	

	@Override
	public String getBankInfoUrl(List<String> list,String pdfOrxls,LoginUserInfo userInfo) throws Exception {
		File tempfile = new File(bankInfoPath);
		if(!tempfile.exists()){
    		tempfile.mkdirs();
    	}
		String path = StringUtil.getUUID();
		String file = bankInfoPath + "/"+ path;
		List<TpmUserBaseInfoModel> tubimList = baseMapper.queryBankInfoByOpenids(list);
		if(tubimList != null && tubimList.size() > 0) {
			String personName = userInfo.getEmployeeModel().getPerson_name();
			String personCode = userInfo.getEmployeeModel().getPerson_code();
			for (TpmUserBaseInfoModel tubim : tubimList) {
				TpmOptLogsModel tolm = new TpmOptLogsModel();
				tolm.setType(12);
				String opt_user = personName + "(" + personCode + ")";
				tolm.setOptUser(opt_user);
				tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
				String content = "临促" + tubim.getName() +"(" +tubim.getOpenid()+")的银行账户信息已下载";
				tolm.setContent(content);
				tpmOptLogsService.insertAllColumn(tolm);
			}
			if("pdf".equals(pdfOrxls)) {
				file += ".pdf";
				createBankInfoPdf(tubimList,file);
				return  bankInfoUrl+"/"+path+".pdf";
			}
			if("xls".equals(pdfOrxls)) {
				file += ".xls";
				createBankInfoXls(tubimList,file);
				return  bankInfoUrl+"/"+path+".xls";
			}
		}
		return null;
	}
	
	public void createBankInfoPdf(List<TpmUserBaseInfoModel> tubimList, String file)
			throws Exception, DocumentException {

		Document document = new Document(PageSize.A4, 20, 20, 10, 10);

		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();

		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font chineseFont = new Font(bfChinese, 5);

		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		List<PdfPRow> listRow = table.getRows();
		float[] columnWidths = {10,15,10,10,10,15,10,10,10};
		table.setWidths(columnWidths);

		PdfPCell[] head = createPdfPCellContent(getTableHead(), chineseFont);
		PdfPRow headRow = new PdfPRow(head);
		listRow.add(headRow);
		for (TpmUserBaseInfoModel tubim : tubimList) {
			List<String> contentList = getTableContent(tubim);
			PdfPCell[] cells = createPdfPCellContent(contentList, chineseFont);
			PdfPRow row = new PdfPRow(cells);
			listRow.add(row);

		}
		chineseFont = new Font(bfChinese, 10);
		Paragraph pa = new Paragraph("临促人员银行信息", chineseFont);
		pa.setAlignment(Paragraph.ALIGN_CENTER);
		chineseFont = new Font(bfChinese, 6);
		Paragraph pa2 = new Paragraph(" ", chineseFont);
		pa2.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(pa);
		document.add(pa2);
		document.add(table);
		document.close();
		pdfWriter.close();
	}

	public void createBankInfoXls(List<TpmUserBaseInfoModel> tubimList, String url)
			throws Exception, DocumentException {
		File file = new File(url);
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("银行信息表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row.createCell(0);
		// 设置单元格内容
		cell.setCellValue("临促人员银行信息");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
		HSSFCellStyle  style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont  fontStyle=wb.createFont(); 
		fontStyle.setFontName("微软雅黑"); 
		fontStyle.setFontHeightInPoints((short)16);
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(fontStyle);  
		cell.setCellStyle(style);
		HSSFCellStyle  style2 = wb.createCellStyle();
		fontStyle.setFontName("宋体"); 
		fontStyle.setFontHeightInPoints((short)12);
		HSSFFont  fontStyle2 = wb.createFont(); 
		style2.setFont(fontStyle2);  
		List<String> headList = getTableHead();
		row = sheet.createRow(1);
		for(int i = 0; i < headList.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(headList.get(i));
			if(i == 1 || i == 5) {
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * 20);
			}else {
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * 15);
			}
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(style2);
		}
		int rowNo = 1;
		for (TpmUserBaseInfoModel tubim : tubimList) {
			rowNo++;
			row = sheet.createRow(rowNo);
			List<String> list = getTableContent(tubim);
			for(int i = 0; i < list.size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(list.get(i));
				cell.setCellStyle(style2);
			}
		}
		FileOutputStream fout = new FileOutputStream(file);
		wb.write(fout);
		fout.close();
	}
	
	public List<String> getTableHead() {
		String[] arr = new String[] { "姓名", "身份证号码", "手机号", "户名", "开户银行名", "银行账号", "开户省",
				"开户市", "开户支行" };
		List<String> list = Arrays.asList(arr);
		return list;
	}
	public List<String> getTableContent(TpmUserBaseInfoModel tubim) {
		String[] arr = new String[] {tubim.getName(),tubim.getId_card(),tubim.getMobile(),tubim.getAccount_name(),
				tubim.getAccount_bank_name(),tubim.getAccount_value(),tubim.getOpen_province(),tubim.getOpen_city(),
				tubim.getOpen_branch()};
		List<String> list = Arrays.asList(arr);
		return list;
	}

	public PdfPCell[] createPdfPCellContent(List<String> list, Font chineseFont) {

		PdfPCell[] cells = new PdfPCell[list.size()];
		for (int i = 0; i < list.size(); i++) {
			cells[i] = new PdfPCell(new Paragraph(list.get(i), chineseFont));
		}

		return cells;
	}
}
